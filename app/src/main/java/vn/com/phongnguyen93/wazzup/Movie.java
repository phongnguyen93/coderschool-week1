package vn.com.phongnguyen93.wazzup;

import java.util.ArrayList;

/**
 * Created by phongnguyen on 2/16/17.
 */

public class Movie {
  public static final String FIELD_MOVIE_ID = "id";

  private String poster_path;
  private boolean adult;
  private String overview;
  private String release_date;
  private int[] genre_ids;
  private int id;
  private String original_title;
  private String original_language;
  private String title;
  private ArrayList<Genre> genres;
  private String backdrop_path;
  private double popularity;
  private int vote_count;
  private boolean video;
  private double vote_average;
  private String status;
  private String tagline;

  public String getPosterPath() {
    return poster_path;
  }

  public void setPosterPath(String poster_path) {
    this.poster_path = poster_path;
  }

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getReleaseDate() {
    return release_date;
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }

  public int[] getGenreId() {
    return genre_ids;
  }

  public void setGenreId(int[] genre_ids) {
    this.genre_ids = genre_ids;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOriginalTitle() {
    return original_title;
  }

  public void setOriginalTitle(String original_title) {
    this.original_title = original_title;
  }

  public String getOriginalLanguage() {
    return original_language;
  }

  public void setOriginalLanguage(String original_language) {
    this.original_language = original_language;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBackdropPath() {
    return backdrop_path;
  }

  public void setBackdropPath(String backdrop_path) {
    this.backdrop_path = backdrop_path;
  }

  public double getPopularity() {
    return popularity;
  }

  public void setPopularity(double popularity) {
    this.popularity = popularity;
  }

  public int getVoteCount() {
    return vote_count;
  }

  public void setVoteCount(int vote_count) {
    this.vote_count = vote_count;
  }

  public boolean isVideo() {
    return video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  public double getVoteAverage() {
    return vote_average;
  }

  public void setVoteAverage(double vote_average) {
    this.vote_average = vote_average;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTagline() {
    return tagline;
  }

  public void setTagline(String tagline) {
    this.tagline = tagline;
  }

  public ArrayList<Genre> getGenres() {
    return genres;
  }

  public void setGenres(ArrayList<Genre> genres) {
    this.genres = genres;
  }

  public class Genre {
    private int id;
    private String name;

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
  }

  public class GenreList {
    private ArrayList<Genre> genres;

    public ArrayList<Genre> getGenres() {
      return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
      this.genres = genres;
    }
  }
}


