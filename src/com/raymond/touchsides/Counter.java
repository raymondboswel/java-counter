package com.raymond.touchsides;

import java.util.Comparator;
import java.util.HashMap;

public class Counter {
    String mostFrequentWord;
    int mostFrequentCount;
    String mostFrequent7LetterWord;
    int mostFrequent7LetterCount;

    private HashMap<String, WordDetail> wordMap = new HashMap();

    void updateWordCount(String word) {
        WordDetail wordDetail;
        if (this.wordMap.containsKey(word)) {
            wordDetail = this.wordMap.get(word);
            wordDetail.count += 1;
        } else {
            wordDetail = new WordDetail();
            wordDetail.count = 1;
            wordDetail.length = word.length();
        }
        this.updateMostFrequentWord(word, wordDetail);
        this.wordMap.put(word, wordDetail);
    }

    // To get word frequency we have to iterate over all words,
    // but to get the best scrabble word, we only have to look at the
    // unique words, which is a much smaller set, so we do this after
    // we have the unique words available.
    ScrabbleWord bestScrabbleWord() {
        ScrabbleWord bestWord = this.wordMap.keySet().stream().map(word -> {
            ScrabbleWord scrabbleWord = new ScrabbleWord(word);
            return scrabbleWord;
        }).max(Comparator.comparingInt(scrabbleWord -> scrabbleWord.score)).orElseThrow();

        return bestWord;
    }

    private void updateMostFrequentWord(String word, WordDetail wordDetail) {
        if(wordDetail.count > this.mostFrequentCount) {
            this.mostFrequentWord = word;
            this.mostFrequentCount = wordDetail.count;
        }

        if(wordDetail.length == 7 && wordDetail.count > this.mostFrequent7LetterCount) {
            this.mostFrequent7LetterWord = word;
            this.mostFrequent7LetterCount = wordDetail.count;
        }
    }
}
