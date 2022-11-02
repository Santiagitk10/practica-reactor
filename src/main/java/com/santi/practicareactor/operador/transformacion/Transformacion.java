package com.santi.practicareactor.operador.transformacion;

import com.santi.practicareactor.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {

    private static final Logger log = LoggerFactory.getLogger(Transformacion.class);

    //Map me sirve para hacer operaciones a los elementos del flujo, devolviendo un nuevo flujo
    //En este caso como el flujo es de elementos de la clase persona si se ve el resultado reflejado
    //luego de haber aumentado la edad con los setter. Es decir me desempaqueta el dato para 
    //manipularlo, y luego me lo vuelve a empaquetar en un flujo
    public void map(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

       /* Flux.fromIterable(personas)
                .map(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return p;
                })
                .subscribe(p -> log.info(p.toString()));*/

        //En este caso para yo poder sacar por consola el resultado esperado debo subscribirme al
        //nuevo flujo que genera Map y no al anterior ya que es inmutable
        //Map me pide como retorno el dato como tal
        Flux<Integer> fx = Flux.range(0,10);
        Flux<Integer> fx2 = fx.map(x -> x + 10);
        fx2.subscribe(x -> log.info("X :" + x));
    }

    //El flatMap me pide que retorne otro flujo de datos, no el dato como tal. Esto se utiliza
    //por ejemplo cuando en llamados a bases de datos, lo que yo estoy obteniendo son flujos de
    //la información encontrada, entonces tendía un flujo de flujos, por lo que requiero aplanarlo
    //para poder devolver un flujo de datos
    public void flatMap(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(2,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(p -> log.info(p.toString()));
    }

    //Me permite agrupar según una propiedad del objeto en este caso el ID
    //Collect List me devuelve el flux como una lista para poder manipularla
    public void groupBy(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Sebastian", 28));
        personas.add(new Persona(1,"Carolina", 27));
        personas.add(new Persona(3,"Gabriel", 33));

        Flux.fromIterable(personas)
                .groupBy(p -> p.getIdPersona())
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(x -> log.info(x.toString()));
    }

}
