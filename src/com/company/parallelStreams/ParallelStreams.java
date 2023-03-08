package com.company.parallelStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String [] args) {


        // Set up number of threads will be used in a parallel stream
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");


        // Get Thread Name and apply it with two threads instead of one thread
        Stream.iterate("+", s -> s + "+")
                .parallel()
                .limit (6)
                .peek(s -> System.out.println(s + "processed in the thread " + Thread.currentThread().getName()))
                .forEach(System.out::println);

      List<String> collect  = Stream.iterate ("+", s -> s + "+")
              .parallel ( )
              .limit (1000)
              .toList ( );

        System.out.println("-> " + collect.size()); //1000



    }
}
