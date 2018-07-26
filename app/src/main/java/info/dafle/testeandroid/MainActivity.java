package info.dafle.testeandroid;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import info.dafle.testeandroid.mvp.contato.ContatoFragment;
import info.dafle.testeandroid.mvp.investimento.InvestimentoFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    Button bt_investimento, bt_contato;
    private int currenttypeFragment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_contato = findViewById(R.id.bt_contato);
        bt_investimento = findViewById(R.id.bt_investimento);
        bt_contato.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/DINPro-Light.ttf"));
        bt_investimento.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/DINPro-Light.ttf"));
        bt_investimento.setOnClickListener(v -> showFragment(1));
        bt_contato.setOnClickListener(v -> showFragment(2));
        if (savedInstanceState != null) {
            currenttypeFragment = savedInstanceState.getInt("fragment");
            changeColorButton(currenttypeFragment);
        } else showFragment(currenttypeFragment);
    }

    void showFragment(int typeFragment) {

        if (getSupportFragmentManager()!=null) {

            changeColorButton(typeFragment);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, typeFragment == 1 ? new InvestimentoFragment() : new ContatoFragment());
            transaction.commit();

            Log.i("Script", "AKiiiiii");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("fragment", currenttypeFragment);
        super.onSaveInstanceState(outState);
    }

    void changeColorButton(int typeFragment) {

        this.currenttypeFragment = typeFragment;
        boolean bg = typeFragment == 1;
        int red = getResources().getColor(R.color.red);
        int redAlpha = getResources().getColor(R.color.redAlpha);
        bt_investimento.setBackgroundColor(!bg ? red : redAlpha);
        bt_contato.setBackgroundColor(bg ? red : redAlpha);
    }
}
