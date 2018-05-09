package rrzaniolo.testandroidsantander.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.main.MainView;
import rrzaniolo.testandroidsantander.utils.Constants;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/7/2018.
 * All rights reserved.
 */

/**
 * Simple SplashView with the Santander logo.
 * */
public class SplashView extends AppCompatActivity{

    //region --- Life Cycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideStatusBar();

        setContentView(R.layout.view_splash);

        startDelay();
    }
    //endregion


    //region --- Private Methods
    /**
     * This methods hides the phone StatusBar.
     * */
    private void hideStatusBar(){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /***
     * This method will finish the SplashView after a few seconds. It's been used to hold the user
     * a little in the splash so it's visible.
     * */
    private void startDelay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        }, Constants.SPLASH_DURATION);
    }


    /**
     * This method send the user to the MainView. It does so using a animation for the Transition.
     * */
    private void goToMain(){
        finish();
        startActivity(new Intent(SplashView.this, MainView.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    //endregion
}
