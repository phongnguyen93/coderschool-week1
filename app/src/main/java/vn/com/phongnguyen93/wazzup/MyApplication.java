package vn.com.phongnguyen93.wazzup;

import android.app.Application;

import vn.com.phongnguyen93.wazzup.network.di.components.DaggerMainComponent;
import vn.com.phongnguyen93.wazzup.network.di.components.DaggerNetComponent;
import vn.com.phongnguyen93.wazzup.network.di.components.MainComponent;
import vn.com.phongnguyen93.wazzup.network.di.components.NetComponent;
import vn.com.phongnguyen93.wazzup.network.di.modules.ApiModule;
import vn.com.phongnguyen93.wazzup.network.di.modules.AppModule;
import vn.com.phongnguyen93.wazzup.network.di.modules.NetModule;

/**
 * Created by phongnguyen on 2/16/17.
 */

public class MyApplication extends Application {
  private String baseURL;
  private static NetComponent mNetComponent;
  private static MainComponent mMainComponent;

  public static MainComponent getmMainComponent() {
    return mMainComponent;
  }

  public static void setmMainComponent(MainComponent mMainComponent) {
    MyApplication.mMainComponent = mMainComponent;
  }

  public static NetComponent getmNetComponent() {
    return mNetComponent;
  }

  public static void setmNetComponent(NetComponent mNetComponent) {
    MyApplication.mNetComponent = mNetComponent;
  }

  @Override public void onCreate() {
    super.onCreate();
    baseURL = getString(R.string.base_url);
    overrideFont();
    ImageHandler.initPicassoClient(this);

    setmNetComponent(DaggerNetComponent.builder()
        .appModule(new AppModule(this))
        .netModule(new NetModule(baseURL))
        .build());

    setmMainComponent(DaggerMainComponent.builder()
        .netComponent(getmNetComponent())
        .apiModule(new ApiModule())
        .build());

  }

  private void overrideFont(){
    FontsOverride.setDefaultFont(this, "DEFAULT", "VolteRounded-Regular.ttf");
    FontsOverride.setDefaultFont(this, "MONOSPACE", "VolteRounded-Regular.ttf");
    FontsOverride.setDefaultFont(this, "SERIF", "VolteRounded-Regular.ttf");
    FontsOverride.setDefaultFont(this, "SANS_SERIF", "VolteRounded-Regular.ttf");
  }
}
