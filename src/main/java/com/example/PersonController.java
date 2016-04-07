package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRep;
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public void addPerson(@RequestBody Person person){
		personRep.save(person);
	}
	
	@RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable long id){
		personRep.delete(personRep.findOne(id));
	}
	
	@RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable long id){
		return personRep.findOne(id);
	}
	
	@RequestMapping("/")
    public String home() {
    	return "testString";
    }
	
	@RequestMapping("/db")
    public Iterable<Person> db() {
    	return personRep.findAll();
    }
	
	@RequestMapping(value = "/people", method = RequestMethod.GET)
    public Iterable<Person> byAge(@RequestParam int age) {
    	return personRep.findByAge(age);
    }
	
	@RequestMapping("/ageSorted/{age}")
    public Iterable<Person> byAgeSorted(@PathVariable int age) {
    	return personRep.findByAgeOrderByName(age);
    }
	
	@RequestMapping("/nameage/{name}/{age}")
    public Iterable<Person> byNameAge(@PathVariable String name, @PathVariable int age) {
    	return personRep.findByNameAndAgeAllIgnoreCase(name, age);
    }
	
}
