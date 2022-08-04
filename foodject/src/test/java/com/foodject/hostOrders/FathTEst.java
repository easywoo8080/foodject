package com.foodject.hostOrders;

import java.nio.file.Paths;

import com.foodject.biz.HostOrdersBiz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FathTEst {

	@Autowired
	HostOrdersBiz biz;

	@Test
	void contextLoads() {
		System.out.println(Paths.get(System.getProperty("user.dir"), "foodject", "src", "main","resources","static","foodject" ).toString() );	
	}

}
