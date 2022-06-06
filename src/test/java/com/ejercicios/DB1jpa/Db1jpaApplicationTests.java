package com.ejercicios.DB1jpa;

import com.ejercicios.TE1.Domain.Persona_TE_1;
import com.ejercicios.TE1.Insfrastructutre.Controllers.Controllers_persona.Create_controller_persona_EJTE_1;
import com.ejercicios.TE1.Insfrastructutre.Controllers.Controllers_persona.Delete_controller_persona_EJTE_1;
import com.ejercicios.TE1.Insfrastructutre.Controllers.Controllers_persona.Find_controller_persona_EJTE_1;
import com.ejercicios.TE1.Insfrastructutre.Controllers.Controllers_persona.Update_controller_persona_EJTE_1;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Date;

//@RunWith(SpringRunner.class)
@SpringBootTest
class Db1jpaApplicationTests {

	@Test
	void contextLoads() {
	}
/*	Persona_TE_1 persona;


	@org.junit.Test
	public void testGetPersonSuccess() throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 8080 + "/EJTE_1";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		//Assert.assertEquals(true, result.getBody().contains("persons"));
	}
	@org.junit.Test
	public void testAddPerson() throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+ 8080 + "/EJTE_1/post";
		URI uri = new URI(baseUrl);
		persona = new Persona_TE_1();
		persona.setId_persona(1);
		persona.setUsuario("pepe");
		persona.setPassword("1234");
		persona.setName("pepe");
		persona.setSurname("pepe1 pepe2");
		persona.setCompany_email("pepe@gmail.com");
		persona.setPersonal_email("personaemail@gmail.com");
		persona.setActive(true);
		persona.setCreated(new Date(2008,12,12));
		persona.setTermination(new Date(2018,12,12));
		persona.setImagen_url("http...");

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Persona_TE_1> request = new HttpEntity<>(persona, headers);

		try
		{
			restTemplate.postForEntity(uri, request, String.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}
	@org.junit.Test
	public void testUpdatePerson() throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+ 8080 + "/EJTE_1/update/{id}";
		URI uri = new URI(baseUrl);
		persona = new Persona_TE_1();
		persona.setId_persona(1);
		persona.setUsuario("pepe");
		persona.setPassword("1234");
		persona.setName("pepe");
		persona.setSurname("pepe1 pepe2");
		persona.setCompany_email("pepe@gmail.com");
		persona.setPersonal_email("personaemail@gmail.com");
		persona.setActive(true);
		persona.setCreated(new Date(2008,12,12));
		persona.setTermination(new Date(2018,12,12));
		persona.setImagen_url("http...");
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Persona_TE_1> request = new HttpEntity<>(persona, headers);

		try
		{
			restTemplate.put(uri, String.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}
	@org.junit.Test
	public void testDeletePerson() throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+ 8080 + "/EJTE_1/delete/{id}";
		URI uri = new URI(baseUrl);
		persona = new Persona_TE_1();
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Persona_TE_1> request = new HttpEntity<>(persona, headers);

		try
		{
			restTemplate.delete(String.valueOf(uri), persona);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}
	@org.junit.Test
	public void testFindPerson() throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+ 8080 + "/EJTE_1/find/getid/{id}";
		URI uri = new URI(baseUrl);
		persona = new Persona_TE_1();
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Persona_TE_1> request = new HttpEntity<>(persona, headers);

		try
		{
			restTemplate.getForEntity(uri, Persona_TE_1.class);
			Assert.fail();
		}
		catch(HttpClientErrorException ex)
		{
			//Verify bad request and missing header
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}
 */

}
