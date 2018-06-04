package com.liuran.web.utils;

import java.util.Collection;

public class StringUtils {
    public static String join(Collection<String> list, String separator){
        return org.apache.commons.lang.StringUtils.join(list, separator);
    }
}
