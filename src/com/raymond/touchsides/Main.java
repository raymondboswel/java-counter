package com.raymond.touchsides;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Counter counter = new Counter();
        System.out.println("Please enter the path to the file that you would like to analyze.");
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        System.out.println("You have entered " + s);
        //Path path = Paths.get("/home/raymond/IdeaProjects/word-counter/test.txt");
        Path path = Paths.get("/home/raymond/IdeaProjects/word-counter/war_and_peace.txt");

        Stream<String> lines = Files.lines(path);
        String data = lines
                .map(l -> l.toLowerCase(Locale.ROOT))
                .map(l -> l.replace(".", ""))
                .map(l -> l.replace(",", ""))
                .map(l -> l.replace("\"", "" ))
                .map(l -> l.replace("\'", "" ))
                .map(l -> l.replace("-", " " ))
                .map(l -> l.replace("—", " " ))
                .collect(Collectors.joining(" "));

        String[] words = data.split(" ");

        Arrays.stream(words).forEach(word -> {
            counter.updateWordCount(word);
        });

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

        lines.close();
    }
}
