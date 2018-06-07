package com.liuran.hadoop.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    private static List<String> specialSymbols = new ArrayList<String>();
    static {
        specialSymbols.add("~");
        specialSymbols.add("@");
        specialSymbols.add("#");
        specialSymbols.add("$");
        specialSymbols.add("%");
        specialSymbols.add("*");
        specialSymbols.add("&");
        specialSymbols.add("(");
        specialSymbols.add(")");
        specialSymbols.add("{");
        specialSymbols.add("}");
        specialSymbols.add("[");
        specialSymbols.add("]");
        specialSymbols.add("+");
        specialSymbols.add("_");
        specialSymbols.add("?");
        specialSymbols.add("-");
        specialSymbols.add(";");
        specialSymbols.add(":");
        specialSymbols.add(",");
        specialSymbols.add(".");
        specialSymbols.add("=");
        specialSymbols.add("'");
        specialSymbols.add("<");
        specialSymbols.add(">");
        specialSymbols.add("\"");
        specialSymbols.add("\\");
        specialSymbols.add("|");
        specialSymbols.add("/");
    }

    public static String cancelSpecialSymbols(String value){
        for (String symbol : specialSymbols){
            if (value.contains(symbol)){
                value = value.replace(symbol, " ");
            }
        }
        return value;
    }

    public static String[] humpSpile(String value){
        if (!needHumpSplit(value)){
            return new String[]{value.toLowerCase()};
        }

        String lowValue = value.toLowerCase();
        StringBuilder tempValue = new StringBuilder();
        for (int i = 0; i < lowValue.length(); i++){
            if (lowValue.charAt(i) != value.charAt(i)){
                tempValue.append("_").append(lowValue.charAt(i));
            } else {
                tempValue.append(lowValue.charAt(i));
            }
        }

        return tempValue.toString().split("_");
    }

    private static boolean needHumpSplit(String value){
        String lowValue = value.toLowerCase();
        if (value.equals(lowValue)){
            return false;
        }

        String upValue = value.toUpperCase();

        if (upValue.equals(value)){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String value = "helloWordMyNameIsLiuran";
        for (String str : humpSpile(value)){
            System.out.println(str);
        }
    }
}
