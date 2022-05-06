package com.ejercicios.DB1jpa;

import com.ejercicios.DB1jpa.aplication.services.ServicePerson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	/*@Bean
	ServicePerson servicePer1(){
		ServicePerson servicePerson = new ServicePerson();
		return  servicePerson;
	}*/

}
