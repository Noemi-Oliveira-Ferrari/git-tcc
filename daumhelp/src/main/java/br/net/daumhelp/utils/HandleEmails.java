package br.net.daumhelp.utils;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.net.daumhelp.model.Confirmacao;

public class HandleEmails {
	
	public static boolean enviar(Confirmacao confirm) {	
		HtmlEmail email = new HtmlEmail();
//		email.setSmtpPort(465);
		email.setHostName("smtp.googlemail.com");
		try {
			email.setAuthenticator(new DefaultAuthenticator("brace.everything@gmail.com", "duh2019s2"));
			email.setSSLOnConnect(true);
			email.setFrom("brace.everything@gmail.com", "DaUmHelp!");
			email.addTo(confirm.getDestinatario(), confirm.getNome());
			email.setSubject("Código de Confirmação DaUmHelp!");

		
			String msgHTML =
					"<head>\n" + 
					"    <link href=\"https://fonts.googleapis.com/css?family=Lexend+Deca&display=swap\" rel=\"stylesheet\">\n" + 
					"    <link href=\"https://fonts.googleapis.com/css?family=Manjari&display=swap\" rel=\"stylesheet\">\n" + 
					"    <link href=\"https://fonts.googleapis.com/css?family=Space+Mono:700&display=swap\" rel=\"stylesheet\">\n" + 
					"</head>"+
					"<section class=\"conteudo\" style=\"padding:0px;margin:0px;  width: 100%;height: 300px;background-image: linear-gradient(to bottom right, #d7f1f5, #d2f1e4);box-sizing: border-box;\">  \n" + 
					"    <div class=\"caixa-txt-email\" style=\"padding:0px;margin:0px;  width: 500px;height: fit-content;box-sizing: border-box;margin-bottom: 15px;padding: 30px;\">  \n" + 
					"        <h2 class=\"titulo-email\" style=\"padding:0px;margin:0px;  color: #157581;font-family: 'Lexend Deca', sans-serif;\">Olá "+confirm.getNome()+"</h2>  \n" + 
					"        <p class=\"texto-email\" style=\"padding:0px;margin:0px;  font-size: 18px;font-family: 'Manjari', sans-serif;margin-top: 5px;\">Aqui está seu código de confirmação de e-mail. Preencha este código de confirmação no aplicativo em seu celular para prosseguir com seu cadastro:</p>  \n" + 
					"    </div>  \n" + 
					"    <div class=\"caixa-codigo\" style=\"padding:0px;margin:0px;  border-radius: 7px; border: dotted 3px #341212; margin: auto; width: 200px;height: 66px;background-image: linear-gradient(to bottom right, #77c9d4, #57bc90);box-sizing: border-box;\">  \n" + 
					"        <div class=\"codigo\" style=\"padding:0px;margin:0px;  width: 200px;height: 66px;padding-top: 9px;padding-left: 8px;\">  \n" + 
					"            <h3 class=\"txt-codigo\" style=\"padding:0px;margin:0px;  font-family: 'Lexend Deca', sans-serif;font-size: 30px;letter-spacing: 20px;text-align: center;color: #ffffff\">"+confirm.getCodigoConfirm()+"</h3>  \n" + 
					"        </div>  \n" + 
					"    </div>  \n" + 
					"</section>  \n" + 
					"<div class=\"caixa-logo-be\" style=\"padding:0px;margin:0px;  width: 50%;height: 100%;box-sizing: border-box;padding-right: 20px;display: flex;align-items: center;justify-content: flex-end;float: left;font-family: 'Space Mono', monospace;font-weight: normal;color: #ffffff;font-size: 18px;\"><p>Brace {Everything}</p></div>";
			email.setHtmlMsg(msgHTML);
			email.send();
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}
}
