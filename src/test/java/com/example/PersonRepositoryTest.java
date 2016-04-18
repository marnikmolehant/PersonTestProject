package com.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("h2")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersonTestProjectApplication.class)
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRep;
	
	@Test
	@Rollback
	@Transactional
	public void findByNameTest(){
		personRep.save(new Person("Marnik",21));
		personRep.save(new Person("Sergio",24));
		
		List<Person> repoList = personRep.findByName("Marnik");
		assertEquals(1, repoList.size());
	}
	
	@Test
	@Rollback
	@Transactional
	public void findByAgeTest(){
		personRep.save(new Person("Marnik",21));
		personRep.save(new Person("Maxime",21));
		personRep.save(new Person("Sergio",24));
		
		List<Person> repoList = personRep.findByAge(21);
		assertEquals(2, repoList.size());
	}
	
	@Test
	@Rollback
	@Transactional
	public void findByNameAndAgeAllIgnoreCaseTest(){
		personRep.save(new Person("Marnik",21));
		personRep.save(new Person("Marnik",22));
		personRep.save(new Person("Jonas",21));
		
		List<Person> repoList = personRep.findByNameAndAgeAllIgnoreCase("Marnik", 21);
		assertEquals(1, repoList.size());
	}
}
