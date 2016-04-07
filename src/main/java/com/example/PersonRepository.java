package com.example;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	ArrayList<Person> findByAge(int age);
	ArrayList<Person> findByAgeOrderByName(int age);
	ArrayList<Person> findByNameAndAgeAllIgnoreCase(String name, int age);
	
}
