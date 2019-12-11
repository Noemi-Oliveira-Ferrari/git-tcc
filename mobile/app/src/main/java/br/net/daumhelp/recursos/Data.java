package br.net.daumhelp.recursos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    public static String dataToBrString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }

    public static String dataHoraToBrString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }



    public static Date brStringToDate(String data) {
        Date stringData = null;
        try {
            SimpleDateFormat stringToDate = new SimpleDateFormat("dd/MM/yyyy");
            stringData = stringToDate.parse(data);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return stringData;
    }

    public static Date stringToDate(String data) {
        Date stringData = null;
        try {
            SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            stringData = stringToDate.parse(data);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return stringData;
    }

    public static Date usStringToDate(String data) {
        Date stringData = null;
        try {
            SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd");
            stringData = stringToDate.parse(data);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return stringData;
    }

    public static String dataToString(Date data) {

        DateFormat dateToString = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateToString.format(data);

        return dataFormatada;
    }

    public static String dataHoraAtual() {
        Date data = new Date();
        String dataAtual = Data.dataToString(data);
        return dataAtual;
    }

}
