package br.net.daumhelp.recursos;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

public class Base64Utils {

    public static String getTokenBody(String token) throws Exception {
        String[] split;
        try {
            split = token.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "error";
        }
        return split[1];
    }

    public static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
