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
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.googlemail.com");
//		email.setSmtpPort(465);
//		email.setAuthenticator(new DefaultAuthenticator("brace.everything@gmail.com", "duh2019s2"));
//		email.setSSLOnConnect(true);
//		try {
//			email.setFrom("brace.everything@gmail.com", "DaUmHelp!");
//			email.setSubject("Codigo de Confirmação DaUmHelp!");
//			email.setMsg(confirm.getCodigoConfirm());
//			email.addTo(confirm.getDestinatario());
//			email.send();
//		} catch (EmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
//		  Create the email message
		HtmlEmail email = new HtmlEmail();
//		email.setSmtpPort(465);
		email.setHostName("smtp.googlemail.com");
		try {
			email.setAuthenticator(new DefaultAuthenticator("brace.everything@gmail.com", "duh2019s2"));
			email.setSSLOnConnect(true);
			email.setFrom("brace.everything@gmail.com", "DaUmHelp!");
			email.addTo(confirm.getDestinatario(), "Kelvin");
			email.setSubject("Código de Confirmação DaUmHelp!");

		
			String msgHTML =
					"<section class=\"conteudo\" style=\"width: 500px;height: 74%;background-image: linear-gradient(to bottom right, #d7f1f5, #d2f1e4);box-sizing: border-box;padding-bottom: 20px;\">  \r\n" + 
					"     <div class=\"caixa-txt-email\" style=\"width: 500px;height: fit-content;box-sizing: border-box;padding: 20px;margin-bottom: 20px;\">  \r\n" + 
					"         <h2 class=\"titulo-email\" style=\"color: #157581;font-family: 'Lexend Deca', sans-serif;\">Olá "+confirm.getNome()+"!</h2>  \r\n" + 
					"         <p class=\"texto-email\" style=\"font-family: 'Manjari', sans-serif;margin-top: 5px;\">Aqui está seu coódigo de confirmação de e-mail. Preencha este código de confirmação no aplicativo em seu celular para prosseguir com a seu cadastro:</p>  \r\n" + 
					"     </div>  \r\n" + 
					"     <div class=\"caixa-codigo\" style=\"margin: auto;width: 200px;height: 66px;background-image: linear-gradient(to bottom right, #77c9d4, #57bc90);border: dashed 3px #000000a9;border-radius: 5px;display: flex;align-items: center;justify-content: center ;box-sizing: border-box;padding-left: 20px;\">  \r\n" + 
					"         <div class=\"codigo\">  \r\n" + 
					"             <h3 class=\"txt-codigo\" style=\"font-family: 'Lexend Deca', sans-serif;font-size: 30px;letter-spacing: 20px;text-align: center;color: #ffffff\">"+confirm.getCodigoConfirm()+"</h3>  \r\n" + 
					"         </div>  \r\n" + 
					"     </div>  \r\n" + 
					" </section>  \r\n" + 
					" <div class=\"caixa-logo-be\" style=\"width: 50%;height: 100%;box-sizing: border-box;padding-right: 20px;display: flex;align-items: center;justify-content: flex-end;float: left;font-family: 'Space Mono', monospace;font-weight: normal;color: #ffffff;font-size: 18px;\"><p>Brace {Everything}</p></div>";
			email.setHtmlMsg(msgHTML);
					
			// send the email
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
