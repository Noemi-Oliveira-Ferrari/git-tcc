package br.net.daumhelp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HandleDates {

	//CONVERTE JAVA DATE UMA UM STRING COM FORMATO BR
    public static String dataToBrString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }

    //CONVERTE UMA STRING NO FORMATO EUA EM UM JAVA DATE
    public static Date stringToDate(String data) {
        Date stringData = null;
        try {
            SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
            stringData = stringToDate.parse(data);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return stringData;
    }
  
    //CONVERTE UM JAVA DATE EM UMA STRING COM FORMATO EUA
    public static String dataToString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }
    
    //RETORNA UMA STRING COM FORMTO EUA COM A DATA E HORA ATUAIS
    public static String dataHoraAtual() {
    	Date data = new Date();
    	String dataAtual = HandleDates.dataToString(data);
    	return dataAtual;
    }
    
    //RETORNA UMA STRING COM FORMATO BR COM A DATA E HORA ATUAIS
    public static String dataHoraAtualBr() {
    	Date data = new Date();
        DateFormat dateToString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dateToString.format(data);
        
    	return dataFormatada;
    }
}
