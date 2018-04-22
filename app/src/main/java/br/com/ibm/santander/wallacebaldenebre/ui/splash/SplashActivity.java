package br.com.ibm.santander.wallacebaldenebre.ui.splash;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import br.com.ibm.santander.wallacebaldenebre.R;
import br.com.ibm.santander.wallacebaldenebre.ui.main.MainActivity;
import br.com.ibm.santander.wallacebaldenebre.utils.Helper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Helper.openClass(SplashActivity.this, MainActivity.class);
            }
        }, 2800);
    }
}
