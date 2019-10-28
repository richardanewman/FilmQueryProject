package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Category;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String USER = "student";
	private static final String PASSWORD = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("42");
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
				Integer releaseYear = rs.getInt("release_year");
				String rating = rs.getString("rating");
				String desc = rs.getString("description");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String features = rs.getString("special_features");
				Category category = getCategory(id);
				List<Actor> actors = findActorsByFilmId(filmId);
				film = new Film(id, title, desc, releaseYear, langId, rentDur, length, repCost, rating, features, category, actors);
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

	@Override
	public List<Film> findFilmBySearch(String keyword) {
		Film film = null;
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select * from film where description like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
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
				Category category = getCategory(id);
				List<Actor> actors = findActorsByFilmId(id);
				film = new Film(id, title, desc, releaseYear, langId, rentDur, length, repCost, rating, features, category, actors);
				films.add(film);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Language getLanguage(int langId) {
		Language lang = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select name from language where language.id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, langId);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				lang = new Language(name);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lang;
	}

	@Override
	public Category getCategory(int categoryId) {
		Category cat = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select category.name from category join film_category on film_category.category_id = category.id where film_category.film_id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, categoryId);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				cat = new Category(name);
			}
			rs.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}
}
