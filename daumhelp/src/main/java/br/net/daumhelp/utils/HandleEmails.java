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
//		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("brace.everything@gmail.com", "duh2019s2"));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("brace.everything@gmail.com", "DaUmHelp!");
			email.setSubject("Codigo de Confirmação DaUmHelp!");
			email.setMsg(confirm.getCodigoConfirm());
			email.addTo(confirm.getDestinatario());
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		  Create the email message
//		HtmlEmail email = new HtmlEmail();
////		email.setSmtpPort(465);
//		email.setHostName("smtp.googlemail.com");
//		try {
//			email.setAuthenticator(new DefaultAuthenticator("brace.everything@gmail.com", "duh2019s2"));
//			email.setSSLOnConnect(true);
//			email.setFrom("brace.everything@gmail.com", "DaUmHelp!");
//			email.addTo(confirm.getDestinatario(), "Kelvin");
//			email.setSubject("Código de Confirmação DaUmHelp!");
//			
//			// embed the image and get the content id
//			URL urlImgDuh = new URL("https://brazilsouth1-mediap.svc.ms/transform/thumbnail?provider=spo&inputFormat=png&cs=fFNQTw&docid=https%3A%2F%2Fsesisenaispedu.sharepoint.com%3A443%2F_api%2Fv2.0%2Fdrives%2Fb!N_uWADtdaUW2bLhJUddII4zCWNHetZ5PrqtXbllSo6U1DLnINVEjSrO0oALWjG5D%2Fitems%2F01UTUAIIHAK5X4PJXAVFDYWKZ3OLAU2XJG%3Fversion%3DPublished&access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAvc2VzaXNlbmFpc3BlZHUuc2hhcmVwb2ludC5jb21AYjEwNTFjNGItM2I5NC00MWFiLTk0NDEtZTczYTcyMzQyZmRkIiwiaXNzIjoiMDAwMDAwMDMtMDAwMC0wZmYxLWNlMDAtMDAwMDAwMDAwMDAwIiwibmJmIjoiMTU2ODMzNjQ2NiIsImV4cCI6IjE1NjgzNTgwNjYiLCJlbmRwb2ludHVybCI6ImpwOTM1MkZTbnV1c3ZjbmJLVVUyTGFqaWd0V1hSUG5tY2RoVkdWeWJ3aEk9IiwiZW5kcG9pbnR1cmxMZW5ndGgiOiIxMjEiLCJpc2xvb3BiYWNrIjoiVHJ1ZSIsImNpZCI6Ik1ESmhOVEF6T1dZdFpqQmhPQzB3TURBd0xUTm1NREF0TVRCaU9HTmlPVGc0WVRWbSIsInZlciI6Imhhc2hlZHByb29mdG9rZW4iLCJzaXRlaWQiOiJNREE1Tm1aaU16Y3ROV1F6WWkwME5UWTVMV0kyTm1NdFlqZzBPVFV4WkRjME9ESXoiLCJzaWduaW5fc3RhdGUiOiJbXCJrbXNpXCJdIiwibmFtZWlkIjoiMCMuZnxtZW1iZXJzaGlwfGtlbHZpbi5mZXJyZWlyYTNAc2VuYWlzcC5lZHUuYnIiLCJuaWkiOiJtaWNyb3NvZnQuc2hhcmVwb2ludCIsImlzdXNlciI6InRydWUiLCJjYWNoZWtleSI6IjBoLmZ8bWVtYmVyc2hpcHwxMDAzMDAwMDk0Njg5MzlmQGxpdmUuY29tIiwidHQiOiIwIiwidXNlUGVyc2lzdGVudENvb2tpZSI6IjMifQ.VElNTjNONXpGazV1dFhWRllqSmhHSEUwWWw3RTFseS9HcUt6UTJ5R2J6TT0&encodeFailures=1&srcWidth=&srcHeight=&width=1920&height=897&action=Access");
//			String imgDuh = email.embed(urlImgDuh, "CUSTOMER");
//
//			URL urlImgPlayStore = new URL("https://brazilsouth1-mediap.svc.ms/transform/thumbnail?provider=spo&inputFormat=png&cs=fFNQTw&docid=https%3A%2F%2Fsesisenaispedu.sharepoint.com%3A443%2F_api%2Fv2.0%2Fdrives%2Fb!N_uWADtdaUW2bLhJUddII4zCWNHetZ5PrqtXbllSo6U1DLnINVEjSrO0oALWjG5D%2Fitems%2F01UTUAIIG2FVIF2UFMIJHLXGLIJC5M4MFK%3Fversion%3DPublished&access_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAvc2VzaXNlbmFpc3BlZHUuc2hhcmVwb2ludC5jb21AYjEwNTFjNGItM2I5NC00MWFiLTk0NDEtZTczYTcyMzQyZmRkIiwiaXNzIjoiMDAwMDAwMDMtMDAwMC0wZmYxLWNlMDAtMDAwMDAwMDAwMDAwIiwibmJmIjoiMTU2ODMzNjQ2NiIsImV4cCI6IjE1NjgzNTgwNjYiLCJlbmRwb2ludHVybCI6ImpwOTM1MkZTbnV1c3ZjbmJLVVUyTGFqaWd0V1hSUG5tY2RoVkdWeWJ3aEk9IiwiZW5kcG9pbnR1cmxMZW5ndGgiOiIxMjEiLCJpc2xvb3BiYWNrIjoiVHJ1ZSIsImNpZCI6Ik1ESmhOVEF6T1dZdFpqQmhPQzB3TURBd0xUTm1NREF0TVRCaU9HTmlPVGc0WVRWbSIsInZlciI6Imhhc2hlZHByb29mdG9rZW4iLCJzaXRlaWQiOiJNREE1Tm1aaU16Y3ROV1F6WWkwME5UWTVMV0kyTm1NdFlqZzBPVFV4WkRjME9ESXoiLCJzaWduaW5fc3RhdGUiOiJbXCJrbXNpXCJdIiwibmFtZWlkIjoiMCMuZnxtZW1iZXJzaGlwfGtlbHZpbi5mZXJyZWlyYTNAc2VuYWlzcC5lZHUuYnIiLCJuaWkiOiJtaWNyb3NvZnQuc2hhcmVwb2ludCIsImlzdXNlciI6InRydWUiLCJjYWNoZWtleSI6IjBoLmZ8bWVtYmVyc2hpcHwxMDAzMDAwMDk0Njg5MzlmQGxpdmUuY29tIiwidHQiOiIwIiwidXNlUGVyc2lzdGVudENvb2tpZSI6IjMifQ.VElNTjNONXpGazV1dFhWRllqSmhHSEUwWWw3RTFseS9HcUt6UTJ5R2J6TT0&encodeFailures=1&srcWidth=&srcHeight=&width=1920&height=897&action=Access");
//			String imgPlayStore = email.embed(urlImgPlayStore, "Google Play Store");
//
//			
//			// embed the image and get the content id
////			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
////			String cid = email.embed(url, "Apache logo");
//					  
//			// set the html message
//			String msgHTML = "<div class=\"all\" style=\"width: 600px;min-height: 100%;background-color: #ee1616;\">\n" + 
//					"            <div class=\"center\" style=\"width: 600px;height: 600px;background-color: rgb(255, 255, 255);margin: auto;\">\n" + 
//					"                <header style=\"width: 100%;height: 16%;background-image: linear-gradient(to bottom right, #57bc90, #77c9d4);border-bottom: solid 5px #015249;box-sizing: border-box;\">\n" + 
//					"                    <div class=\"caixa-logo-duh\" style=\"width: 100%;height: 100%;box-sizing: border-box;padding-left: 20px;padding-top: 10px;display: flex;align-items: center;justify-content: flex-start;float: left;\">\n" + 
//					"                        <figure>\n" + 
//					"                            <img class=\"img-duh\" style=\"max-width: 300px;\" src="+imgDuh+" alt=\"DaUmHelp!\">\n" + 
//					"                        </figure>\n" + 
//					"                    </div>\n" + 
//					"                </header>\n" + 
//					"                <section class=\"conteudo\" style=\"width: 100%;height: 74%;background-color: #ffeeff;display: flex;align-items: center;flex-direction: column;flex-wrap: wrap;box-sizing: border-box;padding: 20px;\">\n" + 
//					"                    <div class=\"caixa-txt-email\" style=\"width: 80%;height: fit-content;box-sizing: border-box;padding: 20px;margin-bottom: 20px;\">\n" + 
//					"                        <h2 class=\"titulo-email\" style=\"font-family: 'Lexend Deca', sans-serif;\">Olá "+confirm.getNome()+"</h2>\n" + 
//					"                        <p class=\"texto-email\" style=\"font-family: 'Manjari', sans-serif;margin-top: 5px;\">Aqui está seu coódigo de confirmação de e-mail. Preencha este código de confirmação no aplicativo em seu celular para prosseguir com a seu cadastro:</p>\n" + 
//					"                    </div>\n" + 
//					"                    <div class=\"caixa-codigo\" style=\"width: 200px;height: 66px;background-color: #f7d2d2;border: dashed 3px #000000a9;border-radius: 5px;display: flex;align-items: center;justify-content: center ;box-sizing: border-box;padding-left: 20px;\">\n" + 
//					"                        <div class=\"codigo\" style=\"\">\n" + 
//					"                            <h3 class=\"txt-codigo\" style=\"font-family: 'Lexend Deca', sans-serif;font-size: 30px;letter-spacing: 20px;text-align: center;\">"+confirm.getCodigoConfirm()+"</h3>\n" + 
//					"                        </div>\n" + 
//					"                    </div>\n" + 
//					"                </section>\n" + 
//					"                <footer style=\"width: 100%;height: 10%;background-image: linear-gradient(to bottom right, #77c9d4, #57bc90);\">\n" + 
//					"                    <div class=\"caixa-info\" style=\"width: 50%;height: 100%;box-sizing: border-box;display: flex;align-items: flex-start;justify-content: flex-start;float: left;\">\n" + 
//					"                        <figure>\n" + 
//					"                            <img class=\"img-playstore\" style=\"max-width: 155px;max-height: inherit;\" src="+imgPlayStore+" alt=\"Disponível no Goople Play Store\">\n" + 
//					"                        </figure>\n" + 
//					"                    </div>\n" + 
//					"                    <div class=\"caixa-logo-be\" style=\"width: 50%;height: 100%;box-sizing: border-box;padding-right: 20px;display: flex;align-items: center;justify-content: flex-end;float: left;font-family: 'Space Mono', monospace;font-weight: normal;color: #ffffff;font-size: 18px;\"><p>Brace {Everything}</p></div>\n" + 
//					"                </footer>\n" + 
//					"            </div>\n" + 
//					"        </div>";
//			email.setHtmlMsg(msgHTML);
//					
//			// send the email
//			email.send();
//		} catch (EmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
