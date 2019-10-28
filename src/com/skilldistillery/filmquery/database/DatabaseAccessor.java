package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.entities.Language;

public interface DatabaseAccessor {
  public Film findFilmById(int filmId);
  public Language getLanguage(int langId);
  public List<Film> findFilmBySearch(String keyword);
  public Actor findActorById(int actorId);
  public List<Actor> findActorsByFilmId(int filmId);
}
