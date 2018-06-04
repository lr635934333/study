package com.liuran.web.utils.analysisdoucument.documentloader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ND on 2017/10/16.
 */
public class TextDocumentAnalysis extends DocumentAnalysis {
    public TextDocumentAnalysis(String path) {
        super(path);
    }

    @Override
    public List<String> getAllDocument(List<String> lines) {
        return lines;
    }

    @Override
    public Set<String> suffix() {
        Set<String> suffix = new HashSet<>();
        suffix.add("txt");
        return suffix;
    }
}
