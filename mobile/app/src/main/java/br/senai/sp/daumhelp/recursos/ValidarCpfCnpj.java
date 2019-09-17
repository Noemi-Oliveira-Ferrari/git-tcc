package br.senai.sp.daumhelp.recursos;
import java.util.ArrayList;
import java.lang.*;

public class ValidarCpfCnpj {

	public static boolean calcCnpj(String cnpj){
		String cnpjSemDigito;
		String digitoOriginal;
		String digitoFinal = "";
		Integer multiplicador = 2;

		if((cnpj != "00.000.000/0000-00" &&
				cnpj != "11.111.111/1111-11" &&
				cnpj != "22.222.222/2222-22" &&
				cnpj != "33.333.333/3333-33" &&
				cnpj != "44.444.444/4444-44" &&
				cnpj != "55.555.555/5555-55" &&
				cnpj != "66.666.666/6666-66" &&
				cnpj != "77.777.777/7777-77" &&
				cnpj != "88.888.888/8888-88" &&
				cnpj != "99.999.999/9999-99") &&
				cnpj.length() >= 18){

			cnpj = cnpj.replaceAll("[./-]", "");
			digitoOriginal = cnpj.substring(12, 14);
			ArrayList<Integer> numCaracteres = new ArrayList<Integer>();
			ArrayList<Integer> multiplicados = new ArrayList<Integer>();
			Double restoDivisao = 0.0;
			Integer soma = 0;

			cnpjSemDigito = cnpj.substring(0, cnpj.length()-2);

			for(int h = 0; h < 2; h++){//executa a função 2 vezes., uma para achar cada digito
				int tamCnpj = cnpjSemDigito.length();
				for(int i = tamCnpj-1, j = 0; i >= 0; i--, j++){
					//i - para pegar os caractes do cnpj do último ao primeiro
					//j - para as posições dos arrays
					numCaracteres.add(Character.getNumericValue(cnpj.charAt(i)));
					multiplicados.add(numCaracteres.get(j)*multiplicador);
					multiplicador++;
					if(multiplicador > 9){
						multiplicador = 2;
					}
					soma += multiplicados.get(j);
				}
				restoDivisao = Double.valueOf(soma) % 11;
				if(restoDivisao < 2){
					digitoFinal += "0";
				}else{
					digitoFinal += String.valueOf((int)(11 - restoDivisao));
				}
				soma = 0;
				numCaracteres.clear();
				multiplicados.clear();
				cnpjSemDigito += digitoFinal;
				multiplicador = 2;
			}

			multiplicador = 0;
			cnpjSemDigito = null;
			if(digitoFinal.equals(digitoOriginal)){
				digitoOriginal = "";
				digitoFinal = "";
				return true;
			}else{
				digitoOriginal = "";
				digitoFinal = "";
				return false;
			}


		}else{
			return false;
		}
	}

	public static boolean calcCpf(String cpf){
		String cpfSemDigito;
		String digitoOriginal;
		String digitoFinal = "";
		Integer multiplicador = 10;

		if((cpf != "000.000.000-00" &&
				cpf != "111.111.111-11" &&
				cpf != "222.222.222-22" &&
				cpf != "333.333.333-33" &&
				cpf != "444.444.444-44" &&
				cpf != "555.555.555-55" &&
				cpf != "666.666.666-66" &&
				cpf != "777.777.777-77" &&
				cpf != "888.888.888-88" &&
				cpf != "999.999.999-99") &&
				cpf.length() >= 14){

			cpf = cpf.replaceAll("[.-]", "");
			digitoOriginal = cpf.substring(9, 11);
			ArrayList<Integer> numCaracteres = new ArrayList<Integer>();
			ArrayList<Integer> multiplicados = new ArrayList<Integer>();
			Double restoDivisao = 0.0;
			Integer soma = 0;

			cpfSemDigito = cpf.substring(0, cpf.length()-2);

			for(int h = 0; h < 2; h++){
				int tamCpf = cpfSemDigito.length();
				for(int i = 0; i < tamCpf; i++){
					numCaracteres.add(Character.getNumericValue(cpf.charAt(i)));
					multiplicados.add(numCaracteres.get(i)*multiplicador);
					multiplicador--;
					soma += multiplicados.get(i);
				}
				restoDivisao = Double.valueOf(soma) % 11;
				if(restoDivisao < 2){
					digitoFinal += "0";
				}else{
					digitoFinal += String.valueOf((int)(11 - restoDivisao));
				}
				soma = 0;
				numCaracteres.clear();
				multiplicados.clear();
				cpfSemDigito += digitoFinal;
				multiplicador = 11;
			}

			multiplicador = 0;
			cpfSemDigito = null;
			if(digitoFinal.equals(digitoOriginal)){
				digitoOriginal = null;
				digitoFinal = "";
				return true;
			}else{
				digitoOriginal = null;
				digitoFinal = "";
				return false;
			}
		}else{
			return false;
		}
	}
}
