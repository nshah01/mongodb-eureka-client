package com.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.model.Person;

public interface PersonRepository extends MongoRepository<Person , String> {

	public Person findByFirstname(String firstname);
	
	public Person findByLastname(String lastname);
	
	//public Person insertPerson(Person person);
}
