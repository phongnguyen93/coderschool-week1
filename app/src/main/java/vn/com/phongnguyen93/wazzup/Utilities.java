package vn.com.phongnguyen93.wazzup;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.util.ArrayList;

/**
 * Created by phongnguyen on 2/17/17.
 */

public class Utilities {

  public static int getScreenWidth(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.x;
  }

  public static int getScreenHeight(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.y;
  }

  public static GradientDrawable generateGradientDrawable(int[] colorList) {
    GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colorList);
    gd.setCornerRadius(0f);
    return gd;
  }

  public static String generateGenreString(@NonNull int[] genreIds, Context context) {
    String genreString = "";
    if (genreIds != null && genreIds.length > 0) {
      final Movie.GenreList genreList = (Movie.GenreList) PreferencesUtility.getInstance(context)
          .getObjectValue(PreferencesUtility.PRE_KEY_GENRE_LIST, Movie.GenreList.class);

      if(genreList!=null && genreList.getGenres()!=null && genreList.getGenres().size()>0){
        for (int i = 0; i < genreIds.length; i++) {
          for (Movie.Genre genre : genreList.getGenres()) {
            if (genreIds[i] == genre.getId()) {
              if (i == 0) {
                genreString = genreString.concat(genre.getName());
              } else {
                genreString = genreString.concat(", " + genre.getName());
              }
            }
          }
        }
      }
    }
    return genreString;
  }

  public static String generateGenreString(@NonNull ArrayList<Movie.Genre> genreIds) {
    String genreString = "";
    if (genreIds != null && genreIds.size() > 0) {

      for (int i = 0; i < genreIds.size(); i++) {
        if (i == 0) {
          genreString = genreString.concat(genreIds.get(i).getName());
        } else {
          genreString = genreString.concat(", " + genreIds.get(i).getName());
        }
      }
    }
    return genreString;
  }

}
