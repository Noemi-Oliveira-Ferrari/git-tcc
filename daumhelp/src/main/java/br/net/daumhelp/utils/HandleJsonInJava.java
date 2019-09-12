package br.net.daumhelp.utils;

import com.google.gson.Gson;

import br.net.daumhelp.model.EnderecoCep;

public class HandleJsonInJava {
	
	public static EnderecoCep convertEnderecoJsonToJavaObject(String json) {
		Gson gson = new Gson();
		EnderecoCep enderecoCep = gson.fromJson(json, EnderecoCep.class);
		System.out.println(enderecoCep);
		return enderecoCep;
	}
	
//	public static String convertEnderecoToJson(Endereco endereco) {
//		Gson gson = new Gson();
//		String jsonEndereco = gson.toJson(endereco);
//		return jsonEndereco;
//	}
	
}
