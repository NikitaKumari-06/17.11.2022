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

11.	adding Movie records to the database
12.	searching Movie record by title
13.	searching for Movie record by id
14.	returning all Movie records
15.	removing one Movie record from the database.
16.	removing all Movie records from the database

 */

//MainMovie Class - Main class of Movie and MovieRepository class(11-16)
package com.hibernate.jpa.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;

public class MainMovie {
	public static void main(String[] args) {
		EntityManagerFactory factory = null;
		EntityManager em = null;
		
		try {
			factory = Persistence.createEntityManagerFactory("HibernateDemo");
			em = factory.createEntityManager();
			//Operations of Actor class and ActorRepository class 
			ActorRepository arep = new ActorRepository(em);
			
			Actor a1 = new Actor("Sidharth", "Malhotra", 1989, null);
			Actor a2 = new Actor("Kiara", "Advani", 1994, null);
			Actor a3 = new Actor("Aliya", "Bhatt", 1995, null);
			Actor a4 = new Actor("Varun", "Dhavan", 1990, null);
			
			List<Actor> actors = new ArrayList<Actor> ();
			
			actors.add(a1);
			actors.add(a2);
			actors.add(a3);
			actors.add(a4);
			
			for(Actor a:actors) {
				arep.save(a);;
			}
			
			List<Actor> actors1 = new ArrayList<Actor>();
			actors1.add(a1);
			actors1.add(a2);
			
			List<Actor> actors2 = new ArrayList<Actor>();
			actors2.add(a3);
			actors2.add(a4);
			
			Genre g1 = new Genre("Action", null);
			Genre g2 = new Genre("Comedy", null);
			
			GenreRepository grep = new GenreRepository(em);
			grep.save(g1);
			grep.save(g2);
			
			//11.	Adding Movie records to the database
			Movie m1 = new Movie("Shershah", 2022, g1, actors1);
			Movie m2 = new Movie("Student Of The Year", 2019, g2, actors2);
			
			MovieRepository mrep = new MovieRepository(em);
			mrep.save(m1);
			mrep.save(m2);
			
			
			//12.	Searching Movie record by title
			System.out.println("Movie detail with given Title :");
			System.out.println();
			
			Optional<Movie> moviesByTitle = mrep.findByTitle("Shershah");
			System.out.println("Movies : " + moviesByTitle);
			System.out.println("*****************************************************************");
			
			//13.	Searching for Movie record by id
			System.out.println("Movie detail with given id :");
			System.out.println();
			
			Optional<Movie> moviesById = mrep.findById(1);
			System.out.println("Movies : " + moviesById);
			System.out.println("*****************************************************************");
			
			//14.	returning all Movie records
			System.out.println("All Movie records :");
			System.out.println();
			
			List<Movie> allMovies = mrep.findAll();
			System.out.println("Movies : " + allMovies);
			System.out.println("*****************************************************************");
			
			//15.	removing one Movie record from the database.
			System.out.println("Removing One Movie :");
			System.out.println();
			
			 mrep.remove(m2);
			System.out.println("*****************************************************************");
			
			//16.	removing all Movie records from the database
			System.out.println("Removing All Movie :");
			System.out.println();
			
			 mrep.remove();;
			System.out.println("*****************************************************************");
			
			
		}
		
		catch(HibernateException e) {
			e.printStackTrace();
			System.out.println("Exception Hibernate");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception");
		}
		finally {
			
		}

	}
}
