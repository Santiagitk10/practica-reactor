package com.santi.practicareactor.operador.matematico;

import com.santi.practicareactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Matematico {

    private static final Logger log = LoggerFactory.getLogger(Matematico.class);

    public void average(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(p -> log.info(p.toString()));

    }

    public void count(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .count()
                .subscribe(x -> log.info("Cantidad: " + x));

    }

    public void min(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(p -> log.info(p.get().toString()));

    }

    public void sum(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(x -> log.info("suma: " + x));

    }

    public void summarizing(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(x -> log.info("Resumen: " + x));

    }




}
