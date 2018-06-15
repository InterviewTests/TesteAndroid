package br.com.accenture.santander.wallacebaldenebre.ui.main.fragments.investment;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.accenture.santander.wallacebaldenebre.model.DownInfo;
import br.com.accenture.santander.wallacebaldenebre.model.Info;
import br.com.accenture.santander.wallacebaldenebre.model.Month;
import br.com.accenture.santander.wallacebaldenebre.model.MoreInfo;
import br.com.accenture.santander.wallacebaldenebre.model.Screen;
import br.com.accenture.santander.wallacebaldenebre.model.TwelveMonths;
import br.com.accenture.santander.wallacebaldenebre.model.Year;
import br.com.accenture.santander.wallacebaldenebre.ui.base.BasePresenter;
import br.com.accenture.santander.wallacebaldenebre.ui.main.fragments.contact.ContactContract;
import br.com.accenture.santander.wallacebaldenebre.utils.Helper;

public class InvestmentPresenter<V extends ContactContract.View> extends BasePresenter<V> implements InvestmentContract.Presenter<V> {
    @Override
    public void showDataFromServer(InvestmentFragment infra, final InvestmentCallback<HashMap<String, Screen>> callback) {
        new GetDataInvestment(infra, callback).execute();
    }

    public class GetDataInvestment extends AsyncTask<Void, Void, Void> {
        private InvestmentCallback<HashMap<String, Screen>> callback;
        private InvestmentFragment infra;

        public GetDataInvestment(InvestmentFragment infra, InvestmentCallback<HashMap<String, Screen>> callback) {
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

                    JSONObject screen = jo.getJSONObject("screen");

                    String title = screen.getString("title");
                    String fundName = screen.getString("fundName");
                    String whatIs = screen.getString("whatIs");
                    String definition = screen.getString("definition");
                    String riskTitle = screen.getString("riskTitle");
                    int risk = screen.getInt("risk");
                    String infoTitle = screen.getString("infoTitle");

                    List<MoreInfo> moreinfos = new ArrayList<>();
                    JSONObject moreInfo = screen.getJSONObject("moreInfo");
                    for (int i = 0; i < moreInfo.length(); i++) {
                        JSONObject month = moreInfo.getJSONObject("month");
                        double fundMonth = month.getDouble("fund");
                        double cdiMonth = month.getDouble("CDI");

                        JSONObject year = moreInfo.getJSONObject("year");
                        double fundYear = year.getDouble("fund");
                        double cdiYear = year.getDouble("CDI");

                        JSONObject twelveMonths = moreInfo.getJSONObject("12months");
                        double fundTwelveMonth = twelveMonths.getDouble("fund");
                        double cdiTwelveMonth = twelveMonths.getDouble("CDI");

                        moreinfos.add(
                                new MoreInfo(
                                        new Month(fundMonth, cdiMonth),
                                        new Year(fundYear, cdiYear),
                                        new TwelveMonths(fundTwelveMonth, cdiTwelveMonth))
                        );
                    }

                    Info[] infosArray = new Info[7];
                    JSONArray infos = screen.getJSONArray("info");
                    for (int j = 0; j < infos.length(); j++) {
                        JSONObject info = infos.getJSONObject(j);
                        String name = info.getString("name");
                        String data = info.getString("data");
                        infosArray[j] = new Info(name, data);
                    }

                    DownInfo[] downInfos = new DownInfo[7];
                    JSONArray downInfo = screen.getJSONArray("downInfo");
                    for (int j = 0; j < downInfo.length(); j++) {
                        JSONObject info = downInfo.getJSONObject(j);
                        String name = info.getString("name");
                        String data = info.getString("data");
                        downInfos[j] = new DownInfo(name, data);
                    }

                    HashMap<String, Screen> temp = new HashMap<>();
                    temp.put("screen", new Screen(
                            title, fundName, whatIs,
                            definition, riskTitle, risk,
                            infoTitle, moreinfos, infosArray, downInfos));

                    callback.onSuccess(temp);
                    infra.hideProgress();

                } catch (Exception e) {
                    infra.hideProgress();
                    e.printStackTrace();
                    Log.e("LOG", "doInBackground: Error -> " + e.getMessage());
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
