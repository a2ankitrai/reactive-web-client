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
public class Client {

    private final WebClient webClient;

    @EventListener(ApplicationReadyEvent.class)
    public void ready(){

        var name = "Ankit";

//        this.webClient
//                .get()
//                .uri("/greeting/{name}", name)
//                .retrieve()
//                .bodyToMono(HelloResponse.class)
//                .map(HelloResponse::getMessage)
//                .retry(5)
//                // we can map the exception to another
//                .onErrorMap(throwable -> new RuntimeException("Server Side Error"))
//                // we can catch and return response for specific exception or define a generic handling
//                .onErrorResume(RuntimeException.class, exception -> Mono.just("Error "+ exception.toString()))
//                .subscribe(message -> log.info("Mono: " + message));

//        this.webClient
//                .get()
//                .uri("/greetings/{name}", name)
//                .retrieve()
//                .bodyToFlux(HelloResponse.class)
//                .map(HelloResponse::getMessage)
//                .onErrorResume(e -> Flux.just("Error occurred"))
//                .subscribe(r -> log.info("Flux: " + r));
    }
}
