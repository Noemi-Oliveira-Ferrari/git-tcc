package br.net.daumhelp.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import br.net.daumhelp.model.Confirmacao;

public class HandleEmails {
	
	public static void enviar(Confirmacao confirm) {
		
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.googlemail.com");
//		email.setSmtpPort(465);
//		email.setAuthenticator(new DefaultAuthenticator("brace.everything@gmail.com", "duh2019s2"));
//		email.setSSLOnConnect(true);
//		try {
//			email.setFrom("brace.everything@gmail.com");
//			email.setSubject("Codigo de Confirmação DaUmHelp!");
//			email.setMsg(confirm.getCodigoConfirm());
//			email.addTo(confirm.getRemetente());
//			email.send();
//		} catch (EmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		  Create the email message
		HtmlEmail email = new HtmlEmail();
		email.setSmtpPort(465);
		email.setHostName("smtp.googlemail.com");
		try {
			email.addTo("silvaferreira129@gmail.com", "Kelvin");
			email.setFrom("brace.everything@gmail.com", "Brace");
			email.setSubject("Test email with inline image");
					  
			// embed the image and get the content id
			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
			String cid = email.embed(url, "Apache logo");
					  
			// set the html message
			email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
					
			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");
					
			// send the email
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		
//		Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//     
//        Session session = Session.getDefaultInstance(props,
//          new javax.mail.Authenticator() {
//               protected PasswordAuthentication getPasswordAuthentication() 
//               {
//                     return new PasswordAuthentication(
//                    		 	"brace.everything@gmail.com", 
//                		 		"duh2019s2");
//               }
//          });
//     
//
//        session.setDebug(true);
//     
//        try {
//     
//          Message message = new MimeMessage(session);
//          message.setFrom(new InternetAddress("brace.everything@gmail.com")); 
//
//          Address[] toUser = InternetAddress 
//                     .parse("silvaferreira129@gmail.com");  
//     
//          message.setRecipients(Message.RecipientType.TO, toUser);
//          message.setSubject("Enviando email com JavaMail");
//          message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
//
//          Transport.send(message);
//     
//          System.out.println("Feito!!!");
//     
//         } catch (MessagingException e) {
//        	 System.out.println("erro");	
//            throw new RuntimeException(e);
//         }
//	}
	}
}
