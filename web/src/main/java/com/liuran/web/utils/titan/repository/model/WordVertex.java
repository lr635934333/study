package com.liuran.web.utils.titan.repository.model;

import com.liuran.web.utils.titan.repository.annotation.TitanCompositeKey;
import com.liuran.web.utils.titan.repository.annotation.TitanField;
import com.liuran.web.utils.titan.repository.annotation.TitanVertex;

/**
 * Created by lr on 2017/1/15.
 */
@TitanVertex
public class WordVertex extends TitanModel{
    @TitanCompositeKey
    @TitanField(name = "identifier")
    private String identifier;
    @TitanField(name = "word")
    private String word;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
