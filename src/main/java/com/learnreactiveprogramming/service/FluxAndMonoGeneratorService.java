package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * depicts various ways of creating a flux.
 * Flux data will usually be produced/ published by a db or remote service
 **/
public class FluxAndMonoGeneratorService {

    public static final Supplier<Flux<String>> getFluxUsingJust = () -> Flux.just("Jim", "Joe", "Alex");

    public static void main(String[] args) {

        // invoking flux
        getFluxFromIterable()
                .subscribe(n -> {
                    System.out.println("name : " + n);
                });

        getFluxUsingJust.get()
                .subscribe(name -> {
                    System.out.println("name : " + name);
                });

        // invoking mono
        getMonoFlux()
                .subscribe(name -> {
                    System.out.println("name : " + name);
                });
    }

    public static Flux<String> getFluxFromIterable() {
        List<String> names = List.of("Jim", "Joe", "Alex");
        return Flux.fromIterable(names);
    }

    public static Flux<String> getFluxFromArray() {
        String[] names = {"Jim", "Joe", "Alex"};
        return Flux.fromArray(names);
    }

    public static Flux<String> getFluxFromStream() {
        List<String> names = List.of("Jim", "Joe", "Alex");
        Stream<String> stream = names.stream();
        return Flux.fromStream(stream);
    }

    // creating a mono
    public static Mono<String> getMonoFlux() {
        return Mono.just("Aryak");
    }

    public static Flux<String> getFlux_map() {
        List<String> names = List.of("Jim", "Joe", "Alex");
        return Flux.fromIterable(names)
                .map(String::toUpperCase);
    }

    public static Mono<String> namesMono_map_filter(int stringLength) {
        Mono<String> mono = Mono.just("alex");
        return mono.map(String::toUpperCase)
                .filter(e -> e.length() > stringLength);
    }

    public static Flux<String> getFluxWithFilterAndMap() {
        List<String> names = List.of("Jim", "Joe", "Alex");
        return Flux.fromIterable(names)
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase).log();
    }

    public static Flux<String> getFluxWithFilterAndFlatMap() {
        List<String> names = List.of("Jim", "Joe", "Alex");
        return Flux.fromIterable(names)
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .flatMap(FluxAndMonoGeneratorService::splitCharacters)
                .log();
    }

    private static Flux<String> splitCharacters(String name) {
        String [] chars =  name.split("");
        return Flux.fromArray(chars);
    }

    /**
     * depicts the asynchronous nature of flatMap
     */
    public Flux<String> getFluxWithFlatMapAsync() {

        Flux<String> namesFlux = Flux.just("Jim", "John", "Alex");
        int delay = new Random().nextInt(1000);
        return namesFlux
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                // the flux being provided to flatmap is unordered, since flatmap is async, it does not care about order
                .flatMap(s -> Flux.fromArray(s.split("")).delayElements(Duration.ofMillis(delay)))
                .log();
    }

    /**
     * depicts the synchronous nature of concatMap
     */
    public Flux<String> getFluxWithConcatMap() {

        Flux<String> namesFlux = Flux.just("Jim", "John", "Alex");
        int delay = new Random().nextInt(1000);
        return namesFlux
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                // the flux being provided to concatMap is unordered, since concatMap is sync, it preserves the order
                .concatMap(s -> Flux.fromArray(s.split("")).delayElements(Duration.ofMillis(delay)))
                .log();
    }

}
