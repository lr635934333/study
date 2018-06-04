package com.liuran.web.utils.analysisdoucument.documentloader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ND on 2017/10/16.
 */
public class JavaDocumentAnalysis extends DocumentAnalysis {
    private static final String start = "/**";
    private static final String end = "*/";
    private static final String docStart = "*";

    public JavaDocumentAnalysis(String path) {
        super(path);
    }

    @Override
    public List<String> getAllDocument(List<String> lines) {
        List<String> result = new ArrayList<>();
        boolean isDocument = false;
        for (String line : lines){
            if (line.startsWith(start)){
                isDocument = true;
                continue;
            }
            if (line.startsWith(end)){
                isDocument = false;
                continue;
            }

            if (isDocument){
                if (line.startsWith(docStart)){
                    result.add(line);
                }
            }

        }
        return result;
    }

    @Override
    public Set<String> suffix() {
        Set<String> suffix = new HashSet<>();
        suffix.add("java");
        return suffix;
    }
}
