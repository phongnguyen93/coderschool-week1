package vn.com.phongnguyen93.wazzup;

import android.content.Context;
import android.widget.ImageView;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by phongnguyen on 2/17/17.
 */

public class ImageHandler {
  public static final String POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w342/";
  public static final String POSTER_LARGE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
  public static final String BACKDROP_IMAGE_URL = "https://image.tmdb.org/t/p/w780/";
  private static Picasso sPicasso;

  public static void initPicassoClient(Context context) {
      sPicasso = new Picasso.Builder(context).downloader(new OkHttpDownloader(context))
          .build();
  }
  public static Picasso getInstance() {
    return sPicasso;
  }


}
