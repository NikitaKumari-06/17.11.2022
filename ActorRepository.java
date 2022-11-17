/*
 * Hibernate Exercises
1.	Create the following tables by using Hibernate JPA. (Don’t create tables by your own, let hibernate to do it.)
a.	actors
id – int
name – varchar(255)
last_name – varchar(255)
year_of_birth – int
b.	genres
id – int
name – varchar(255)
c.	movies
id – int
title – varchar(255)
year_of_release – int
genre_id int
d.	actors_to_movies (There is no class by this name)
actor_id – int
movie_id – int

2.	Create entity classes like Actor, Genre and Movie

3.	The identifier for each class should be automatically generated.

4.	Implement @OneToMany between Genre and Movie

5.	Implememt @ManyToMany between the Actor and Movie entities
6.	Implememt @ManyToOne between Movie and Genre
7.	Saving objects of type Actor to the database
8.	look for objects in the database of type Actor by id
9.	search for objects in the Actor type database that were born after a certain year (i.e. the year is a method parameter)
10.	look for objects in the database of the Actor type, the names of which end with the specified value of the String type object
11.	adding Movie records to the database
12.	searching Movie record by title
13.	searching for Movie record by id
14.	returning all Movie records
15.	removing one Movie record from the database.
16.	removing all Movie records from the database
*/

//ActorRepository Class - It has all the methods to operate on Actor Class.

package com.hibernate.jpa.exam;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ActorRepository {
	private static EntityManager em;
	
	public ActorRepository(final EntityManager em) {
		this.em = em;
	}
	
	//7.	Saving objects of type Actor to the database
	public  void save(final Actor actor) {
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			
			if(!tx.isActive()) {
				tx.begin();
			}
			
			em.persist(actor);
			tx.commit();
			
		} catch (Exception e) {

			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	//8.Look for objects in the database of type Actor by id
	public  Optional<Actor> findById(int id) {
		Actor a = em.find(Actor.class, id);
			
		if(a != null)
			return Optional.of(a);
		else
			return Optional.empty();
	}
		
	//9.	search for objects in the Actor type database that were born after a certain year 
	//(i.e. the year is a method parameter)
	public List<Actor> findAllBornAfter(int year){
		System.out.println("Year = " + year);
		List<Actor> actors = em.createQuery("select a from Actor a where a.yearOfBirth > :year", Actor.class)
						.setParameter("year", year).getResultList();
	
		return actors;
	}
		
	//10.	look for objects in the database of the Actor type, the names of which end with the
	// specified value of the String type object
	public List<Actor> findAllWithLastNameEndsWith(final String surnameEndsWith) {
		List<Actor> actors = em.createQuery("SELECT a FROM Actor a WHERE a.lastName LIKE :lastName", Actor.class)
		        .setParameter("lastName", "%" + surnameEndsWith)
		        .getResultList();
		    
		return actors;
	}
		
}
