package br.net.daumhelp.cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetCep{

	public String dados = "";
	
	public static String trazerCep(String cep) {
		String dados = "";
		try {
			URL url = new URL("https://viacep.com.br/ws/"+cep+"/json/");
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			InputStream dadosStream = conexao.getInputStream();
			InputStreamReader leitorStream = new InputStreamReader(dadosStream);
			BufferedReader bufferedReader = new BufferedReader(leitorStream);
			
			String registro;
			
			for(int i = 0; i < 11; i++) {
				registro = bufferedReader.readLine();
				dados += registro;
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dados;		
		
	}
}
