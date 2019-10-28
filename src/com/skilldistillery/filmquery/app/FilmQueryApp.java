package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}


	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		List<Film> films = new ArrayList<>();
		boolean flag = true;
		Film film;

		System.out.println("Welcome to the Film Query Database");
		while (flag) {
			System.out.println("\n****************** MAIN MENU ******************");
			System.out.println("\nEnter 1 to look up a film by its ID number.");
			System.out.println("Enter 2 to search for a film by a keyword.");
			System.out.println("Enter 3 to exit the application.");
			System.out.print("Please enter 1-3 now: \n>>");
			int selection = 0;
			try {
				selection = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Input mismatch! Restart program and enter 1-3 only.");
				break;

			}
			switch (selection) {
			case 1:
				System.out.println("Please enter a film id number: ");
				film = db.findFilmById(input.nextInt());
				if (film == null) {
					System.out.println("We did not find a matching film with that ID number.");
					System.out.println("Please try again.");
				} else {
					System.out.println(film.filmDisplay());
					System.out.println("\nEnter 1 to return to main menu.");
					System.out.println("Enter 2 to see film's full details.");
					int submenu = input.nextInt();
					switch (submenu) {
					case 1: continue;
					case 2: System.out.println(film.toString());
					}
				}
				break;
			case 2:
				System.out.println("Please enter keyword to search: ");
				films = db.findFilmBySearch(input.next());
				if (films.isEmpty()) {
					System.out.println("We did not find a matching film with that keyword.");
					System.out.println("Please try again.");
				} else {
					for (Film f : films) {
						System.out.println(f.filmDisplay());
					}
					System.out.println("\nEnter 1 to return to main menu.");
					System.out.println("Enter 2 to see film's full details.");
					int submenu = input.nextInt();
					switch (submenu) {
					case 1: continue;
					case 2: System.out.println(films.toString());
					}
					
				}
				break;
			case 3:
				System.out.println("Goodbye!");
				flag = false;
				break;
			default:
				System.out.println("\n*******************************");
				System.out.println("* That is not a valid entry.  *");
				System.out.println("* Press 1, 2, or 3 then press *");
				System.out.println("* return key to continue.     *");
				System.out.println("*******************************");

			}
		}

	}
}
