package com.pms.command;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.ManagerDAO;
import com.pms.dto.ManagerBean;
import com.pms.util.SMTPAuthenticatior;


/*import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import mail.SMTPAuthenticatior;
import javax.mail.Authenticator;
import java.util.Properties;*/




public class SearchIdCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		request.setCharacterEncoding("UTF-8");
		
		ManagerDAO mdao = new ManagerDAO();
		ManagerBean idbean = new ManagerBean();
		
		String name = request.getParameter("name"); //받는사람이름
		idbean.setName(name); 
		String email = request.getParameter("email"); //보샐사람..
		idbean.setEmail(email); 
		idbean.setTel(request.getParameter("tel"));
		
		String idsearch =mdao.MdSearchId(idbean);
		
		request.setAttribute("idsearch", idsearch);
		
		
		
		
		Properties p = new Properties(); // 정보를 담을 객체
		
		p.put("mail.smtp.host","smtp.naver.com"); // 네이버 SMTP(키값,메일서버주소)

		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		// SMTP 서버에 접속하기 위한 정보들
		
		String noo = "없음";
		if(idsearch != null&& idsearch != noo) {
			try{
			    Authenticator auth = new SMTPAuthenticatior();
			    Session ses = Session.getInstance(p, auth);
			     
			    ses.setDebug(true);
			     
			    MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
			    String subject = name + " 님의 아이디 입니다.";
			    msg.setSubject(subject); // 제목
			    
			    String from = "hczza@naver.com";
			    Address fromAddr = new InternetAddress(from);
			    msg.setFrom(fromAddr); // 보내는 사람
			     
			    Address toAddr = new InternetAddress(email);
			    msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
			     
			    String content = "귀하의 ID : " + idsearch + " 입니다.";
			    msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩
			     
			    Transport.send(msg); // 전송
			} catch(Exception e){
			    e.printStackTrace();
			    

			}
		}
		


		
		return "login/searchId";
	}

}




























