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

//Genre Class

package com.hibernate.jpa.exam;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
public class Genre {
	//3.	The identifier for each class should be automatically generated
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	
	//4.	Implement @OneToMany between Genre and Movie
	@OneToMany
	@JoinColumn(name = "genre_id")
	private List<Movie> movies;
	
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Genre(String name, List<Movie> movies) {
		super();
		this.name = name;
		this.movies = movies;
	}


	public Genre(int id, String name, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.movies = movies;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Movie> getMovies() {
		return movies;
	}


	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", movies=" + movies + "]";
	}

	
}
