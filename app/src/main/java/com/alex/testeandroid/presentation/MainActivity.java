package com.alex.testeandroid.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.alex.testeandroid.R;
import com.alex.testeandroid.presentation.contact.ContactFragment;
import com.alex.testeandroid.presentation.funds.FundsFragment;

/**
 * Created by Alex on 27/08/18.
 */
public class MainActivity extends AppCompatActivity {

    //region FIELDS
    BottomNavigationView bnvOptions;
    //endregion

    //region LIFECYCLE
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bnvOptions = findViewById(R.id.activity_main_bnv_options);

        setupBnvOptions();
        bnvOptions.setSelectedItemId(R.id.menu_main_options_investment);
    }
    //endregion

    //region METHODS
    //region PRIVATE METHODS
    private void setupBnvOptions() {
        bnvOptions.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.menu_main_options_investment:
                        fragment = FundsFragment.newInstance();
                        break;
                    default:
                        fragment = ContactFragment.newInstance();
                        break;

                }
                loadFragment(fragment);
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_cons_content, fragment)
                .commit();
    }
    //endregion
    //endregion
}
