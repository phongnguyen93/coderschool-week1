package vn.com.phongnguyen93.wazzup;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Target;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {
  MovieListAdapter adapter;
  @BindView(R.id.lvMovieList) ListView lvMovieList;
  @BindView(R.id.MyToolbar) Toolbar toolbar;
  @BindView(R.id.pullRefresh) PullToRefreshView pullToRefreshView;
  private Target target;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
      @Override public void onRefresh() {
        getMovieList();
      }
    });

    MovieRepository.getInstance().getGenreList();
  }

  @Override protected void onResume() {
    super.onResume();
    getMovieList();
  }

  private void getMovieList() {
    MovieRepository.getInstance().getNowPlaying(new MovieRepository.MovieListCallback() {
      @Override public void onGetMovie(ArrayList<Movie> movies) {
        pullToRefreshView.setRefreshing(false);
        adapter = new MovieListAdapter(MainActivity.this, movies);
        lvMovieList.setAdapter(adapter);
      }
    });
  }
}
