package vn.com.phongnguyen93.wazzup;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.hedgehog.ratingbar.RatingBar;
import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.Alpha;
import su.levenetc.android.textsurface.animations.AnimationsSet;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Loop;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.SideCut;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Side;
import su.levenetc.android.textsurface.contants.TYPE;

public class MovieDetailActivity extends YouTubeFailureRecoveryActivity
    implements MovieRepository.MovieDetailCallback,MovieTrailerAdapter.TrailerItemCallback {

  @BindView(R.id.MyToolbar) Toolbar toolbar;
  @BindView(R.id.imgPoster) ImageView imgPoster;
  @BindView(R.id.imgBackDrop) ImageView imgBackDrop;
  @BindView(R.id.btn_play) ImageButton btnPlay;
  @BindView(R.id.tvTitle) TextView tvTitle;
  @BindView(R.id.tvGenre) TextView tvGenre;
  @BindView(R.id.tvOverview) TextView tvOverview;
  @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
  @BindView(R.id.ratingbar) RatingBar ratingBar;
  @BindView(R.id.tvRating) TextView tvRating;
  @BindView(R.id.poster_image_layout) LinearLayout imagePosterLayout;
  @BindView(R.id.backdrop_image_layout) LinearLayout backDropLayout;
  @BindView(R.id.rvPoster) RecyclerView rvPoster;
  @BindView(R.id.rvBackdrop) RecyclerView rvBackdrop;
  @BindView(R.id.tagTextSurface) TextSurface tsTag;
  @BindView(R.id.movie_header_layout) RelativeLayout headerLayout;
  @BindView(R.id.content_layout) RelativeLayout contentLayout;
  @BindView(R.id.video_content) LinearLayout videoContent;
  @BindView(R.id.playerView) YouTubePlayerView playerView;
  @BindView(R.id.rvTrailer) RecyclerView rvTrailer;
  @BindView(R.id.dragPanel) DraggableView draggableView;
  @BindView(R.id.root) ScrollView root;


  private YouTubePlayer player;
  private MovieImageAdapter posterAdapter;
  private MovieImageAdapter backdropAdapter;
  private MovieTrailerAdapter trailerAdapter;
  private boolean instantPlay;
  private int movieId;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_detail);
    ButterKnife.bind(this);

    if(getActionBar()!=null){
      getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    if (getIntent() != null) {
      movieId = getIntent().getIntExtra(Movie.FIELD_MOVIE_ID, 0);
      instantPlay = getIntent().getBooleanExtra("instantPlay",false);
    }

    initUI();
    //Get movie detail
    MovieRepository.getInstance().getMovieDetail(movieId, this);
  }

  @Override protected void onResume() {
    super.onResume();
    playerView.initialize(getString(R.string.youtube_api_key),this);
    //Get movie images (posters,backdrops) if available
    MovieRepository.getInstance().getMovieImages(movieId, this);
    MovieRepository.getInstance().getMovieTrailers(movieId,this);

  }

  /**
   * Init UI for this activity
   */
  private void initUI() {
    videoContent.setVisibility(View.GONE);
    //init adapter, layout manager, recycler view for movie poster image list
    posterAdapter = new MovieImageAdapter(MovieImageAdapter.AdapterType.POSTER_IMAGES);
    rvPoster.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    rvPoster.setAdapter(posterAdapter);

    //init adapter, layout manager, recycler view for movie backdrop image list
    backdropAdapter = new MovieImageAdapter(MovieImageAdapter.AdapterType.BACKDROP_IMAGES);
    rvBackdrop.setLayoutManager(
        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    rvBackdrop.setAdapter(backdropAdapter);

    trailerAdapter =  new MovieTrailerAdapter(this);
    rvTrailer.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    rvTrailer.setAdapter(trailerAdapter);

    playerView.getLayoutParams().height = Utilities.getScreenHeight(this)/3;
    playerView.requestLayout();

    draggableView.setDraggableListener(new DraggableListener() {
      @Override public void onMaximized() {
        if(player!=null) player.play();
      }

      @Override public void onMinimized() {
        showDetailView();
      }

      @Override public void onClosedToLeft() {
        if(player!=null) player.pause();
      }

      @Override public void onClosedToRight() {
        if (player!=null) player.pause();
      }
    });

  }

  @OnClick(R.id.btn_play) public void onPlayClick() {
    hideDetailView();

  }

  @Override protected void onStop() {
    super.onStop();
    playerView = null;
  }

  private void hideDetailView() {
    Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.slide_down);

    Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.slide_up);

    headerLayout.startAnimation(slide_up);
    contentLayout.startAnimation(slide_down);
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
      @Override public void run() {
        headerLayout.setVisibility(View.GONE);
        contentLayout.setVisibility(View.GONE);
        rvTrailer.setVisibility(View.VISIBLE);
        videoContent.setVisibility(View.VISIBLE);
        draggableView.setVisibility(View.VISIBLE);
      }
    },500);
    showTrailerView();
  }

  private void showDetailView(){
    Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.slide_down_from_top);

    final Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.slide_up_from_bottom);

    videoContent.setVisibility(View.GONE);
    draggableView.setVisibility(View.GONE);
    headerLayout.startAnimation(slide_down);
    contentLayout.startAnimation(slide_up);
    root.setEnabled(false);
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
      @Override public void run() {
        headerLayout.setVisibility(View.VISIBLE);
        root.setEnabled(true);

        root.fullScroll(ScrollView.FOCUS_UP);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
          @Override public void run() {
            contentLayout.setVisibility(View.VISIBLE);
          }
        },200);
      }
    },100);
  }

  private void showTrailerView(){

    Animation slide_left = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.slide_left);

    Animation slide_right = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.slide_right);

    rvTrailer.startAnimation(slide_right);
    playerView.startAnimation(slide_left);
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
      @Override public void run() {
        videoContent.setVisibility(View.VISIBLE);
        rvTrailer.setVisibility(View.VISIBLE);
        playerView.setVisibility(View.VISIBLE);


      }
    },500);
  }

  /**
   * Populate data of movie into view
   *
   * @param movie Movie object
   */
  private void invalidateMovieData(Movie movie) {
    tvTitle.setText(movie.getTitle());
    tvGenre.setText(Utilities.generateGenreString(movie.getGenres()));
    tvReleaseDate.setText(movie.getReleaseDate());
    tvOverview.setText(movie.getOverview());
    ratingBar.setStar((int) (movie.getVoteAverage() / 2));
    tvRating.setText(String.valueOf(movie.getVoteAverage()));

    final Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setTypeface(Typeface.createFromAsset(this.getAssets(), "VolteRounded-Regular.ttf"));
    Text txtTag = TextBuilder.create(movie.getTagline())
        .setPaint(paint)
        .setSize(20)
        .setAlpha(0)
        .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
        .setPosition(Align.SURFACE_CENTER)
        .build();
    tsTag.play(new Loop(new AnimationsSet(TYPE.SEQUENTIAL,
        ShapeReveal.create(txtTag, 3000, SideCut.show(Side.LEFT), false), Delay.duration(1500),
        Alpha.hide(txtTag, 500))));

    ImageHandler.getInstance()
        .load(ImageHandler.BACKDROP_IMAGE_URL + movie.getBackdropPath())
        .fit()
        .into(imgBackDrop);
    ImageHandler.getInstance()
        .load(ImageHandler.POSTER_IMAGE_URL + movie.getPosterPath())
        .placeholder(R.drawable.ic_movie_placeholder)
        .fit()
        .into(imgPoster);
  }

  /**
   * Load poster & backdrop image into views
   *
   * @param imageList MovieImageList object which contain poster image list & backdrop image list
   */
  private void loadImages(MovieImageList imageList) {
    if (imageList.getPosters() != null && imageList.getPosters().size() > 0) {
      imagePosterLayout.setVisibility(View.VISIBLE);
      posterAdapter.setImages(imageList.getPosters());
    } else {
      imagePosterLayout.setVisibility(View.GONE);
    }

    if (imageList.getBackdrops() != null && imageList.getBackdrops().size() > 0) {
      backDropLayout.setVisibility(View.VISIBLE);
      backdropAdapter.setImages(imageList.getBackdrops());
    } else {
      backDropLayout.setVisibility(View.GONE);
    }
  }

  @Override public void onGetMovie(Movie movie) {
    if (movie != null) {
      invalidateMovieData(movie);
    }
  }

  @Override public void onGetMovieImages(MovieImageList imageList) {
    if (imageList != null) {
      loadImages(imageList);
    }
  }

  @Override public void onGetMovieTrailer(MovieTrailerList trailerList) {
    if(trailerList!=null && trailerList.getResults()!=null && trailerList.getResults().size()>0){
      trailerAdapter.setmData(trailerList.getResults());
      if(player!=null){
        player.cueVideo(trailerList.getResults().get(0).getKey());
      }

      if(instantPlay){
        hideDetailView();
      }
    }
  }

  @Override protected YouTubePlayer.Provider getYouTubePlayerProvider() {
    return playerView;
  }

  @Override
  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
      boolean wasRestored) {
    if (!wasRestored) {
      player = youTubePlayer;
    }
  }

  @Override public void onClick(MovieTrailerList.MovieTrailer trailer) {
    if(player!=null && trailer!=null){
      player.cueVideo(trailer.getKey());
    }
  }
}
