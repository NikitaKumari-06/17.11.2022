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

//MovieRepository Class - It has all the methods to operate on Movie Class.

package com.hibernate.jpa.exam;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MovieRepository {
	private static EntityManager em;
	
	public MovieRepository(final EntityManager em) {
		this.em = em;
	}
	
	//11.	adding Movie records to the database
	public  void save(final Movie movie) {
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			
			if(!tx.isActive()) {
				tx.begin();
			}
			
			em.persist(movie);
			tx.commit();
			
		} catch (Exception e) {

			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
	
	//12.	Searching Movie record by title
	public  Optional<Movie> findByTitle(String title) {
	Movie m = em.createQuery("select m from Movie m where m.title = :title", Movie.class)
					.setParameter("title", title).getSingleResult();
				
	if(m != null)
		return Optional.of(m);
	else
		return Optional.empty();
	}
			
	//13.	Searching for Movie record by id
	public  Optional<Movie> findById(int id) {
		Movie a = em.find(Movie.class, id);
						
		if(a != null)
			return Optional.of(a);
		else
			return Optional.empty();
	}
	
	//14.	returning all Movie records	
	public List<Movie> findAll(){
			
		List<Movie> movies = em.createQuery("from Movie ", Movie.class).getResultList();
						
		return movies;
	}
	
	//15.	removing one Movie record from the database.
	public void remove(final Movie movie) {
		EntityTransaction tx = null;
		
		try {
			tx = em.getTransaction();
			
			if(!tx.isActive()) {
				tx.begin();
			}
			
			em.remove(movie);
			tx.commit();
			
		} 
		catch (Exception e) {

			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}
	
	//16.	removing all Movie records from the database
	public void remove() {
		
		List<Movie> movies = findAll();
		
		for(Movie m : movies) {
			EntityTransaction tx = null;
			
			try {
				tx = em.getTransaction();
				
				if(!tx.isActive()) {
					tx.begin();
				}
				
				em.remove(m);
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

}
