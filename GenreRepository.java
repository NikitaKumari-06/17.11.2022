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

//GenreRepository Class - It has all the methods to operate on Genre Class.

package com.hibernate.jpa.exam;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class GenreRepository {
	
	private static EntityManager em;
	
	public GenreRepository(final EntityManager em ) {
		this.em = em;
	}
	
	//save method
	public  void save(final Genre genre) {
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			
			if(!tx.isActive()) {
				tx.begin();
			}
			
			em.persist(genre);
			tx.commit();
			
		} catch (Exception e) {

			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
	
	//remove method
	public void remove() {
		
		List<Genre> genres = findAll();
		
		for(Genre g : genres) {
			EntityTransaction tx = null;
			
			try {
				tx = em.getTransaction();
				
				if(!tx.isActive()) {
					tx.begin();
				}
				
				em.remove(g);
				tx.commit();
				
			}
			catch (Exception e) {

				if(tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			}
		}	
	}
	
	//findAll
	public List<Genre> findAll(){
		
		List<Genre> genres = em.createQuery("from Genre ", Genre.class).getResultList();				

		return genres;
	}
	//findById
	public  Optional<Genre> findById(int id) {
		Genre a = em.find(Genre.class, id);
						
		if(a != null)
			return Optional.of(a);
		else
			return Optional.empty();
	}
	//findAllByName
	public  Optional<Genre> findByName(String name) {
		Genre g= em.createQuery("select m from Genre m where m.name = :name", Genre.class)
						.setParameter("name", name).getSingleResult();
					
		if(g != null)
			return Optional.of(g);
		else
			return Optional.empty();
		}
	
}
