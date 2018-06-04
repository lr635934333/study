package com.liuran.web.utils.analysisdoucument.output;

import com.liuran.web.utils.analysisdoucument.bean.Word;

import java.util.*;

/**
 * Created by ND on 2017/10/16.
 */
public class WordStatistics {
    private Map<String, Word> statisticsMap = new HashMap<>();
    private Set<String> filterWord = new HashSet<>();
    private int minWordLength = 4;

    public void setMinWordLength(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    public void setFilterWord(Set<String> filterWord) {
        this.filterWord = filterWord;
    }

    public void addWords(List<String> words){
        if (words == null || words.size() ==0){
            return;
        }

        for (String value : words){
            if (filter(value)){
                continue;
            }
            Word word = statisticsMap.get(value);
            if (word == null){
                word = new Word(value);
                statisticsMap.put(value, word);
            }
            word.incrementCount();
        }
    }

    public List<Word> display(int topN){
        List<Word> result = new ArrayList<>();
        List<Word> orderList = new ArrayList<>();
        orderList.addAll(statisticsMap.values());
        Collections.sort(orderList);
        int index = 0;
        for (Word word : orderList){
            result.add(word);
            index ++;
            if (index == topN){
                break;
            }
        }

        return result;
    }

    public List<Word> displayByTimes(int times){
        List<Word> result = new ArrayList<>();
        List<Word> sortList = new ArrayList<>();
        sortList.addAll(statisticsMap.values());
        Collections.sort(sortList);
        for (Word word : sortList){
            if (word.getCount() >= times){
                result.add(word);
            }
        }

        return result;
    }

    private boolean filter(String word){
        if (word.length() < minWordLength){
            return true;
        }
        return filterWord.contains(word);
    }
}
