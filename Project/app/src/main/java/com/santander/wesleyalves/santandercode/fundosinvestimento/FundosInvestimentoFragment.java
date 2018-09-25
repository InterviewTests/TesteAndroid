package com.santander.wesleyalves.santandercode.fundosinvestimento;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
import com.santander.wesleyalves.santandercode._utils.FontUtils;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.DownInfo;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.FundoInvestimentoResponse;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.Info;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.TwelveMonths;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class FundosInvestimentoFragment extends Fragment implements FundosInvestimentoContract.View {

    private View root;

    private TextView txt_titulo;
    private TextView txt_nome_fundo;
    private TextView txt_oque_e;
    private TextView txt_definicao;
    private TextView txt_titulo_grau_risco;
    private SeekBar seekBar;
    private TextView txt_titulo_mais_investimento;
    private TextView txt_mes_fundo;
    private TextView txt_mes_CDI;
    private TextView txt_ano_fundo;
    private TextView txt_ano_CDI;
    private TextView txt_meses_fundo;
    private TextView txt_meses_CDI;

    private LinearLayout list_infos;
    private LinearLayout list_down_infos;
    private Button btn_investir;

    private final String URL_INVESTIMENTOS = "https://floating-mountain-50292.herokuapp.com/fund.json";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_fundos_investimento, container, false);
        setHasOptionsMenu(true);

        definirObjetosLayout();
        ExecuteHttpRequest();
        definirListeners();
        definirFontes();

        return root;
    }

    public void ExecuteHttpRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL_INVESTIMENTOS, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("screen");

                            FundoInvestimentoResponse fundos = new FundoInvestimentoResponse();
                            Gson gson = new Gson();

                            fundos = gson.fromJson(object.toString(), FundoInvestimentoResponse.class);

                            JSONObject maisInformacoes = object.getJSONObject("moreInfo").getJSONObject("12months");
                            fundos.moreInfo.twelveMonths = gson.fromJson(maisInformacoes.toString(), TwelveMonths.class);

                            InformacoesArrayAdapter(fundos.info);
                            InformacoesDownloadArrayAdapter(fundos.downInfo);
                            DefinirInformacoes(fundos);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
Fun
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        boolean Error = true;
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void DefinirInformacoes(final FundoInvestimentoResponse fundosInvestimento) {
        txt_titulo.setText(fundosInvestimento.title);
        txt_nome_fundo.setText(fundosInvestimento.fundName);
        txt_oque_e.setText(fundosInvestimento.whatIs);
        txt_definicao.setText(fundosInvestimento.definition);
        txt_titulo_grau_risco.setText(fundosInvestimento.riskTitle);
        seekBar.setProgress(fundosInvestimento.risk);
        txt_titulo_mais_investimento.setText(fundosInvestimento.infoTitle);
        txt_mes_fundo.setText(String.valueOf(fundosInvestimento.moreInfo.month.fund));
        txt_mes_CDI.setText(String.valueOf(fundosInvestimento.moreInfo.month.CDI));
        txt_ano_fundo.setText(String.valueOf(fundosInvestimento.moreInfo.year.fund));
        txt_ano_CDI.setText(String.valueOf(fundosInvestimento.moreInfo.year.CDI));
        txt_meses_fundo.setText(String.valueOf(fundosInvestimento.moreInfo.twelveMonths.fund));
        txt_meses_CDI.setText(String.valueOf(fundosInvestimento.moreInfo.twelveMonths.CDI));
    }

    private void InformacoesArrayAdapter(ArrayList<Info> infos) {
        for (Info info : infos) {
            LinearLayout childLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearParams.setMargins(0, 20, 0, 0);

            childLayout.setLayoutParams(linearParams);

            TextView mName = new TextView(getContext());
            TextView mData = new TextView(getContext());

            mName.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            mData.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

            mName.setTextSize(16);
            mName.setGravity(Gravity.LEFT);

            mData.setTextSize(16);
            mData.setGravity(Gravity.RIGHT);

            mName.setText(info.getName());
            mData.setText(info.getData());

            childLayout.addView(mData, 0);
            childLayout.addView(mName, 0);

            list_infos.addView(childLayout);
        }
    }

    private void InformacoesDownloadArrayAdapter(ArrayList<DownInfo> downInfos) {
        for (DownInfo info : downInfos) {
            LinearLayout childLayout = new LinearLayout(getContext());
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            linearParams.setMargins(0, 20, 0, 0);
            childLayout.setLayoutParams(linearParams);

            TextView mName = new TextView(getContext());
            TextView mBaixar = new TextView(getContext());

            mName.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

            mBaixar.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, 5f));


            mName.setTextSize(16);
            mName.setGravity(Gravity.LEFT);
            mBaixar.setGravity(Gravity.RIGHT | Gravity.RIGHT);
            mBaixar.setTextColor(Color.RED);

            Drawable img = getContext().getResources().getDrawable(android.R.drawable.stat_sys_download);
            img.setBounds(0, 0, 60, 60);
            img.setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);
            mBaixar.setCompoundDrawables(img, null, null, null);

            mName.setText(info.getName());
            mBaixar.setText("Baixar");

            childLayout.addView(mBaixar, 0);
            childLayout.addView(mName, 0);

            list_down_infos.addView(childLayout);
        }
    }

    public void BtnInvestirClick() {

    }

    public static FundosInvestimentoFragment newInstance() {
        return new FundosInvestimentoFragment();
    }

    public void definirListeners() {
        btn_investir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BtnInvestirClick();
            }
        });
    }

    public void definirFontes() {
        Typeface dimpro_medium = Typeface.createFromAsset(root.getContext().getAssets(), FontUtils.DINPRO_MEDIUM);

        txt_titulo.setTypeface(dimpro_medium);
        txt_nome_fundo.setTypeface(dimpro_medium);
        txt_oque_e.setTypeface(dimpro_medium);
        txt_definicao.setTypeface(dimpro_medium);
        txt_titulo_grau_risco.setTypeface(dimpro_medium);
        txt_titulo_mais_investimento.setTypeface(dimpro_medium);
    }

    public void definirObjetosLayout() {
        list_infos = root.findViewById(R.id.linear_info_fundos);
        list_down_infos = root.findViewById(R.id.linear_downinfo_fundos);
        btn_investir = root.findViewById(R.id.btn_investir);
        btn_investir.setBackgroundResource(R.drawable.button_bg_color);

        txt_titulo = root.findViewById(R.id.txt_titulo);
        txt_nome_fundo = root.findViewById(R.id.txt_nome_fundo);
        txt_oque_e = root.findViewById(R.id.txt_oque_e);
        txt_definicao = root.findViewById(R.id.txt_definicao);
        txt_titulo_grau_risco = root.findViewById(R.id.txt_titulo_grau_risco);
        seekBar = root.findViewById(R.id.seekbar);
        seekBar.setEnabled(false);
        txt_titulo_mais_investimento = root.findViewById(R.id.txt_titulo_mais_investimentos);
        txt_mes_fundo = root.findViewById(R.id.txt_mes_fundo);
        txt_mes_CDI = root.findViewById(R.id.txt_mes_cdi);
        txt_ano_fundo = root.findViewById(R.id.txt_ano_fundo);
        txt_ano_CDI = root.findViewById(R.id.txt_ano_cdi);
        txt_meses_fundo = root.findViewById(R.id.txt_meses_fundo);
        txt_meses_CDI = root.findViewById(R.id.txt_meses_cdi);
    }
}
