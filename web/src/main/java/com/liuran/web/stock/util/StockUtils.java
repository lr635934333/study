package com.liuran.web.stock.util;

import com.liuran.web.stock.domain.StockDataDomain;
import com.liuran.web.utils.StringUtils;
import com.liuran.web.utils.net.HttpClientHelper;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class StockUtils {
    private static final String SINA_GET_URL = "http://hq.sinajs.cn/list=";
    public static List<StockDataDomain> getFormSina(Set<String> codes){
        if (CollectionUtils.isEmpty(codes)){
            return new ArrayList<>();
        }
        String url = SINA_GET_URL + StringUtils.join(codes, ",");

        String result = HttpClientHelper.sendGet(url, null, "GBK");

        return analysis(result, codes);
    }

    private static List<StockDataDomain> analysis(String data, Set<String> codes){
        String[] dataArray = data.split(";");
        List<StockDataDomain> result = new ArrayList<>();

        for (int i = 0; i < dataArray.length; i ++){
            String line = dataArray[i];
            String[] lineArray  = line.split("=");
            String code = lineArray[0].substring(lineArray[0].indexOf("hq_str_") + "hq_str_".length());
            String value = lineArray[1].replaceAll("\"", "");
            result.add(new StockDataDomain(code.toLowerCase(), value));
        }

        return result;
    }

    public static void main(String[] args) {
        Set<String> codes = new HashSet<>();
        codes.add("sh601006");
        codes.add("sz000938");
        getFormSina(codes);
    }
}
