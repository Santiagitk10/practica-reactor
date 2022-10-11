package com.santi.practicareactor.operador.errores;

import com.santi.practicareactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ErrorOp {

    private static final Logger log = LoggerFactory.getLogger(ErrorOp.class);

    //Reintenta correr el código cuando pasa un error pero igual lanza la traza del error
    //Concatwith es para concatenar lo que estoy haciendo con error en el flujo
    public void retry(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .retry(1)
                .doOnNext(x -> log.info(x.toString()))
                .subscribe();
    }

    //En caso de que suceda un error me retorna lo que yo le indique
    public void errorReturn(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorReturn(new Persona(0,"Pepe", 34))
                .subscribe(x -> log.info(x.toString()));

    }

    //El resultado es el mismo que con onErrorReturn, pero acá se está manejando la excepción
    //se podría trabajar con los mensajes que vienen dentro del error
    public void errorResume(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorResume(e -> Mono.just(new Persona(0,"xyz",99)))
                .subscribe(x -> log.info(x.toString()));
    }

    public void errorMap(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("UN ERROR")))
                .onErrorMap(e -> new InterruptedException(e.getMessage()))
                .subscribe(x -> log.info(x.toString()));
    }

}
