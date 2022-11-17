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

 */

//MainActor Class - Main class of Actor and ActorRepository class(7-10)

package com.hibernate.jpa.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;

public class MainActor {

	
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
			
			//7.	Saving objects of type Actor to the database
			for(Actor a:actors) {
				arep.save(a);;
			}
			
			List<Actor> actors1 = new ArrayList<Actor>();
			actors1.add(a1);
			actors1.add(a2);
			
			List<Actor> actors2 = new ArrayList<Actor>();
			actors2.add(a3);
			actors2.add(a4);
			
			//Saving objects of type Genre to the database
			Genre g1 = new Genre("Action", null);
			Genre g2 = new Genre("Comedy", null);
			
			GenreRepository grep = new GenreRepository(em);
			grep.save(g1);
			grep.save(g2);
			
			//Saving objects of type Movie to the database
			Movie m1 = new Movie("Shershah", 2022, g1, actors1);
			Movie m2 = new Movie("Student Of The Year", 2019, g2, actors2);
			
			MovieRepository mrep = new MovieRepository(em);
			mrep.save(m1);
			mrep.save(m2);
			
			//8.	Look for objects in the database of type Actor by id
			System.out.println("Actor detail with given id :");
			System.out.println();
			
			Optional<Actor> actorsById = arep.findById(1);
			System.out.println("Actors : " + actorsById);
			System.out.println("*****************************************************************");
			
			
			//9.	search for objects in the Actor type database that were born after a certain year 
			//(i.e. the year is a method parameter)
			System.out.println();
			System.out.println("Actor detail having greater born year than given year :");
			System.out.println();
			
			List<Actor> actorsByYear = arep.findAllBornAfter(1984);
			System.out.println("Actors : " + actorsByYear);
			System.out.println("******************************************************************");
			
			//10.	look for objects in the database of the Actor type, the names of which end with the
			// specified value of the String type object
			System.out.println();
			System.out.println("Actor detail having the names end with the specified value of the String type object :");
			System.out.println();
			
			List<Actor> actorsByEndValue  = arep.findAllWithLastNameEndsWith("a");
			System.out.println("Actors : " + actorsByEndValue );
			System.out.println("******************************************************************");
			
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
			if(factory != null) {
				factory.close();
			}
		}
	
	}	
}
