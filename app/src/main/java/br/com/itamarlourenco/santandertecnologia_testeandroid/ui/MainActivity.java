package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.view.View;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomButton;

public class MainActivity extends BaseActivity  implements View.OnClickListener {
    private CustomButton investment;
    private CustomButton contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        investment = findViewById(R.id.investment);
        contact = findViewById(R.id.contact);
        investment.setOnClickListener(this);
        contact.setOnClickListener(this);
    }

    @Override
    protected BaseFragment getFragment() {
        return FormFragment.newInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if(v == investment){
            investment.setBackground(getDrawable(R.drawable.button_red_hover_main_selected));
            contact.setBackground(getDrawable(R.drawable.button_red_hover_main));

            alterFragments(InvestmentFragment.newInstance());
        }else if(v == contact){
            investment.setBackground(getDrawable(R.drawable.button_red_hover_main));
            contact.setBackground(getDrawable(R.drawable.button_red_hover_main_selected));

            alterFragments(getFragment());
        }
    }


}
