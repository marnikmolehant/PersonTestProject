package com.example;

import java.util.ArrayList;
import java.util.List;

public class Result {


	private List<Person> persons;

	public Result() {
		super();
		persons = new ArrayList<>();
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person p) {
		this.persons.add(p);

	}

}
