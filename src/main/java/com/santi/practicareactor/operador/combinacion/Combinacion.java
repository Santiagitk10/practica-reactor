package com.santi.practicareactor.operador.combinacion;

import com.santi.practicareactor.model.Persona;
import com.santi.practicareactor.model.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {

    private static final Logger log = LoggerFactory.getLogger(Combinacion.class);

    public void merge(){
        List<Persona> personas1 = new ArrayList<>();
        personas1.add(new Persona(1,"Sebastian", 28));
        personas1.add(new Persona(2,"Carolina", 27));
        personas1.add(new Persona(3,"Gabriel", 33));

        List<Persona> personas2 = new ArrayList<>();
        personas2.add(new Persona(4,"Ron", 45));
        personas2.add(new Persona(5,"Tom", 78));
        personas2.add(new Persona(6,"Gabo", 23));

        Flux<Persona> fx1 = Flux.fromIterable(personas1);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.merge(fx1,fx2, fx3, fx1)
                .subscribe(p -> log.info(p.toString()));


    }

}
