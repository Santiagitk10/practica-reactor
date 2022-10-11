package com.santi.practicareactor.operador.filtrado;

import com.santi.practicareactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {

    private static final Logger log = LoggerFactory.getLogger(Filtrado.class);


    public void filter(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .filter(p -> p.getEdad() > 28)
                .subscribe(p -> log.info(p.toString()));
    }

    //Para tipos de dato primitivo funciona la distinción, para objectos debe estar
    //el override de equals y hashcode
    public void distinct(){
        Flux.fromIterable(List.of(1,1,2,2))
                .distinct()
                .subscribe(p -> log.info(p.toString()));

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(1,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .distinct()
                .subscribe(p -> log.info(p.toString()));

    }

    public void take(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(1,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .take(2)
                .subscribe(p -> log.info(p.toString()));

    }

    public void takeLast(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(1,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .takeLast(1)
                .subscribe(p -> log.info(p.toString()));

    }

    public void skip(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .skip(1)
                .subscribe(p -> log.info(p.toString()));

    }

    public void skipLast(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .skipLast(2)
                .subscribe(p -> log.info(p.toString()));

    }




}
