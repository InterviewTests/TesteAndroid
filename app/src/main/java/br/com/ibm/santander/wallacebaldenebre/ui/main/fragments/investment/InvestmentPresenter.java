package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.investment;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import br.com.ibm.santander.wallacebaldenebre.ui.base.BasePresenter;
import br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.contact.ContactContract;
import br.com.ibm.santander.wallacebaldenebre.utils.Helper;

public class InvestmentPresenter<V extends ContactContract.View> extends BasePresenter<V> implements InvestmentContract.Presenter<V> {
    @Override
    public void showDataFromServer(InvestmentFragment infra, final InvestmentCallback<HashMap<String, String>> callback) {
        new GetDataInvestment(infra, callback).execute();
    }

    public class GetDataInvestment extends AsyncTask<Void, Void, Void> {
        private InvestmentCallback<HashMap<String, String>> callback;
        private InvestmentFragment infra;

        public GetDataInvestment(InvestmentFragment infra, InvestmentCallback<HashMap<String, String>> callback) {
            this.infra = infra;
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            infra.showProgress();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String jsonString = Helper.callAPI("https://floating-mountain-50292.herokuapp.com/fund.json");

            if (jsonString != null) {
                try {
                    JSONObject jo = new JSONObject(jsonString);

                    JSONArray screens = jo.getJSONArray("screen");
                    for (int i = 0; i < screens.length(); i++) {
                        JSONObject screen = screens.getJSONObject(i);

                        String title = screen.getString("title");
                        String fundName = screen.getString("fundName");
                        String whatIs = screen.getString("whatIs");
                        String definition = screen.getString("definition");
                        String riskTitle = screen.getString("riskTitle");
                        int risk = screen.getInt("risk");
                        String infoTitle = screen.getString("infoTitle");

                        JSONObject moreInfo = screen.getJSONObject("moreInfo");
                        JSONObject month = moreInfo.getJSONObject("month");
                        String fundMonth = month.getString("fund");
                        double cdiMonth = month.getDouble("CDI");

                        JSONObject year = moreInfo.getJSONObject("year");
                        String fundYear = year.getString("fund");
                        double cdiYear = year.getDouble("CDI");

                        JSONObject twelveMonths = moreInfo.getJSONObject("12months");
                        String fundTwelveMonth = twelveMonths.getString("fund");
                        double cdiTwelveMonth = twelveMonths.getDouble("CDI");

                        JSONArray infos = screen.getJSONArray("info");
                        for (int j = 0; j < infos.length(); j++) {
                            JSONObject info = infos.getJSONObject(j);
                            String name = info.getString("name");
                            String data = info.getString("data");
                        }

                        JSONArray downInfo = screen.getJSONArray("downInfo");
                        for (int j = 0; j < downInfo.length(); j++) {
                            JSONObject info = downInfo.getJSONObject(j);
                            String name = info.getString("name");
                            String data = info.getString("data");
                        }

                        HashMap<String, String> temp = new HashMap<>();
                        temp.put("title", title);
                        temp.put("fundName", fundName);
                        temp.put("whatIs", whatIs);
                        temp.put("definition", definition);
                        temp.put("riskTitle", riskTitle);
                        temp.put("risk", String.valueOf(risk));
                        temp.put("infoTitle", infoTitle);
                        temp.put("moreInfo", String.valueOf(moreInfo));
                        temp.put("info", String.valueOf(infos));
                        temp.put("downInfo", String.valueOf(downInfo));

                        callback.onSuccess(temp);
                        infra.hideProgress();
                    }
                } catch (Exception e) {
                    infra.hideProgress();
                    e.printStackTrace();
                }
            } else {
                infra.hideProgress();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            infra.hideProgress();
        }
    }
}
