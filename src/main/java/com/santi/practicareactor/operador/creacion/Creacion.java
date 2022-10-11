package com.santi.practicareactor.operador.creacion;

import com.santi.practicareactor.PracticaReactorApplication;
import com.santi.practicareactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {

    private static final Logger log = LoggerFactory.getLogger(Creacion.class);

    //Creación de flujos vacíos
    public void empty(){
        Mono.empty();
        Flux.empty();
    }

    //Para crear flujos con rangos
    public void range(){
        Flux.range(0,3)
                .doOnNext(i -> log.info("" + i))
                .subscribe();
    }

    //Me repite las veces que desee cada elemento del flux o del mono
    public void repeat(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

/*        Flux.fromIterable(personas)
                .repeat(2)
                .subscribe(p -> log.info(p.toString()));*/

        Mono.just(new Persona(1,"Sebastian", 28))
                .repeat(3)
                .subscribe(p -> log.info(p.toString()));

    }

}
