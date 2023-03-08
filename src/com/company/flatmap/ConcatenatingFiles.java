package com.company.flatmap;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class ConcatenatingFiles implements Serializable {


    public static void main (String[] args) throws IOException {

        Path file1 = Paths.get ("src/com/company/flatmap/files/TomSawyer_01.txt");
        Path file2 = Paths.get ("src/com/company/flatmap/files/TomSawyer_02.txt");
        Path file3 = Paths.get ("src/com/company/flatmap/files/TomSawyer_03.txt");
        Path file4 = Paths.get ("src/com/company/flatmap/files/TomSawyer_04.txt");


        Stream < String > stream1 = Files.lines (file1);
        Stream < String > stream2 = Files.lines (file2);
        Stream < String > stream3 = Files.lines (file3);
        Stream < String > stream4 = Files.lines (file4);


//        System.out.println("Stream 1 : " + stream1.count());
//        System.out.println("Stream 2 : " + stream2.count());
//        System.out.println("Stream 3 : " + stream3.count());
//        System.out.println("Stream 4 : " + stream4.count());

        Stream <Stream <String>> streamOfStreams = Stream.of (stream1,stream2,stream3,stream4);
        //  System.out.println("#streams : " + streamOfStreams.count()); //4

        Stream <String> streamOfLines = streamOfStreams.flatMap (Function.identity());
        //     System.out.println("#lines : " + streamOfLines.count());

        Function <String, Stream <String>> lineSplitter = line -> Pattern.compile (" ").splitAsStream (line);

        Stream <String> streamOfWords =
                streamOfLines.flatMap (lineSplitter)
                        .map (String :: toLowerCase)
                        // .filter(word -> word.length() == 4)
                        .distinct ( );
        System.out.println ("#words : " + streamOfWords.count());

    }
}
