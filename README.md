### Film Query Application Project

### Week 7 Homework Project for Skill Distillery

### Overview

This program creates a command-line application that retrieves and displays film data. It uses a menu to allow the user to choose actions and submit query data in order to display relevant information from a fictional movie database.

### Technologies

Java Version 1.8, SQL, MySQL, Git, Github, Eclipse IDE, Atom

### Topics Applied

The primary topics applied from Week 7 at Skill Distillery were using Java Database Connectivity (JDBC), relational databases such as SQL and more specifically MySQL in this exercise, and Object-Relational Mapping (ORM).

### Lessons Learned

This project was straightforward. I did not get hung up too often. But it was new material, so I took my time going through each step slowly to ensure that I understood the process. It was actually a nice refresher after focusing on OCA preparation for two weeks, and it's exciting to finally start working with databases.

My biggest takeaway from this exercise was on how to use object-relational mapping to retrieve data from database tables. Breaking the program into a database accessor interface and then implementing those methods into a database accessor object or DAO was interesting. I immediately noticed how we built upon our previous work modeling objects and using those same concepts to construct the database entities we mapped from the database information. I definitely feel comfortable working with JDBC and find the challenges of mapping the data to be fun and interesting work. I am ardently looking forward to cleaning, mapping, and analyzing larger data sets in the weeks and years to come.

I regret not giving myself more time to complete all of the stretch goals before the deadline. I did complete two of the stretch goals but will definitely prioritize going forward to give myself more room to play with the program.


### How to Run

You must compile this program to run it. Current version will only work on the console of an IDE. Download or clone this repository to a local directory. Open Eclipse IDE (or your favorite Java compiler) and import the project under the File menu. Alternatively, you can clone the repository directly into your IDE via terminal. Once you have compiled it, click run and follow the prompts.

Clone with SSH:

git@github.com:richardanewman/FilmQueryProject.git

Clone with HTTPS:

https://github.com/richardanewman/FilmQueryProject.git



## Context

Below you will find a copy of the assignment's tasks. I am providing this context for anyone evaluating my code. This project builds on a lab written in class. We were guided through the first JDBC connection, along with the matching entity for that connection. The remainder of the lab work was performed independently. For better or worse, the code is my own. The only resources referenced were from the prior week's course material and my class notes. I am not a fan of copy and paste code because it does not aid in the learning process. I am attending this bootcamp so that I can thoroughly understand how to properly develop software using Java.

### The Following Task Overview and User Stories Were Provided by Skill Distillery

All JDBC code will be encapsulated in methods of the com.skilldistillery.filmquery.database.DatabaseAccessorObject class. As you need new database access methods, declare them first in the DatabaseAccessor interface, then implement them in DatabaseAccessorObject. Methods should return objects like Film, Actor, and List<Actor>, not String or List<String>.

All user input and display output will be in methods of com.skilldistillery.filmquery.app.FilmQueryApp (or additional application classes in that package, if you choose to create them.)


## User Stories

### User Story 1

The user is presented with a menu in which they can choose to:

  * Look up a film by its id.
  * Look up a film by a search keyword.
  * Exit the application.

### User Story 2

If the user looks up a film by id, they are prompted to enter the film id. If the film is not found, they see a message saying so. If the film is found, its title, year, rating, and description are displayed.

### User Story 3

If the user looks up a film by search keyword, they are prompted to enter it. If no matching films are found, they see a message saying so. Otherwise, they see a list of films for which the search term was found anywhere in the title or description, with each film displayed exactly as it is for User Story 2.

### User Story 4

When a film is displayed, its language (English,Japanese, etc.) is also displayed.

### User Story 5

When a film is displayed, the list of actors in its cast is displayed along with the title, year, rating, and description.

### Stretch Goals

### Goal 1

When a film is displayed, the user can choose from a submenu whether to:

  * Return to the main menu.
  * View all film details.
  * If they choose to view all details, they will see all column values of the film record they chose.

### Goal 2

When viewing film details, the user will see a list of all the film's categories (Family, Comedy, etc.) for the film.

### Goal 3

When viewing film details, the user will see a list of all copies of the film in inventory, with their condition.

### Goal 4

Write JUnit tests for DatabaseAccessorObject's methods.



### Grading

The application pushed to Github must satisfy all of the user stories without throwing any exceptions.
