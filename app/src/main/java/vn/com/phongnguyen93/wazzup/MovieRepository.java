package vn.com.phongnguyen93.wazzup;

import android.content.Context;
import java.util.ArrayList;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.phongnguyen93.wazzup.network.interfaces.WazzUpEndpoint;

/**
 * Created by phongnguyen on 2/16/17.
 */

public class MovieRepository {
  public static MovieRepository instance;
  private WazzUpEndpoint endpoint;
  private Context context;

  public static MovieRepository getInstance() {
    if (instance == null) {
      synchronized (MovieRepository.class) {
        if (instance == null) {
          instance = new MovieRepository();
        }
      }
    }
    return instance;
  }

  public void init(WazzUpEndpoint wazzUpEndpoint, Context context) {
    this.endpoint = wazzUpEndpoint;
    this.context = context;
  }

  public interface MovieListCallback {
    void onGetMovie(ArrayList<Movie> movies);
  }

  public interface MovieDetailCallback {
    void onGetMovie(Movie movie);

    void onGetMovieImages(MovieImageList imageList);

    void onGetMovieTrailer(MovieTrailerList trailerList);
  }

  public void getNowPlaying(final MovieListCallback callback) {
    endpoint.getNowPlaying(context.getString(R.string.api_key), Locale.getDefault().getLanguage(),
        1).enqueue(new Callback<MovieNowPlaying>() {
      @Override
      public void onResponse(Call<MovieNowPlaying> call, Response<MovieNowPlaying> response) {
        if (response.isSuccessful()) {
          ArrayList<Movie> results = response.body().getResults();
          if (results != null && results.size() > 0) {
            callback.onGetMovie(results);
          }
        }
      }

      @Override public void onFailure(Call<MovieNowPlaying> call, Throwable t) {

      }
    });
  }

  public void getGenreList() {
    endpoint.getGenreList(context.getString(R.string.api_key), Locale.getDefault().getLanguage())
        .enqueue(new Callback<Movie.GenreList>() {
          @Override
          public void onResponse(Call<Movie.GenreList> call, Response<Movie.GenreList> response) {
            if (response.isSuccessful()) {
              PreferencesUtility.getInstance(context)
                  .setObjectValue(PreferencesUtility.PRE_KEY_GENRE_LIST, response.body());
            }
          }

          @Override public void onFailure(Call<Movie.GenreList> call, Throwable t) {

          }
        });
  }

  public void getMovieDetail(int movieId, final MovieDetailCallback callback) {
    endpoint.getMovieDetail(movieId, context.getString(R.string.api_key),
        Locale.getDefault().getLanguage()).enqueue(new Callback<Movie>() {
      @Override public void onResponse(Call<Movie> call, Response<Movie> response) {
        if (response.isSuccessful()) {
          callback.onGetMovie(response.body());
        }
      }

      @Override public void onFailure(Call<Movie> call, Throwable t) {

      }
    });
  }

  public void getMovieImages(int movieId, final MovieDetailCallback callback) {
    endpoint.getMovieImages(movieId, context.getString(R.string.api_key),
        Locale.getDefault().getLanguage(), Locale.getDefault().getLanguage())
        .enqueue(new Callback<MovieImageList>() {
          @Override
          public void onResponse(Call<MovieImageList> call, Response<MovieImageList> response) {
            if (response.isSuccessful()) {
              callback.onGetMovieImages(response.body());
            }else
              callback.onGetMovieImages(null);
          }

          @Override public void onFailure(Call<MovieImageList> call, Throwable t) {

          }
        });
  }

  public void getMovieTrailers(int movieId, final MovieDetailCallback callback){
    endpoint.getMovieTrailer(movieId,context.getString(R.string.api_key),Locale.getDefault().getLanguage())
        .enqueue(new Callback<MovieTrailerList>() {
          @Override
          public void onResponse(Call<MovieTrailerList> call, Response<MovieTrailerList> response) {
            if(response.isSuccessful()){
              callback.onGetMovieTrailer(response.body());
            }else
              callback.onGetMovieTrailer(null);
          }

          @Override public void onFailure(Call<MovieTrailerList> call, Throwable t) {

          }
        });
  }
}
