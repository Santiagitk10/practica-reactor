package com.santi.practicareactor;

import com.santi.practicareactor.model.Persona;
import com.santi.practicareactor.operador.combinacion.Combinacion;
import com.santi.practicareactor.operador.condicional.Condicional;
import com.santi.practicareactor.operador.creacion.Creacion;
import com.santi.practicareactor.operador.errores.ErrorOp;
import com.santi.practicareactor.operador.filtrado.Filtrado;
import com.santi.practicareactor.operador.matematico.Matematico;
import com.santi.practicareactor.operador.transformacion.Transformacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class PracticaReactorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PracticaReactorApplication.class);



	public void mono(){
		Mono.just(new Persona(1,"Santiago",31))
				.doOnNext(p -> log.info("[Reactor] Persona: " + p))
				.subscribe();
	}

	public void flux(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"Sebastian", 28));
		personas.add(new Persona(2,"Carolina", 27));
		personas.add(new Persona(3,"Gabriel", 33));

		//El método fromIterable me construye un Flux desde in iterable, por ejemplo una lista
		Flux.fromIterable(personas).subscribe(p -> log.info(p.toString()));
	}

	public void fluxMono(){
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"Sebastian", 28));
		personas.add(new Persona(2,"Carolina", 27));
		personas.add(new Persona(3,"Gabriel", 33));

		//El método fromIterable me construye un Flux desde in iterable, por ejemplo una lista
		Flux<Persona> fx = Flux.fromIterable(personas);
		//CollectList me devuelve un Mono con la lista adentro
		fx.collectList().subscribe(list -> log.info(list.toString()));

	}


	public static void main(String[] args) {
		SpringApplication.run(PracticaReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*		mono();*/
/*		flux();*/
/*		fluxMono();*/

	/*	Creacion creacion = new Creacion();*/
/*		creacion.range();*/
/*		creacion.repeat();*/

		/*Transformacion transformacion = new Transformacion();*/
/*		transformacion.map();*/
/*		transformacion.flatMap();*/
		/*transformacion.groupBy();*/

/*		Filtrado filtrado = new Filtrado();*/
/*		filtrado.filter();*/
/*		filtrado.distinct();*/
/*		filtrado.take();*/
/*		filtrado.takeLast();*/
/*		filtrado.skip();*/
/*		filtrado.skipLast();*/

/*		Combinacion combinacion = new Combinacion();
		combinacion.merge();*/

/*		ErrorOp error = new ErrorOp();*/
/*		error.retry();*/
/*		error.errorReturn();*/
/*		error.errorResume();*/
/*		error.errorMap();*/

/*		Condicional condicional = new Condicional();*/
/*		condicional.defaultIfEmpty();*/
/*		condicional.takeUntil();*/
/*		condicional.timeout();*/

		Matematico matematico = new Matematico();
/*		matematico.average();*/
/*		matematico.count();*/
/*		matematico.min();*/
/*		matematico.sum();*/
		matematico.summarizing();

	}
}



//CURSO YOUTUBE
//https://www.youtube.com/watch?v=UMgaV9fhLm8&list=PLvimn1Ins-41pwh18gh_ZkxPOkrEEhXz6