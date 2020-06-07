package com.ank.reactivewebclient;

import com.ank.reactivewebclient.model.HelloResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Log4j2
@RequiredArgsConstructor
public class Client {

    private final WebClient webClient;

    @EventListener(ApplicationReadyEvent.class)
    public void ready(){

        var name = "Ankit";

        this.webClient
                .get()
                .uri("/greeting/{name}", name)
                .retrieve()
                .bodyToMono(HelloResponse.class)
                .subscribe(r -> log.info("Mono: " + r.getMessage()));

        this.webClient
                .get()
                .uri("/greetings/{name}", name)
                .retrieve()
                .bodyToFlux(HelloResponse.class)
                .subscribe(r -> log.info("Flux: " + r.getMessage()));
    }
}
