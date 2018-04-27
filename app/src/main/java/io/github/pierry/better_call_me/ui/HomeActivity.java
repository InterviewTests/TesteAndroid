package io.github.pierry.better_call_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.R;
import io.github.pierry.better_call_me.ui.fragments.ContactFragment;
import io.github.pierry.better_call_me.ui.fragments.InvestmentFragment;
import javax.inject.Inject;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

  @BindView(R.id.navigation) BottomNavigationView navigation;

  @Inject InvestmentFragment investmentFragment;
  @Inject ContactFragment contactFragment;

  private Unbinder unbind;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.home_activity);
    unbind = ButterKnife.bind(this);
    App.getControllerComponent(this).inject(this);
    investment();
    navigation.setOnNavigationItemSelectedListener(this);
  }

  @Override protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unbind.unbind();
  }

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.actionInvestment:
        navigation.getMenu().getItem(0).setChecked(true);
        investment();
        break;
      case R.id.actionContact:
        navigation.getMenu().getItem(1).setChecked(true);
        contact();
        break;
    }
    return false;
  }

  void investment() {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.frame, investmentFragment);
    transaction.commit();
  }

  void contact() {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.frame, contactFragment);
    transaction.commit();
  }
}
