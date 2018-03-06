package com.elliotb.Helpers;

public class EasyJSON {

    public static String convert(String resource, String val){
        return "{\"" + resource + "\": \"" + val + "\"}";
    }

}
