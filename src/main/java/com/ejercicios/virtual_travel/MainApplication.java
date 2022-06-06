package com.ejercicios.virtual_travel;

import com.ejercicios.SA2.Application.StorageProperties;
//import com.ejercicios.shared.security.JWTAuthorizationFilter;
import com.ejercicios.virtual_travel.shared.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.cloud.openfeign.EnableFeignClients;

/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;*/

@SpringBootApplication
//@EnableFeignClients
@EnableConfigurationProperties(StorageProperties.class)
//@EnableWebSecurity
public class MainApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/travel").permitAll()
				.antMatchers(HttpMethod.GET).permitAll()
				.antMatchers(HttpMethod.POST).permitAll()
				//.antMatchers(HttpMethod.POST, "/travel/Reserva/add").hasAnyRole( "ADMIN")
				//.antMatchers(HttpMethod.PUT, "/EJ3_S1/controlerPersona/updatePerson/{id}").hasAnyRole( "ADMIN")
				//.antMatchers(HttpMethod.DELETE, "/EJ3_S1/controlerPersona/delete/{id}").hasAnyRole( "ADMIN")
				.anyRequest().authenticated();
	}

}
