package com.liuran.web.utils;

import org.springframework.util.StringUtils;

public class DoubleUtils {
    public static double parseDouble(String str){
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str.trim())){
            return 0;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
