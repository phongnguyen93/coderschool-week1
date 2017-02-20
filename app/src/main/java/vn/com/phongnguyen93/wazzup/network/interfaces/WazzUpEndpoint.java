package vn.com.phongnguyen93.wazzup.network.interfaces;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import vn.com.phongnguyen93.wazzup.Movie;
import vn.com.phongnguyen93.wazzup.MovieImageList;
import vn.com.phongnguyen93.wazzup.MovieNowPlaying;
import vn.com.phongnguyen93.wazzup.MovieTrailerList;

public interface WazzUpEndpoint {

  @GET("movie/now_playing") Call<MovieNowPlaying> getNowPlaying(@Query("api_key") String apiKey,
      @Query("language") String language, @Query("page") int page);

  @GET("genre/movie/list") Call<Movie.GenreList> getGenreList(@Query("api_key") String apiKey,
      @Query("language") String language);

  @GET("movie/{id}") Call<Movie> getMovieDetail(@Path("id") int movieId,
      @Query("api_key") String apiKey, @Query("language") String language);

  @GET("movie/{id}/images") Call<MovieImageList> getMovieImages(@Path("id") int movieId,
      @Query("api_key") String apiKey, @Query("language") String language,
      @Query("include_image_language") String imageLanguage);

  @GET("movie/{id}/videos") Call<MovieTrailerList> getMovieTrailer(@Path("id") int movieId,
      @Query("api_key") String apiKey, @Query("language") String language);
}