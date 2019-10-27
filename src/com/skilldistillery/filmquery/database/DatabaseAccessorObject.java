package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String USER = "student";
	private static final String PASSWORD = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select * from film where film.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				Integer releaseYear = rs.getInt("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> actors = findActorsByFilmId(filmId);
				film = new Film(id, title, desc, releaseYear, langId, rentDur, length, repCost, rating, features, actors);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		String user = "student";
		String password = "student";
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select id, first_name, last_name from actor where id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, actorId);
			ResultSet actorResult = pst.executeQuery();
			if (actorResult.next()) {
				actor = new Actor();
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select * from actor join film_actor on actor.id = film_actor.actor_id where film_actor.film_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);	
			}
			rs.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

//	public List<Film> findFilmsByActorId(int actorId) {
//		List<Film> films = new ArrayList<>();
//		try {
//			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
//			sql += " rental_rate, length, replacement_cost, rating, special_features "
//					+ " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setInt(1, actorId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int filmId = rs.getInt(1);
//				String title = rs.getString(2);
//				String desc = rs.getString(3);
//				short releaseYear = rs.getShort(4);
//				int langId = rs.getInt(5);
//				int rentDur = rs.getInt(6);
//				double rate = rs.getDouble(7);
//				int length = rs.getInt(8);
//				double repCost = rs.getDouble(9);
//				String rating = rs.getString(10);
//				String features = rs.getString(11);
//				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
//						features);
//				films.add(film);
//			}
//			rs.close();
//			stmt.close();
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return films;
//	}

}
