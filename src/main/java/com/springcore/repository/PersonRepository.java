package com.springcore.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.springcore.entities.Person;

public interface PersonRepository extends Repository<Person,Long >{
	Person save(Person person);
	
	Optional<Person> findById(Long personId);
}
