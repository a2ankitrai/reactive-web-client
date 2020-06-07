package com.ank.reactivewebclient;

import com.ank.reactivewebclient.model.HelloResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Log4j2
@RequiredArgsConstructor
public class ClientWithCircuitBreaker {

    private final WebClient webClient;
    private final ReactiveCircuitBreakerFactory circuitBreakerFactory;

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {

        var name = "Ankit";

        /** Service hedging example..**/
//        Flux<String> host1 = null; //todo
//        Flux<String> host2 = null; //todo
//        Flux<String> host3 = null; //todo
//
//        Flux<String> first = Flux.first(host1, host2, host3);

        ReactiveCircuitBreaker circuitBreaker = circuitBreakerFactory.create("greeting");

        Mono<String> httpCall = this.webClient
                .get()
                .uri("/greeting/{name}", name)
                .retrieve()
                .bodyToMono(HelloResponse.class)
                .map(HelloResponse::getMessage);

        circuitBreaker.run(httpCall, throwable -> Mono.just("Error occurred ReactiveCircuitBreaker"))
                .subscribe(message -> log.info("Mono: " + message));

    }
}