package com.santander.wesleyalves.santandercode.fundosinvestimento;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.santander.wesleyalves.santandercode.R;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.FundoInvestimentoResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class FundosInvestimentoFragment extends Fragment {

    private View root;

    private final String URL_INVESTIMENTOS = "https://floating-mountain-50292.herokuapp.com/fund.json";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_fundos_investimento, container, false);
        setHasOptionsMenu(true);

        ExecuteHttpRequest();

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

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        boolean Error = true;
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public static FundosInvestimentoFragment newInstance() {
        return new FundosInvestimentoFragment();
    }
}
