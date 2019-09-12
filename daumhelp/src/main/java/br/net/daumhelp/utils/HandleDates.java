package br.net.daumhelp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HandleDates {

    public static String dataToBrString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("dd/MM/yy HH:mm");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }

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
  
    public static String dataToString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }
    
    public static String dataHoraAtual() {
    	Date data = new Date();
    	String dataAtual = HandleDates.dataToString(data);
    	return dataAtual;
    }
}
