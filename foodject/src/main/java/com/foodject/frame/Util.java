package com.foodject.frame;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;

import com.foodject.restapi.NaverObj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class Util {

	@Autowired
	NaverObj nobj;
	@Value("${userDir}")
	String userDir;

	// 파일, 저장할 이름, 사용되는 디비 테이블명
	public void saveFile(MultipartFile mf, String savename, String table) {
		byte [] data;
		System.out.println("Enter 'saveFile'");

		//이미지 경로설정
		//String pimgpath = Paths.get(System.getProperty("user.dir"), "src", "main","resources","static","custimg").toString();
		String filePath = "";

	
		filePath = userDir + File.separator + table + File.separator + savename;
		
		// System.out.println("Os name : "+System.getProperty("os.name").toLowerCase().startsWith("win"));
        System.out.println("path Result : " + filePath);
        
		try {
			data = mf.getBytes();
			FileOutputStream fo =
					new FileOutputStream(filePath);
			fo.write(data);
			fo.close();

			nobj.upLoad( mf , filePath , savename, table);
			
		} catch (IOException e) {
			
		}

	}

}
