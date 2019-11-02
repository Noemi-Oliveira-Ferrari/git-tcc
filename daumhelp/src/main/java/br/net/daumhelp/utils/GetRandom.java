package br.net.daumhelp.utils;

import java.util.ArrayList;

import org.jboss.jandex.Main;

public class GetRandom {
	public static String random() {
        int max = 6;
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		String codigo = "";
		for(int i = 0; i < max; i++){
		    numeros.add(GetRandom.getRandom());
		}
		for(int k = 1; k < max; k++){
    		for(int j = 0; j < k; j++){
    		    if(numeros.get(j) == numeros.get(k)){
    		        numeros.set(j, GetRandom.getRandom());
    		        j--;
    		        k--;
    		    }
    		}
		}
		for(Integer num : numeros){
		    codigo += String.valueOf(num);
		}
	    return codigo;
	}
	
	public static int getRandom(){
	    return (int)(Math.random() * 9 + 0);
	}
}
