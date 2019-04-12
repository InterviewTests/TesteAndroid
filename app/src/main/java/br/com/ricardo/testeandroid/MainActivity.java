package br.com.ricardo.testeandroid;

import android.graphics.Typeface;
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
    private FrameLayout frameMainContainer;
    private LinearLayout linearMainInvestment;
    private LinearLayout linearMainContact;
    private TextView textMainInvestment;
    private TextView textMainContact;
    private FragmentManager fragmentManagerMain;
    private FragmentTransaction fragmentTransactionMain;

    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarMain = (Toolbar) findViewById(R.id.main_toolbar);
        textMainTitle = (TextView) findViewById(R.id.main_text_title);
        imageMainShare = (ImageView) findViewById(R.id.main_image_share);
        frameMainContainer = (FrameLayout) findViewById(R.id.main_frame_container);
        linearMainInvestment = (LinearLayout) findViewById(R.id.main_linear_investment);
        linearMainContact = (LinearLayout) findViewById(R.id.main_linear_contact);
        textMainInvestment = (TextView) findViewById(R.id.main_text_investment);
        textMainContact = (TextView) findViewById(R.id.main_text_contact);

        textMainTitle.setText("Contato");
        font = Typeface.createFromAsset(getAssets(), "font/DINPro-Medium.otf");
        textMainTitle.setTypeface(font);
        textMainInvestment.setTypeface(font);
        textMainContact.setTypeface(font);


        fragmentManagerMain = getSupportFragmentManager();
        fragmentTransactionMain = fragmentManagerMain.beginTransaction();
        fragmentTransactionMain.add(R.id.main_frame_container, new ContactFragment());
        fragmentTransactionMain.commit();

        linearMainInvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textMainTitle.setText("Investimento");
                imageMainShare.setVisibility(View.VISIBLE);
                fragmentTransactionMain = fragmentManagerMain.beginTransaction();
                fragmentTransactionMain.replace(R.id.main_frame_container, new InvestmentFragment());
                fragmentTransactionMain.commit();

            }
        });

        linearMainContact.setOnClickListener(new View.OnClickListener() {
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
