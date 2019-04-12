package br.com.ricardo.testeandroid;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private TextView textMainTitle;
    private ImageView imageMainShare;
    private FrameLayout frameContainer;
    private LinearLayout linearInvestment;
    private LinearLayout linearContact;
    private FragmentManager fragmentManagerMain;
    private FragmentTransaction fragmentTransactionMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = (Toolbar) findViewById(R.id.main_toolbar);
        textMainTitle = (TextView) findViewById(R.id.main_text_title);
        imageMainShare = (ImageView) findViewById(R.id.main_image_share);
        frameContainer = (FrameLayout) findViewById(R.id.main_frame_container);
        linearInvestment = (LinearLayout) findViewById(R.id.main_linear_investment);
        linearContact = (LinearLayout) findViewById(R.id.main_linear_contact);

        textMainTitle.setText("Contato");
        fragmentManagerMain = getSupportFragmentManager();
        fragmentTransactionMain = fragmentManagerMain.beginTransaction();
        fragmentTransactionMain.add(R.id.main_frame_container, new ContactFragment());
        fragmentTransactionMain.commit();

        linearInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textMainTitle.setText("Investimento");
                imageMainShare.setVisibility(View.VISIBLE);
                fragmentTransactionMain = fragmentManagerMain.beginTransaction();
                fragmentTransactionMain.replace(R.id.main_frame_container, new InvestmentFragment());
                fragmentTransactionMain.commit();

            }
        });

        linearContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textMainTitle.setText("Contato");
                imageMainShare.setVisibility(View.INVISIBLE);
                fragmentTransactionMain = fragmentManagerMain.beginTransaction();
                fragmentTransactionMain.replace(R.id.main_frame_container, new ContactFragment());
                fragmentTransactionMain.commit();

            }
        });

    }
}
