package resource.com.br.santanderapp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import resource.com.br.santanderapp.R;
import resource.com.br.santanderapp.ui.form.FormFragment;
import resource.com.br.santanderapp.ui.investment.InvestmentFragment;
import resource.com.br.santanderapp.ui.success.SuccessFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final Toolbar toolbar = findViewById(R.id.toobar_fragment);
        final TextView txtTitleToolbar = findViewById(R.id.toolbar_title);
        final ImageView imgMenuToobal = findViewById(R.id.toolbar_menu);
        final Button btnContacts = findViewById(R.id.form_btn_contacts);
        final Button btnInvestment = findViewById(R.id.form_btn_investment);

        setSupportActionBar(toolbar);


        final ViewPager viewPager = findViewById(R.id.form_view_page);
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new InvestmentFragment(viewPager));
        adapter.addFragment(new FormFragment(viewPager));
        adapter.addFragment(new SuccessFragment(viewPager));

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:

                        setScreenInvestment(txtTitleToolbar, btnContacts, btnInvestment, imgMenuToobal);
                        ;

                        break;

                    case 1:
                        setScreenForm(txtTitleToolbar, btnContacts, btnInvestment, imgMenuToobal);

                        break;

                    case 2:
                        setScreenForm(txtTitleToolbar, btnContacts, btnInvestment, imgMenuToobal);
                        imgMenuToobal.setVisibility(View.INVISIBLE);
                        break;
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void setScreenInvestment(TextView txtTitleToolbar, Button btnContacts, Button btnInvestment, ImageView imgMenuToobal) {
        txtTitleToolbar.setText("Investimento");
        btnContacts.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorRed));
        btnInvestment.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorDarkRed));
        imgMenuToobal.setVisibility(View.VISIBLE);
    }

    private void setScreenForm(TextView txtTitleToolbar, Button btnContacts, Button btnInvestment, ImageView imgMenuToobal) {
        txtTitleToolbar.setText("Contato");
        btnContacts.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorDarkRed));
        btnInvestment.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorRed));
        imgMenuToobal.setVisibility(View.INVISIBLE);
    }


}
