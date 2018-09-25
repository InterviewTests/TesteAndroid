package com.santander.wesleyalves.santandercode.fundosinvestimento;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.santander.wesleyalves.santandercode.R;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.DownInfo;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.FundoInvestimentoResponse;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.Info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContatoFragment extends Fragment {

    private View root;

    private LinearLayout list_infos;
    private LinearLayout list_down_infos;
    private Button btn_investir;

    private final String URL_INVESTIMENTOS = "https://floating-mountain-50292.herokuapp.com/fund.json";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_contato, container, false);
        setHasOptionsMenu(true);

        return root;
    }

    public static ContatoFragment newInstance() {
        return new ContatoFragment();
    }


    public void definirObjetosLayout() {
        list_infos = root.findViewById(R.id.linear_info_fundos);
        list_down_infos = root.findViewById(R.id.linear_downinfo_fundos);
        btn_investir = root.findViewById(R.id.btn_investir);
        btn_investir.setBackgroundResource(R.drawable.button_bg_color);
    }
}
