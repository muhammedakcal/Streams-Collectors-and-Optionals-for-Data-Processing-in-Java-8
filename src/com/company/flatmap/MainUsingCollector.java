package com.company.flatmap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainUsingCollector {


    public static void main ( String[] args ) throws IOException {

        Path shakespearePath = Paths.get ("src/com/company/flatmap/files/words.shakespeare.txt");
        Path ospdPath = Paths.get ("src/com/company/flatmap/files/ospd.txt");

        try (Stream < String > ospd = Files.lines (ospdPath);
             Stream < String > shakespeare = Files.lines (shakespearePath);) {

            Set < String > shakespeareWords = shakespeare.collect (Collectors.toSet ());

            Set < String > scrabbleWords = ospd.collect (Collectors.toSet ());

            System.out.println ("#words of Shakespeare : " + shakespeareWords.size ());
            System.out.println ("#words of Scrabble : " + scrabbleWords.size ());

            final int[] letterScores = {
                    //a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p,  q, r, s, t, u, v, w, x, y, z
                      1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
            };

            Function < String, Integer > score =
                    word -> word.toLowerCase ().chars ()
                            .map (letter -> letterScores[letter - 'a']).sum ();

            Map <Integer, List <String>> histoWordsByScore =
                    shakespeareWords.stream()
                            .filter(scrabbleWords::contains)
                            .collect(
                                    Collectors.groupingBy (score)
                            );

            System.out.println ("#histoWordsByScore: " + histoWordsByScore.size());

            histoWordsByScore.entrySet()
                    .stream()
                    .sorted(
                            Comparator.comparing (entry -> -entry.getKey())
                    )
                    .limit(3)
                    .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue ()));

            
        } catch (IOException ioe) {
            ioe.printStackTrace ();
        }
    }
}
