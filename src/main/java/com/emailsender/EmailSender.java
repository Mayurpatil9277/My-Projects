package com.emailsender;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.InputStreamReader;
import java.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;

public class EmailSender {
	static String apiKey = "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";
	public static String sendEmail(String fromId,String toId, String subject, String emailBody) {
		// TODO Auto-generated method stub
		System.out.println("Test");
		CloseableHttpClient httpClient=null;
		Base64.Encoder encoder = Base64.getEncoder();
		String attachmentStr = encoder.encodeToString(subject.getBytes());  
		try{
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("https://api.sendgrid.com/v3/mail/send");
			
			
			httpPost.setHeader("content-type", "application/json");
			//httpPost.setHeader("accept", "application/json");
			httpPost.setHeader("YOUR_API_KEY", "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");
			httpPost.setHeader("Authorization", "Bearer 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");
			String jsonStr="{\r\n" + 
					"  \"personalizations\": [\r\n" + 
					"    {\r\n" + 
					"      \"to\": [\r\n" + 
					"        {\r\n" + 
					"          \"email\": \""+toId+"\"\r\n" + 
					"        }\r\n" + 
					"      ],\r\n" + 
					"      \"subject\": \""+subject+"\"\r\n" + 
					"    }\r\n" + 
					"  ],\r\n" + 
					"  \"from\": {\r\n" + 
					"    \"email\": \""+fromId+"\"\r\n" + 
					"  },\r\n" + 
					"  \"content\": [\r\n" + 
					"    {\r\n" + 
					"      \"type\": \"text/plain\",\r\n" + 
					"      \"value\": \""+emailBody+"\"\r\n" + 
					"    }\r\n" + 
					"  ],\r\n" + 
					"\"attachments\":[ {\"content\":\""+attachmentStr+"\", \"filename\":\"attachment.txt\", \"disposition\":\"attachment\"} ]"+
					"}";
			StringEntity jsonString =new StringEntity(jsonStr);
			httpPost.setEntity(jsonString);
			StringBuilder sb = new StringBuilder();
		    HttpResponse response = httpClient.execute(httpPost);
		    BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String inputLine = null;
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			httpClient.close();
			System.out.println(sb.toString());
			
			//return sb.toString();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return "1";
	}
}
