package vn.com.phongnguyen93.wazzup.network.di.modules;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

import vn.com.phongnguyen93.wazzup.network.di.AppScope;
import vn.com.phongnguyen93.wazzup.network.interfaces.WazzUpEndpoint;

@Module
@AppScope
public class ApiModule {

    @Provides
    public WazzUpEndpoint providesApiInterface(Retrofit retrofit) {
        return retrofit.create(WazzUpEndpoint.class);
    }

}
