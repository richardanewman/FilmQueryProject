package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	  System.out.println("Please enter a film id: ");
	  Film film = db.findFilmById(input.nextInt());
	  System.out.println(film);
	  System.out.println();
	  
	  System.out.println("Please enter a actor id: ");
	  Actor actor = db.findActorById(input.nextInt());
	  System.out.println(actor);
	  System.out.println();
	  
	  System.out.println("Please enter a film ID to get a list of actors in film: ");
	  List<Actor> actors = db.findActorsByFilmId(input.nextInt());
	  for (Actor star : actors) {
		System.out.println(star);
	}
  }
	  

}
