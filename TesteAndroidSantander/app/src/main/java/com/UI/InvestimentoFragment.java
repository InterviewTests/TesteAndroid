package com.UI;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cerqueira.mellina.testeandroidsantander.R;
import com.http.HttpCall;
import com.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InvestimentoFragment extends Fragment {

    private static final String URL = "https://floating-mountain-50292.herokuapp.com/fund.json";
    Context context;

    private TextView title;
    private TextView fundName;
    private TextView whatIs;
    private TextView definition;
    private TextView riskTitle;
    private TextView risk;
    private TextView infoTitle;
    RecyclerView recyclerViewMoreInfo;
    RecyclerView recyclerViewInfo;
    List<MoreInfo> moreInfo;
    List<Info> info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investimento, container, false);
        context = getActivity();

        adicionaComponentesdoLayoutXML(view);

        recyclerViewMoreInfo.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewInfo.setLayoutManager(new LinearLayoutManager(context));

        moreInfo = new ArrayList<>();
        info = new ArrayList<>();

        ReadInvestimentoJSONTask task = new ReadInvestimentoJSONTask();
        task.execute(URL);

        return view;
    }

    private void adicionaComponentesdoLayoutXML(View view) {
        title =  view.findViewById(R.id.txtTitle);
        fundName = view.findViewById(R.id.txtfundName);
        whatIs =  view.findViewById(R.id.txtWhatIs);
        definition =  view.findViewById(R.id.txtDefinition);
        riskTitle = view.findViewById(R.id.txtRiskTitle);
        risk =  view.findViewById(R.id.txtRisk);
        infoTitle = view.findViewById(R.id.txtInfoTitle);
        recyclerViewMoreInfo = view.findViewById(R.id.recycler_view);
        recyclerViewInfo = view.findViewById(R.id.recycler_view_info);
    }

    private class ReadInvestimentoJSONTask extends AsyncTask<String, Void, ArrayList<Object>> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList<Object> doInBackground(String... strings) {

            try {
                String url = strings[0];
                HttpCall http = new HttpCall(url);
                HttpResponse response = http.execute(HttpCall.Method.GET);
                Map<String, String> textosIniciais = new HashMap<String, String>();

                ArrayList<Object> objetos = new ArrayList<>();
                try {
                    JSONObject screen = new JSONObject(response.extractDataAsString());

                    JSONObject screenJSON = screen.getJSONObject("screen");

                    preencheArrayMoreInfoJSON(textosIniciais, screenJSON);
                    preencheArrayInfoJSON(screenJSON);

                    objetos.add(textosIniciais);
                    objetos.add(moreInfo);
                    objetos.add(info);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return objetos;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Object> objetos) {

            Map<String, String> textosIniciais = (Map<String, String>) objetos.get(0);
            List<MoreInfo> moreInfos = (List<MoreInfo>) objetos.get(1);
            List<Info> infos = (List<Info>) objetos.get(2);

            adicionaTextosIniciaisNaTela(textosIniciais);

            MoreInfoAdapter adapter = new MoreInfoAdapter(context, moreInfos);
            recyclerViewMoreInfo.setAdapter(adapter);

            InfoAdapter adapterInfo = new InfoAdapter(context, infos);
            recyclerViewInfo.setAdapter(adapterInfo);

            super.onPostExecute(objetos);
        }
    }

    private void preencheArrayMoreInfoJSON(Map<String, String> textosIniciais, JSONObject screenJSON) throws JSONException {
        preencheMapaTextosJSON(textosIniciais, screenJSON);

        JSONObject moreInfoJSON = screenJSON.getJSONObject("moreInfo");
        JSONObject monthJSON = moreInfoJSON.getJSONObject("month");
        JSONObject yearJSON = moreInfoJSON.getJSONObject("year");
        JSONObject months12JSON = moreInfoJSON.getJSONObject("12months");

        moreInfo.add(new MoreInfo(getString(R.string.month), (Double) monthJSON.get("fund"), (Double) monthJSON.get("CDI")));
        moreInfo.add(new MoreInfo(getString(R.string.year), (Double) yearJSON.get("fund"), (Double) yearJSON.get("CDI")));
        moreInfo.add(new MoreInfo(getString(R.string.months12), (Double) months12JSON.get("fund"), (Double) months12JSON.get("CDI")));
    }

    private void preencheArrayInfoJSON(JSONObject screenJSON) throws JSONException {
        JSONArray infoJSON = screenJSON.getJSONArray("info");

        for (int i = 0; i < infoJSON.length(); i++) {
            JSONObject infoObject = infoJSON.getJSONObject(i);
            info.add(new Info(String.valueOf(infoObject.get("name")), String.valueOf(infoObject.get("data"))));
        }

        JSONArray downinfoJSON = screenJSON.getJSONArray("downInfo");

        for (int i = 0; i < downinfoJSON.length(); i++) {
            JSONObject infoObject = downinfoJSON.getJSONObject(i);
            info.add(new Info(String.valueOf(infoObject.get("name")), String.valueOf(infoObject.get("data"))));
        }
    }

    private void preencheMapaTextosJSON(Map<String, String> textosIniciais, JSONObject screenJSON) throws JSONException {
        textosIniciais.put("title", String.valueOf(screenJSON.get("title")));
        textosIniciais.put("fundName", String.valueOf(screenJSON.get("fundName")));
        textosIniciais.put("whatIs", String.valueOf(screenJSON.get("whatIs")));
        textosIniciais.put("definition", String.valueOf(screenJSON.get("definition")));
        textosIniciais.put("riskTitle", String.valueOf(screenJSON.get("riskTitle")));
        textosIniciais.put("risk", String.valueOf(screenJSON.get("risk")));
        textosIniciais.put("infoTitle", String.valueOf(screenJSON.get("infoTitle")));
    }

    private void adicionaTextosIniciaisNaTela(Map<String, String> textosIniciais) {
        title.setText(textosIniciais.get("title"));
        fundName.setText(textosIniciais.get("fundName"));
        whatIs.setText(textosIniciais.get("whatIs"));
        definition.setText(textosIniciais.get("definition"));
        definition.setText(textosIniciais.get("definition"));
        riskTitle.setText(textosIniciais.get("riskTitle"));
        risk.setText(textosIniciais.get("risk"));
        infoTitle.setText(textosIniciais.get("infoTitle"));
    }
}
