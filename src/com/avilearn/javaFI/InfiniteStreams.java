package com.avilearn.javaFI;

import com.avilearn.javaFI.pojo.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteStreams {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //4/28/2021

        Stream.iterate(0, i -> i + 1)
                .limit(10)
                .forEach(System.out::println);


        IntStream.iterate(0, i -> i + 1)
                .limit(10)
                .forEach(System.out::println);


        Stream.generate(new Random() :: nextInt)
                .limit(10)
                .forEach(System.out::println);

        // Flatmap

        Stream<String> a = Stream.of("hello","there");
        Stream<String> b = Stream.of("anyone","out there");

        //Stream<String> c = Stream.of(a,b); Stream of Streams , hence need to flatmap

       // Stream<Stream<String>> c = Stream.of(a,b);

        String c = Stream.of(a,b)
                .flatMap(e ->e)
                .collect(Collectors.toList()).toString();
        System.out.println(c); // [hello, there, anyone, out there]

        Path p = Paths.get("./words.txt");
        try (Stream<String> words = Files.lines(p);) {

            words.flatMap(line -> Arrays.stream(line.split(" ")))
            .collect(Collectors.toList())
            .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }


        // parallel Streams

        long startTime;
        long endTime;

        List<Employee> empList= new ArrayList<>();

        for(int i=0;i< 20;i++){
            empList.add(new Employee("john",54));
            empList.add(new Employee("smith",24));
            empList.add(new Employee("juliet",34));

        }

        startTime = System.nanoTime();
        System.out.println
                ( empList.parallelStream()
                        .filter(e -> e.getAge() >32)
                        .count());
        endTime = System.nanoTime();
        System.out.println(" time taken Parallel :" + (endTime-startTime)/1_000_000);
        startTime = System.nanoTime();
        System.out.println
       ( empList.stream()
                .filter(e -> e.getAge() >32)
                .count());
        endTime = System.nanoTime();
        System.out.println(" time taken :" + (endTime-startTime)/1_000_000);

       // List<Integer> list = List.of(1, 3,4,6,0,7,8);

        //avoid parallel streamwhen using stateful methods .

      /*  List<Integer> collect = list
                .parallelStream()
                .limit(4)
                .skip(2)*/

        System.out.println(Runtime.getRuntime().availableProcessors());
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","2");
       // System.out.println(ForkJoinPool.getCommonPoolParallelism());

        ForkJoinPool pool = new ForkJoinPool(2);

        long count = pool.submit(() ->
                empList.parallelStream()
        .filter( e -> e.getAge() > 25)
        .count()).get();

        System.out.println(count);


    }
}



