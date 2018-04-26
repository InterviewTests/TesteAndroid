package com.aline.teste.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aline.teste.MVP.presenter.PresenterContato;
import com.aline.teste.Models.Cells;
import com.aline.teste.Models.Screen;
import com.aline.teste.R;
import com.aline.teste.eventbus.EventContato;
import com.aline.teste.eventbus.EventFund;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.text.InputType.TYPE_CLASS_PHONE;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_NULL;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    PresenterContato presenterContato = new PresenterContato();
    List<Cells> cellsList = new ArrayList<>();

    @Subscribe
    public void onEvent(EventContato event) {
        cellsList = event.getCellsList();
        criarForm();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.linear_main);

        boolean checkRede = getCheckSate();
        if (checkRede) {
            presenterContato.callNetworkContato();

        } else {
            Snackbar.make(layout, getString(R.string.snack_sem_conexao),
                    Snackbar.LENGTH_LONG).show();
        }

        TextView btnInvestimento = findViewById(R.id.btn_investimento);
        btnInvestimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Investimento.class));
                finish();
            }
        });

    }



    public boolean getCheckSate() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo inforRede = null;
        if (connectivityManager != null) {
            inforRede = connectivityManager.getActiveNetworkInfo();
        }
        if (inforRede != null && inforRede.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView btnContato = findViewById(R.id.btn_contato);
        btnContato.setBackgroundColor(getResources().getColor(R.color.colorBtnCheck));
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void criarForm() {

        for (int i = 0; i < cellsList.size(); i++) {
            Cells cells = cellsList.get(i);
            switch (cells.getType()) {
                case 1:
                    EditText editType = new EditText(MainActivity.this);
                    if (cells.getTypefield().equals(1)) {
                        editType.setInputType(TYPE_CLASS_TEXT);
                    } else if (cells.getTypefield().equals(2)) {
                        editType.setInputType(TYPE_CLASS_PHONE);
                    } else if (cells.getTypefield().equals(3)) {
                        editType.setInputType(TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    }else{
                        editType.setInputType(TYPE_NULL);
                    }

                    if (cells.getMessage() != null && !cells.getMessage().trim().isEmpty()) {
                        editType.setHint(cells.getMessage());
                    }
                    if (cells.isHidden()) {
                        editType.setVisibility(View.INVISIBLE);
                    } else {
                        editType.setVisibility(View.VISIBLE);
                    }
                    editType.setId(cells.getId());
                    layout.addView(editType);
                    break;

                case 2:
                    TextView textType = new TextView(MainActivity.this);
                    textType.setId(cells.getId());
                    textType.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    if (cells.getMessage() != null && !cells.getMessage().trim().isEmpty())
                        textType.setText(cells.getMessage());
                    if (cells.isHidden()) {
                        textType.setVisibility(View.INVISIBLE);
                    } else {
                        textType.setVisibility(View.VISIBLE);
                    }

                    textType.setGravity(Gravity.CENTER);
                    layout.addView(textType);
                    break;

                case 3:
                    ImageView imgType = new ImageView(MainActivity.this);
                    break;

                case 4:
                    CheckBox checkType = new CheckBox(MainActivity.this);
                    checkType.setId(cells.getId());
                    if (cells.getMessage() != null && !cells.getMessage().trim().isEmpty())
                        checkType.setText(cells.getMessage());
                    if (cells.isHidden()) {
                        checkType.setVisibility(View.INVISIBLE);
                    } else {
                        checkType.setVisibility(View.VISIBLE);
                    }
                    layout.addView(checkType);
                    break;

                case 5:
                    Button btnType = new Button(MainActivity.this);
                    btnType.setId(cells.getId());


                    if (cells.getMessage() != null && !cells.getMessage().trim().isEmpty())
                        btnType.setText(cells.getMessage());
                    if (cells.isHidden()) {
                        btnType.setVisibility(View.INVISIBLE);
                    } else {
                        btnType.setVisibility(View.VISIBLE);
                    }
                    btnType.setBackground(getResources().getDrawable(R.drawable.background_btn));
                    btnType.setTextColor(getResources().getColor(R.color.colorPrimary));
                    layout.addView(btnType);
                    break;
                default:
            }


        }

    }


}

