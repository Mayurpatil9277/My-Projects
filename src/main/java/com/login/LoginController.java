package com.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emailsender.EmailSender;

@RestController

public class LoginController {
	@RequestMapping("/login")
	public String index(@RequestParam String userName,@RequestParam String password) {
		try {

			Assert.hasLength(userName, "userName must not be null and must not the empty");
			Assert.hasLength(password, "password must not be null and must not the empty");

			//validate username and password provided by user matches with senders file data or not
			validateUserNameAndPassword(userName,password);
			
			
			//Assert.isTrue(-11 > 0, "speed must be positive");
			List<String> senderList = new ArrayList<String>();
			readStaticFiles("static/config/sender.txt", senderList);
			Assert.notEmpty( senderList,  "Senders list mustn't be empty"); 

			List<String> receiverList = new ArrayList<String>();
			readStaticFiles("static/config/receiver.txt", receiverList);
			Assert.notEmpty( receiverList,  "Receivers list mustn't be empty"); 
			
			String emailBody = getEmailBody("static/config/mailbody.txt");
			Assert.hasLength(emailBody, "emailBody must not be null and must not the empty");
			
			StringBuilder sb = new StringBuilder();
			String subject= null;
			for(int i=0;i<senderList.size();i++) {
				for(int j=0;j<receiverList.size();j++) {
					subject = (i+1)+" "+senderList.get(i).toUpperCase()+" "+receiverList.get(j).toUpperCase()+"  "+(j+1);
					EmailSender.sendEmail(senderList.get(i), receiverList.get(j),subject,emailBody);
					sb.append(subject+"<br>");
				}
			}
			//EmailSender.sendEmail("mayurpatil9277@gmail.com", "patil.mandar77@gmail.com");
			return "Email has been sent to below users: <br>"+sb.toString() ;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public void readStaticFiles(String fileName,List<String> list) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(fileName)));
        String inputLine = null;
		while ((inputLine = br.readLine()) != null) {
			list.add(inputLine.split("[,]", 0)[0]);
		}
	}

	public String getEmailBody(String fileName) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(fileName)));
        String inputLine = null;
        StringBuilder sb = new StringBuilder();
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
		}
		return sb.toString();
	}
	
	public void validateUserNameAndPassword(String userName,String password) throws Exception{
		ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("static/config/sender.txt")));
        String inputLine = null;
        boolean matchFound = false;
		while ((inputLine = br.readLine()) != null) {
			String [] unamePwdArray = inputLine.split("[,]", 0);
			if(unamePwdArray[0].equals(userName) && unamePwdArray[1].equals(password)) {
				matchFound = true;
				break;
			}
		}
		if(!matchFound) {
			throw new Exception("Incorrect username and password");
		}
	}
}
