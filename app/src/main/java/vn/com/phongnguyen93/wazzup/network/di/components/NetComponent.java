package vn.com.phongnguyen93.wazzup.network.di.components;


import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import vn.com.phongnguyen93.wazzup.network.di.modules.AppModule;
import vn.com.phongnguyen93.wazzup.network.di.modules.NetModule;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
    okhttp3.OkHttpClient okHttpClient();
}