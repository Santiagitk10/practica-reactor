package com.santi.practicareactor.operador.condicional;

import com.santi.practicareactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Condicional {

    private static final Logger log = LoggerFactory.getLogger(Condicional.class);

    //Para utilizar cuando podría tener una respuesta vacía. En este caso se mockea esa respuesta vacía
    public void defaultIfEmpty(){
        Mono.just(new Persona(1,"Mito", 29))
/*        Mono.empty()*/
/*        Flux.empty()*/
                .defaultIfEmpty(new Persona(0, "Default", 99))
                .subscribe(x -> log.info(x.toString()));
    }


    //Toma en orden en el flux hasta el primero que cumpla la condición y ya no toma más
    public void takeUntil(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .takeUntil(p -> p.getEdad() > 27)
                .subscribe(x -> log.info(x.toString()));
    }

    //Para lanzar un error de Timeout si la respuesta se me demora más del tiempo que puedo esperar
    //El hilo principal se debe dejar despierto por un tiempo mayor
    public void timeout() throws InterruptedException {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofSeconds(2))
                .onErrorReturn(new Persona(0,"delayed", 88))
                .subscribe(x -> log.info(x.toString()));

        Thread.sleep(10000);//Tiempo que permanece el hilo principal activo
    }

}
