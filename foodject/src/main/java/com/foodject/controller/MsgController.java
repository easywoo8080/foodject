package com.foodject.controller;

import java.io.IOException;

import com.foodject.restapi.chatbot;
import com.foodject.vo.Msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;



@Controller
public class MsgController {
	
	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	chatbot cbot;
	
	@MessageMapping("/receiveall") // 모두에게 전송
	public void receiveall(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
		System.out.println(msg);
		template.convertAndSend("/send",msg);
	}
	@MessageMapping("/receiveme") // 나에게만 전송 ex)Chatbot
	public void receiveme(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
		String id = msg.getSendid();

		try {
			String result = cbot.getMessage(msg.getContent1());
			msg.setContent2(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		template.convertAndSend("/send/"+id,msg);
	}
	@MessageMapping("/receiveto") // 특정 Id에게 전송
	public void receiveto(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
		String id = msg.getSendid();
		String target = msg.getReceiveid();
		template.convertAndSend("/send/to/"+target,msg);
	}
	@MessageMapping("/receivetomsg") // 특정 Id에게 전송
	public void receivetomsg(Msg msg, SimpMessageHeaderAccessor headerAccessor) {
		String id = msg.getSendid();
		String target = msg.getReceiveid();
		template.convertAndSend("/send/tomsg/"+target,msg);
	}
}
