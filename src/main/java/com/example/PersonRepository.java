package com.example;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long>,CrudRepository<Person, Long>{
	
	List<Person> findByName(String name);
	List<Person> findByName(String name, Sort sort);
	List<Person> findByAge(Integer age);
	List<Person> findByAge(Integer age, Sort sort);
	List<Person> findByNameAndAgeAllIgnoreCase(String name, Integer age);;
	
}
