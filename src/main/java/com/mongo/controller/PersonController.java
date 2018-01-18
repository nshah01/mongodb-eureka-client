package com.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.model.Person;
import com.mongo.repositories.PersonRepository;

@RestController
public class PersonController {
	
	@Autowired
	private PersonRepository personRepo;
	
	@RequestMapping(value = "/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value = "/getPerson/{firstname}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getFallback")
	public Person getPersonByFirstname(@PathVariable String firstname) {
		Person person = personRepo.findByFirstname(firstname);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + person);
		return person;
		
	}
	
	@RequestMapping(value = "/getPeople/{lastname}" , method = RequestMethod.GET)
	public Person getPersonByLastname(@PathVariable String lastname) {
		Person person = personRepo.findByLastname(lastname);
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<" + person);
		return person;
	}

	@RequestMapping(value = "/create" ,method = RequestMethod.POST)
	public Person createPerson(@RequestBody Person person) {
		return personRepo.save(person);
		
	}
	
	public Person getFallback() {
		Person person = personRepo.findByFirstname("Dummy");
		System.out.println("======================= Fallback Response ====================="+person);
		return person;
		
	}
}
