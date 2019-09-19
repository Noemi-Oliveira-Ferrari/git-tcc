package br.senai.sp.daumhelp.recursos;

import java.util.ArrayList;
import java.lang.*;

public class ValidarCpf{

	private static Integer multiplicador = 10;
	private static String cpfSemDigito;
    private static String digitoOriginal;
    private static String digitoFinal = "";
	
	public static boolean calc(String cpf){
		Integer multiplicador = 10;
    	cpf = cpf.replaceAll("[.-]", "");
    	digitoOriginal = cpf.substring(9, 11);
    	ArrayList<Integer> numCaracteres = new ArrayList<Integer>();
    	ArrayList<Integer> multiplicados = new ArrayList<Integer>();
    	Double restoDivisao = 0.0;
        Integer soma = 0;
    	
		cpfSemDigito = cpf.substring(0, cpf.length()-2);

    	for(int h = 0; h < 2; h++){
			int tam = cpfSemDigito.length();
        	for(int i = 0; i < tam; i++){
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
    	
    	if(digitoFinal.equals(digitoOriginal)){
    	    multiplicador = 0;
            cpfSemDigito = null;
            digitoOriginal = null;
            digitoFinal = "";
            return true;

    	}else{
            multiplicador = 0;
            cpfSemDigito = null;
            digitoOriginal = null;
            digitoFinal = "";
            return false;

    	}
	}
	
}
