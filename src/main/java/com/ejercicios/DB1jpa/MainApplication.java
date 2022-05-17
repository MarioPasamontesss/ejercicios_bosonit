package com.ejercicios.DB1jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
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
