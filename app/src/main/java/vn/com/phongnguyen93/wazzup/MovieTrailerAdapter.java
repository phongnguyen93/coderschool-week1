package vn.com.phongnguyen93.wazzup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phongnguyen on 2/20/17.
 */
public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MovierTrailerViewHolder> {
  private TrailerItemCallback callback;
  private static final String TAG = MovieTrailerAdapter.class.getSimpleName();

  public interface TrailerItemCallback{
    void onClick(MovieTrailerList.MovieTrailer trailer);
  }

  private List<MovieTrailerList.MovieTrailer> mData;

  public void setmData(ArrayList<MovieTrailerList.MovieTrailer> data){
    if(data!=null && data.size()>0){
      mData = data;
      notifyDataSetChanged();
    }
  }

  /**
   * Change {@link List} type according to your needs
   */
  public MovieTrailerAdapter(TrailerItemCallback callback) {
    this.callback = callback;
  }

  /**
   * Change the null parameter to a layout resource {@code R.layout.example}
   */
  @Override public MovierTrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailer_item, parent, false);
    return new MovierTrailerViewHolder(view);
  }

  @Override public void onBindViewHolder(MovierTrailerViewHolder holder, final int position) {
    holder.tvTitle.setText(mData.get(position).getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        callback.onClick(mData.get(position));
      }
    });
  }

  @Override public int getItemCount() {
    return mData != null ? mData.size() : 0;
  }

  public static class MovierTrailerViewHolder extends RecyclerView.ViewHolder {
    // include {@link View} components here
    @BindView(R.id.tvTrailerTitle) TextView tvTitle;

    public MovierTrailerViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}