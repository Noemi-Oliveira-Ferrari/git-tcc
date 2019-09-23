package br.net.daumhelp.recursos;
public class GerarCodEmail {

    public static int gerarCodigo() {

        int min = 1000;
        int max = 9999;

        return (int) (min+(Math.random() * (max - min)));

    }

}
