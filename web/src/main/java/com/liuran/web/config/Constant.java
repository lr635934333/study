package com.liuran.web.config;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class Constant {
    public static Properties system ;
    public static Properties jdbc;
    static {
        try {
            system = PropertiesLoaderUtils.loadAllProperties("system.properties");
            jdbc = PropertiesLoaderUtils.loadAllProperties("jdbc.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
