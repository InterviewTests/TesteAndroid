package br.com.itamarlourenco.santandertecnologia_testeandroid.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class override of application
 */
public class App extends Application {

    private static App instance;

    private static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";
    private static Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        App.instance = this;
    }

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        if (mRetrofit == null) {
            mRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static Context getContext() {
        return App.instance;
    }

    static public class Fonts{
        public static Typeface normal(){
            return Typeface.createFromAsset(App.getContext().getAssets(), "fonts/DINPro-Regular.otf");
        }

        public static Typeface bold(){
            return Typeface.createFromAsset(App.getContext().getAssets(), "fonts/DINPro-Bold.otf");
        }
    }
}
