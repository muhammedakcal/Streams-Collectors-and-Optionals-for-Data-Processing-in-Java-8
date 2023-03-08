package com.company.spliterator;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingSpliterator {

    public static void main(String[] args) {

        Path path = Paths.get("src/com/company/spliterator/people.txt");

        try  {


            Spliterator<Person> peopleSpliterator = new PersonSpliterator(Files.lines(path).spliterator());

            Stream<Person> people = StreamSupport.stream(peopleSpliterator, false);

            people.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

