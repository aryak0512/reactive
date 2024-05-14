package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {

    @Test
    void test_create_flux_by_iterator() {

        // given
        Flux<String> flux = FluxAndMonoGeneratorService.getFluxFromIterable();

        // when
        StepVerifier.create(flux)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    void test_create_flux_by_just() {

        // given
        Flux<String> flux = FluxAndMonoGeneratorService.getFluxUsingJust.get();

        // when
        StepVerifier.create(flux)
                .expectNext("Jim")
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }

    @Test
    void test_create_flux_by_array() {

        // given
        Flux<String> flux = FluxAndMonoGeneratorService.getFluxFromArray();

        // when
        StepVerifier.create(flux)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    void test_create_flux_by_stream() {

        // given
        Flux<String> flux = FluxAndMonoGeneratorService.getFluxFromStream();

        // when
        StepVerifier.create(flux)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    void test_mono() {

        // given
        Mono<String> monoFlux = FluxAndMonoGeneratorService.getMonoFlux();

        // when
        StepVerifier.create(monoFlux)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }


    @Test
    void getFlux_map() {

        // given
        Flux<String> flux = FluxAndMonoGeneratorService.getFlux_map();

        // when
        StepVerifier.create(flux)
                .expectNext("JIM")
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }

    @Test
    void namesMono_map_filter() {

        // given
        Mono<String> monoFlux = FluxAndMonoGeneratorService.namesMono_map_filter(3);

        // when
        StepVerifier.create(monoFlux)
                .expectNext("ALEX")
                .expectComplete()
                .verify();
    }

    @Test
    void getFluxWithFilterAndMap() {

        // given
        Flux<String> flux = FluxAndMonoGeneratorService.getFluxWithFilterAndMap();

        // when
        StepVerifier.create(flux)
                .expectNext("ALEX")
                .verifyComplete();
    }

    @Test
    void getFluxWithFilterAndFlatMap() {

        // given
        Flux<String> flux = FluxAndMonoGeneratorService.getFluxWithFilterAndFlatMap();
        // when
        StepVerifier.create(flux)
                .expectNext("A", "L","E","X")
                .verifyComplete();
    }

    @Test
    void testGetFluxWithFlatMapAsync() {
        FluxAndMonoGeneratorService obj = new FluxAndMonoGeneratorService();
        // given
        Flux<String> flux = obj.getFluxWithFlatMapAsync();
        // when
        StepVerifier.create(flux)
                .expectNextCount(8)
                //.expectNext("J","O","H","N","A", "L","E","X")
                .verifyComplete();
    }

    @Test
    void testGetFluxWithConcatMap() {
        FluxAndMonoGeneratorService obj = new FluxAndMonoGeneratorService();
        // given
        Flux<String> flux = obj.getFluxWithConcatMap();
        // when
        StepVerifier.create(flux)
                .expectNext("J","O","H","N","A", "L","E","X")
                .verifyComplete();
    }

}
