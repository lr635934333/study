package com.liuran.web.utils.analysisdoucument.bean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ND on 2017/10/16.
 */
public class Word implements Comparable<Word>{
    private String word;
    private AtomicInteger count;
    public Word(String word){
        this.word = word;
        count = new AtomicInteger(0);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getCount() {
        return count.longValue();
    }

    public void incrementCount() {
        count.incrementAndGet();
    }

    @Override
    public String toString() {
        return word + ":" + count.longValue();
    }

    @Override
    public int compareTo(Word word) {
        if (word == null){
            return 1;
        }
        if (count.longValue() > word.getCount()){
            return -1;
        } if (count.longValue() == word.getCount()){
            return 0;
        }else {
            return 1;
        }
    }
}
