// package com.example;
//
// import javax.annotation.PostConstruct;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Profile;
// import org.springframework.stereotype.Component;
//
// @Profile("localdb")
// @Component
// public class DataBaseInit {
//
// @Autowired
// private PersonRepository personRep;
//
// @PostConstruct
// public void init(){
// personRep.save(new Person("Marnik", 21));
// personRep.save(new Person("Thomas", 19));
// personRep.save(new Person("Maxime", 24));
// personRep.save(new Person("Sophie", 21));
// personRep.save(new Person("Arthur", 21));
// personRep.save(new Person("Lina", 19));
// personRep.save(new Person("Marnik", 20));
// }
// }
