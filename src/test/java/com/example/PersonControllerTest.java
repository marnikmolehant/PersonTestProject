package com.example;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Profile("h2")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersonTestProjectApplication.class)
@WebAppConfiguration
public class PersonControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private PersonRepository personRep;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		personRep.deleteAll();
		personRep.save(new Person("Marnik", 21));
		personRep.save(new Person("Thomas", 19));
		personRep.save(new Person("Maxime", 24));
		personRep.save(new Person("Sophie", 21));
		personRep.save(new Person("Arthur", 21));
		personRep.save(new Person("Lina", 19));
		personRep.save(new Person("Marnik", 20));
	}

	@Test
	public void getAllPeopleTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/people").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.persons[0].name").value("Marnik"))
				.andExpect(jsonPath("$.persons[1].name").value("Thomas"))
				.andExpect(jsonPath("$.persons[2].name").value("Maxime"))
				.andExpect(jsonPath("$.persons[3].name").value("Sophie"))
				.andExpect(jsonPath("$.persons[4].name").value("Arthur"))
				.andExpect(jsonPath("$.persons[5].name").value("Lina"))
				.andExpect(jsonPath("$.persons[6].name").value("Marnik"));
	}

	@Test
	public void getAllPeopleSortedByNameTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/people").param("sort", "name").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.persons[0].name").value("Arthur"))
				.andExpect(jsonPath("$.persons[1].name").value("Lina"))
				.andExpect(jsonPath("$.persons[2].name").value("Marnik"))
				.andExpect(jsonPath("$.persons[3].name").value("Marnik"))
				.andExpect(jsonPath("$.persons[4].name").value("Maxime"))
				.andExpect(jsonPath("$.persons[5].name").value("Sophie"))
				.andExpect(jsonPath("$.persons[6].name").value("Thomas"));
	}

	@Test
	public void getAllPeopleSortedByAgeTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/people").param("sort", "age").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.persons[0].name").value("Thomas"))
				.andExpect(jsonPath("$.persons[1].name").value("Lina"))
				.andExpect(jsonPath("$.persons[2].name").value("Marnik"))
				.andExpect(jsonPath("$.persons[2].age").value(20))
				.andExpect(jsonPath("$.persons[3].name").value("Marnik"))
				.andExpect(jsonPath("$.persons[3].age").value(21))
				.andExpect(jsonPath("$.persons[4].name").value("Sophie"))
				.andExpect(jsonPath("$.persons[5].name").value("Arthur"))
				.andExpect(jsonPath("$.persons[6].name").value("Maxime"));
	}

	@Test
	public void getByNameTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/people").param("name", "Marnik")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.persons[0].name").value("Marnik"));
	}

	@Test
	public void getByNameSortedByNameTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/people").param("name", "Marnik").param("sort", "name")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.persons[0].name").value("Marnik"));
	}

	@Test
	public void getByNameSortedByAgeTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/people").param("name", "Marnik").param("sort", "age")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.persons[0].name").value("Marnik"))
				.andExpect(jsonPath("$.persons[0].age").value(20))
				.andExpect(jsonPath("$.persons[1].name").value("Marnik"))
				.andExpect(jsonPath("$.persons[1].age").value(21));
	}

	@Test
	public void getByAgeTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/people").param("age", "21").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.persons[0].age").value(21))
				.andExpect(jsonPath("$.persons[1].age").value(21)).andExpect(jsonPath("$.persons[2].age").value(21));
	}

}
