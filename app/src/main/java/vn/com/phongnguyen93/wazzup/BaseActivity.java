package vn.com.phongnguyen93.wazzup;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import retrofit2.Retrofit;
import vn.com.phongnguyen93.wazzup.network.interfaces.WazzUpEndpoint;

/**
 * Created by phongnguyen on 2/16/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
  @Inject static WazzUpEndpoint mEndpoint;
  @Inject Retrofit mRetrofit;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MyApplication.getmMainComponent().inject(this);
    MovieRepository.getInstance().init(mEndpoint,this);

  }
}
