package vn.com.phongnguyen93.wazzup;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import java.util.ArrayList;

/**
 * Created by phongnguyen on 2/17/17.
 */

public class MovieListAdapter extends ArrayAdapter<Movie> {
  private static final int NORMAL_MOVIE = 0;
  private static final int POPULAR_MOVIE = 1;

  public static class PopularMovieViewHolder {
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.btn_instant_play) ImageButton btnInstantPlay;
    @Nullable @BindView(R.id.imgBackDrop) KenBurnsView imgBackdrop;

    PopularMovieViewHolder(View view) {
      ButterKnife.bind(this, view);
    }

    void bindItem(final Movie item) {
      tvTitle.setText(item.getTitle());
      ImageHandler.getInstance()
          .load(ImageHandler.BACKDROP_IMAGE_URL + item.getBackdropPath())
          .fit()
          .into(imgBackdrop);
    }
  }

  public static class PortraitMovieViewHolder {
    private Context context;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvGenre) TextView tvGenre;
    @BindView(R.id.btn_instant_play) ImageButton btnInstantPlay;
    @Nullable @BindView(R.id.tvRating) TextView tvRating;
    @Nullable @BindView(R.id.diagonalLayout) DiagonalLayout diagonalLayout;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @Nullable @BindView(R.id.imgPoster) ImageView imgPoster;
    @Nullable @BindView(R.id.poster_layout) CardView posterLayout;
    @Nullable @BindView(R.id.rating_layout) CardView ratingLayout;
    @Nullable @BindView(R.id.imgBackDrop) ImageView imgBackdrop;
    @BindView(R.id.detail_layout) RelativeLayout detailLayout;
    @Nullable @BindView(R.id.root) RelativeLayout root;
    @BindView(R.id.tvHot) TextView tvHot;

    PortraitMovieViewHolder(View view, Context context) {
      ButterKnife.bind(this, view);
      int defaultMargin = (int) context.getResources().getDimension(R.dimen.space_medium);
      this.context = context;
      if (posterLayout != null) {
        posterLayout.getLayoutParams().width = Utilities.getScreenWidth(context) / 3;
        posterLayout.requestLayout();
      }

      RelativeLayout.LayoutParams detailParams = null, params = null;

      if (diagonalLayout != null) {
        RelativeLayout.LayoutParams dianoParam =
            new RelativeLayout.LayoutParams(Utilities.getScreenWidth(context) / 2,
                ViewGroup.LayoutParams.MATCH_PARENT);
        diagonalLayout.setLayoutParams(dianoParam);

        detailParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
        detailParams.addRule(RelativeLayout.RIGHT_OF, R.id.diagonalLayout);
        detailParams.setMargins(0, 0, defaultMargin * 2, 0);
      } else if (ratingLayout != null) {
        detailParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
        detailParams.addRule(RelativeLayout.ALIGN_LEFT, R.id.rating_layout);
        detailParams.setMargins(defaultMargin * 3, 0, defaultMargin * 2, 0);

        params = new RelativeLayout.LayoutParams(
            (int) context.getResources().getDimension(R.dimen.rating_button_size),
            (int) context.getResources().getDimension(R.dimen.rating_button_size));

        params.setMargins(defaultMargin + Utilities.getScreenWidth(context) / 3
                - ratingLayout.getLayoutParams().width / 2, defaultMargin, defaultMargin,
            defaultMargin * 2);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
      }

      if (params != null) ratingLayout.setLayoutParams(params);
      if (detailParams != null) detailLayout.setLayoutParams(detailParams);
    }

    void bindItem(final Movie item) {
      if (item.getVoteAverage() > 7) {
        tvHot.setVisibility(View.VISIBLE);
        if (root != null) {
          root.setBackground(
              ContextCompat.getDrawable(context, R.drawable.movie_item_land_background));
        }
        btnInstantPlay.setVisibility(View.VISIBLE);
      } else {
        tvHot.setVisibility(View.GONE);
        btnInstantPlay.setVisibility(View.GONE);
      }

      tvTitle.setText(item.getTitle());
      tvGenre.setText(Utilities.generateGenreString(item.getGenreId(), context));
      if (tvRating != null) tvRating.setText(String.valueOf(item.getVoteAverage()));
      tvReleaseDate.setText(item.getReleaseDate());
      tvOverview.setText(item.getOverview());
      if (imgPoster != null)

      {
        ImageHandler.getInstance()
            .load(ImageHandler.POSTER_IMAGE_URL + item.getPosterPath())
            .placeholder(R.drawable.ic_movie_placeholder)
            .fit()
            .into(imgPoster);
      }

      if (imgBackdrop != null)

      {
        ImageHandler.getInstance()
            .load(ImageHandler.BACKDROP_IMAGE_URL + item.getBackdropPath())
            .fit()
            .into(imgBackdrop);
      }
    }
  }

  private ArrayList<Movie> movies;

  public MovieListAdapter(Context context, ArrayList<Movie> movies) {
    super(context, 0, movies);
    if (movies != null && movies.size() > 0) {
      this.movies = movies;
    }
  }

  @NonNull @Override public View getView(int position, View convertView, ViewGroup parent) {
    int viewType = this.getItemViewType(position);
    final Movie movie = getItem(position);
    switch (viewType) {
      case NORMAL_MOVIE:
        View normalView = convertView;
        PortraitMovieViewHolder viewHolder = null;
        if (normalView == null) {
          normalView =
              LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
          viewHolder = new PortraitMovieViewHolder(normalView, getContext());
          normalView.setTag(viewHolder);
        } else {
          if (normalView.getTag() instanceof PortraitMovieViewHolder) {
            viewHolder = (PortraitMovieViewHolder) normalView.getTag();
          }
        }
        if (viewHolder != null) {
          viewHolder.bindItem(movie);
          normalView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
              Intent t = new Intent(getContext(), MovieDetailActivity.class);
              t.putExtra(Movie.FIELD_MOVIE_ID, movie.getId());
              t.putExtra("instantPlay",movie.getVoteAverage()>7.5);
              getContext().startActivity(t);
            }
          });
        }
        return normalView;
      case POPULAR_MOVIE:
        View popularView = convertView;
        PopularMovieViewHolder viewHolder1 = null;
        if (popularView == null) {
          popularView = LayoutInflater.from(getContext())
              .inflate(R.layout.movie_popular_item, parent, false);
          viewHolder1 = new PopularMovieViewHolder(popularView);
          popularView.setTag(viewHolder1);
        } else {
          if (popularView.getTag() instanceof PopularMovieViewHolder) {
            viewHolder1 = (PopularMovieViewHolder) popularView.getTag();
          }
        }
        if (viewHolder1 != null) {
          viewHolder1.bindItem(movie);
          popularView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
              Intent t = new Intent(getContext(), MovieDetailActivity.class);
              t.putExtra(Movie.FIELD_MOVIE_ID, movie.getId());
              t.putExtra("instantPlay",movie.getVoteAverage()>7.5);
              getContext().startActivity(t);
            }
          });
        }
        return popularView;
      default:
        return null;
    }
  }

  @Override public int getItemViewType(int position) {
    if (movies.get(position).getVoteAverage() >= 7.5) {
      return POPULAR_MOVIE;
    } else {
      return NORMAL_MOVIE;
    }
  }

  @Override public int getViewTypeCount() {
    return movies.size();
  }
}
