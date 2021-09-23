package com.raymond.touchsides;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    static String[] getWords(Path path) throws IOException {
        Stream<String> lines = Files.lines(path);
        // Using a single replaceAll with a regex matching the correct characters would
        // be more efficient.
        String data = lines
                .map(l -> l.toLowerCase(Locale.ROOT)
                        .replace(".", "")
                        .replace(",", "")
                        .replace("\"", "")
                        .replace("\'", "")
                        .replace("-", " ")
                        .replace("—", " ")
                )
                .collect(Collectors.joining(" "));

        lines.close();

        String[] words = data.split(" ");
        return words;
    }
}
