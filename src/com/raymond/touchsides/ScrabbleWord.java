package com.raymond.touchsides;

public class ScrabbleWord {
    int score;
    String word;

    ScrabbleWord(String word) {
        this.word = word;
        this.score = this.calculateScore(word);
    }

    private int calculateScore(String word) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            score = score + scrabbleLetterScore(c);
        }
        return score;
    }

    private int scrabbleLetterScore(char c) {
        switch (c) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'l':
            case 'n':
            case 's':
            case 't':
            case 'r':
                return 1;
            case 'd':
            case 'g':
                return 2;
            case 'b':
            case 'c':
            case 'm':
            case 'p':
                return 3;
            case 'f':
            case 'h':
            case 'v':
            case 'w':
            case 'y':
                return 4;
            case 'k':
                return 5;
            case 'j':
            case 'x':
                return 8;
            case 'q':
            case 'z':
                return 10;
            default:
                // Not a valid char
                return 0;
        }
    }
}
