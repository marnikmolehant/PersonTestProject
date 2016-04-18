package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonRepository personRep;

	@RequestMapping(value = "/people", method = RequestMethod.POST)
	public @ResponseBody PostResult addPerson(@RequestBody Person person) {
		personRep.save(person);
		PostResult result = new PostResult(person.getId());
		return result;
	}

	@RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
	public void deletePerson(@PathVariable long id) {
		personRep.delete(personRep.findOne(id));
	}

	@RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
	public Person getPerson(@PathVariable long id) {
		return personRep.findOne(id);
	}

	@RequestMapping("/")
	public String home() {
		return "testString";
	}

	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public @ResponseBody Result getPersonList(@RequestParam(required = false) Integer age,
			@RequestParam(required = false) String name, @RequestParam(required = false) String sort) {

		Iterable<Person> returnList = null;

		if (age == null && name == null && sort == null) {
			logger.debug("Requesting all people (age=null, name=null, sort=null)");
			returnList = personRep.findAll();
		}

		if (age != null && name == null && sort == null) {
			logger.debug("Requesting people with age = " + age + " (age=" + age + ", name=null, sort=null)");
			returnList = personRep.findByAge(age);
		}

		if (age == null && name != null && sort == null) {
			logger.debug("Requesting people with name = " + name + " (age=null, name=" + name + ", sort=null)");
			returnList = personRep.findByName(name);
		}

		if (age == null && name == null && sort != null) {
			if (sort.equalsIgnoreCase("name")) {
				logger.debug("Requesting all people sorted by name (age=null, name=null, sort=name)");
				returnList = personRep.findAll(new Sort(Sort.Direction.ASC, "name"));
			} else if (sort.equalsIgnoreCase("age")) {
				logger.debug("Requesting all people sorted by age (age=null, name=null, sort=age)");
				returnList = personRep.findAll(new Sort(Sort.Direction.ASC, "age"));
			}
		}
		if (age != null && name == null && sort != null) {
			if (sort.equalsIgnoreCase("name")) {
				logger.debug("Requesting people with age = " + age + "sorted by name (age=" + age
						+ ", name=null, sort=name)");
				returnList = personRep.findByAge(age, new Sort(Sort.Direction.ASC, "name"));
			} else if (sort.equalsIgnoreCase("age")) {
				logger.debug(
						"Requesting people with age = " + age + "sorted by age (age=" + age + ", name=null, sort=age)");
				returnList = personRep.findByAge(age, new Sort(Sort.Direction.ASC, "age"));
			}
		}
		if (age == null && name != null && sort != null) {
			if (sort.equalsIgnoreCase("name")) {
				logger.debug("Requesting people with name = " + name + " sorted by name (age=null, name=" + name
						+ ", sort=name)");
				returnList = personRep.findByName(name, new Sort(Sort.Direction.ASC, "name"));
			} else if (sort.equalsIgnoreCase("age")) {
				logger.debug("Requesting people with name = " + name + " sorted by age (age=null, name=" + name
						+ ", sort=age)");
				returnList = personRep.findByName(name, new Sort(Sort.Direction.ASC, "age"));
			}
		}

		Result results = new Result();
		for (Person person : returnList) {
			results.getPersons().add(person);
		}
		return results;
	}

}
