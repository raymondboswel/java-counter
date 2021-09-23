package com.raymond.touchsides;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Counter counter = new Counter();
        System.out.println("Please enter the path to the file that you would like to analyze.");
        Scanner scan = new Scanner(System.in);
        String userPath = scan.next();
        System.out.println("You have entered " + userPath);
        Path path = Paths.get(userPath);

        String[] words = FileReader.getWords(path);
        counter.countWords(words);

        String mostFrequent = String.format("Most frequent word: %s occurred %d times", counter.mostFrequentWord, counter.mostFrequentCount);
        String mostFrequent7Letter = String.format("Most frequent 7 character word: %s occurred %d times", counter.mostFrequent7LetterWord, counter.mostFrequent7LetterCount);

        if(counter.mostFrequentCount == 0) {
            System.out.println("There are no words in the data set.");
        } else {
            System.out.println(mostFrequent);
        }

        if(counter.mostFrequent7LetterCount == 0) {
            System.out.println("There are no 7 character words in the data set.");
        } else {
            System.out.println(mostFrequent7Letter);
        }

        try {
            ScrabbleWord bestWord = counter.bestScrabbleWord();
            String bestScrabbleWordOut = String.format("Highest scoring word(s) (according to Scrabble): %s with a score of %d", bestWord.word, bestWord.score);
            System.out.println(bestScrabbleWordOut);
        } catch(Exception e) {
            System.out.println("Failed to get the best scrabble word, was the text file empty?");
        }
    }
}
