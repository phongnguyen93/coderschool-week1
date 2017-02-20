package vn.com.phongnguyen93.wazzup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

/**
 * Adapter for movie backdrops or poster image list
 * Created by phongnguyen on 2/19/17.
 */

public class MovieImageAdapter extends RecyclerView.Adapter<MovieImageAdapter.ImageViewHolder> {
  private ArrayList<MovieImageList.MovieImage> images;
  private AdapterType adapterType;

  public enum AdapterType {
    BACKDROP_IMAGES, POSTER_IMAGES;
  }

  public MovieImageAdapter(AdapterType adapterType) {
    this.adapterType = adapterType;
  }

  public void setImages(ArrayList<MovieImageList.MovieImage> images) {
    if (images != null && images.size() > 0) {
      this.images = images;
      notifyDataSetChanged();
    }
  }

  @Override public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v;
    if (adapterType == AdapterType.BACKDROP_IMAGES) {
      v = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.movie_backdrop_image_item, parent, false);
    }
    else v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.movie_poster_image_item, parent, false);
    return new ImageViewHolder(v);
  }

  @Override public void onBindViewHolder(ImageViewHolder holder, int position) {
    holder.bindItem(images.get(position));
  }

  @Override public int getItemCount() {
    return images != null ? images.size() : 0;
  }

  public class ImageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image) ImageView image;

    public ImageViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }

    void bindItem(MovieImageList.MovieImage item) {
      String imageURL = ImageHandler.POSTER_IMAGE_URL + item.getFilePath();
      if (adapterType == AdapterType.BACKDROP_IMAGES) {
        imageURL = ImageHandler.BACKDROP_IMAGE_URL + item.getFilePath();
      }
      ImageHandler.getInstance()
          .load(imageURL)
          .fit()
          .placeholder(R.drawable.ic_movie_placeholder)
          .error(R.drawable.ic_movie_placeholder)
          .into(image);
    }
  }
}
