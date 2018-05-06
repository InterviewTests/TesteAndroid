package com.UI;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapters.Info;
import com.adapters.InfoAdapter;
import com.adapters.MoreInfo;
import com.adapters.MoreInfoAdapter;
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
    private ImageView ImageRisk;
    private TextView infoTitle;
    RecyclerView recyclerViewMoreInfo;
    RecyclerView recyclerViewInfo;
    List<MoreInfo> moreInfo;
    List<Info> info;
    ReadInvestimentoJSONTask task;
    Button btnInvestir;
    View barraDivider;
    View barraDivider2;


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

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();

        if (isConnected) {
            task = new ReadInvestimentoJSONTask();
            task.execute(URL);

            alteraVisibilidadeComponentes(View.VISIBLE);
        }else{
            alteraVisibilidadeComponentes(View.INVISIBLE);
        }
        return view;
    }

    private void alteraVisibilidadeComponentes(int visible) {
        btnInvestir.setVisibility(visible);
        barraDivider.setVisibility(visible);
        barraDivider2.setVisibility(visible);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(task != null){
            task.cancel(true);
        }

    }

    private void adicionaComponentesdoLayoutXML(View view) {
        title =  view.findViewById(R.id.txtTitle);
        fundName = view.findViewById(R.id.txtfundName);
        whatIs =  view.findViewById(R.id.txtWhatIs);
        definition =  view.findViewById(R.id.txtDefinition);
        riskTitle = view.findViewById(R.id.txtRiskTitle);
        ImageRisk = view.findViewById(R.id.imageRisk);
        infoTitle = view.findViewById(R.id.txtInfoTitle);
        recyclerViewMoreInfo = view.findViewById(R.id.recycler_view);
        recyclerViewInfo = view.findViewById(R.id.recycler_view_info);
        btnInvestir = view.findViewById(R.id.btnInvestir);
        barraDivider = view.findViewById(R.id.divider);
        barraDivider2 = view.findViewById(R.id.divider2);

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
                Log.i("HTTP", "HTTP     "+http);
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

        moreInfo.add(new MoreInfo("","Fund" , "CDI"));
        moreInfo.add(new MoreInfo(getString(R.string.month),  String.valueOf(monthJSON.get("fund")), String.valueOf(monthJSON.get("CDI"))));
        moreInfo.add(new MoreInfo(getString(R.string.year), String.valueOf(yearJSON.get("fund")), String.valueOf(yearJSON.get("CDI"))));
        moreInfo.add(new MoreInfo(getString(R.string.months12), String.valueOf(months12JSON.get("fund")), String.valueOf(months12JSON.get("CDI"))));
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
        adicionaImagemRiskNaTela(Integer.parseInt(textosIniciais.get("risk")));
        infoTitle.setText(textosIniciais.get("infoTitle"));
    }

    private void adicionaImagemRiskNaTela(int risk){

        switch (risk){
            case 1:
                ImageRisk.setImageDrawable(context.getResources().getDrawable(R.drawable.horizontal_line_filled_50));
                break;
            case 2:
                ImageRisk.setImageDrawable(context.getResources().getDrawable(R.drawable.horizontal_line_filled_50));
                break;
            case 3:
                ImageRisk.setImageDrawable(context.getResources().getDrawable(R.drawable.horizontal_line_filled_50));
                break;
            case 4:
                ImageRisk.setImageDrawable(context.getResources().getDrawable(R.drawable.horizontal_line_filled_50));
                break;
            case 5:
                ImageRisk.setImageDrawable(context.getResources().getDrawable(R.drawable.horizontal_line_filled_50));
                break;
        }

    }
}
