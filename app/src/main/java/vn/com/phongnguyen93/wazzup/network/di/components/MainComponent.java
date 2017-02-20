package vn.com.phongnguyen93.wazzup.network.di.components;



import dagger.Component;
import vn.com.phongnguyen93.wazzup.BaseActivity;
import vn.com.phongnguyen93.wazzup.network.di.AppScope;
import vn.com.phongnguyen93.wazzup.network.di.modules.ApiModule;


@AppScope
@Component(dependencies = NetComponent.class, modules = ApiModule.class)
public interface MainComponent {
    void inject(BaseActivity activity);
}
