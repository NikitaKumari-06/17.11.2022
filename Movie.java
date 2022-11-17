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

//Movie Class

package com.hibernate.jpa.exam;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "movies")
public class Movie {
	//3.	The identifier for each class should be automatically generated
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title", nullable = false, length = 255)
	private String title;
	
	@Column(name = "year_of_release")
	private int yearOfRelease;
	
	//6.	Implememt @ManyToOne between Movie and Genre
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genre genre;
	
	//5.	Implememt @ManyToMany between the Actor and Movie entities
	@ManyToMany(mappedBy =  "movies")
	private List<Actor> actors = new ArrayList<Actor>();

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(int id, String title, int yearOfRelease, Genre genre, List<Actor> actors) {
		super();
		this.id = id;
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.genre = genre;
		this.actors = actors;
	}

	public Movie(String title, int yearOfRelease, Genre genre, List<Actor> actors) {
		super();
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.genre = genre;
		this.actors = actors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", yearOfRelease=" + yearOfRelease + ", genre=" + genre
				+ ", actors=" + actors + "]";
	}	
	
}
