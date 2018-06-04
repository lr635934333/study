package com.liuran.web.utils.analysisdoucument.documentloader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManDocumentAnalysis extends DocumentAnalysis {
    public ManDocumentAnalysis(String path) {
        super(path);
    }

    @Override
    public List<String> getAllDocument(List<String> lines) {
        return lines;
    }

    @Override
    public Set<String> suffix() {
        return new HashSet<>();
    }
}
