package com.example.savio.testeandroid.model;

import android.os.AsyncTask;

import com.example.savio.testeandroid.contract.FundContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FundModel implements FundContract.Model {

    public static List<Object> data = new ArrayList<>();

    @Override
    public List<Object> getData() {

        //call the class with AsyncTask
        try {

            data = new GetJson().execute().get();

        } catch (InterruptedException | ExecutionException e) {

            e.printStackTrace();
        }

        return data;
    }


//-----------------------------------AsyncTaks------------------------------------------------------//

    public static class GetJson extends AsyncTask<Void, Void, List<Object>> {

        //url of funds
        private String cellsURL = "http://floating-mountain-50292.herokuapp.com/fund.json";

        private List<Object> screen = new ArrayList<>();
        private List<Object> moreInfos = new ArrayList<>();
        private List<Object> infos = new ArrayList<>();

        private String bufferData = "";
        JSONObject jsonObject, array;
        JSONArray infoArray, downloadInfo;

        @Override
        protected List<Object> doInBackground(Void... voids) {

            URL url;
            String line = "";

            try {

                //connect to url link
                url = new URL(cellsURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                //inputStream to reading the data
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                //iterate the data
                while (line != null){

                    line = bufferedReader.readLine();
                    bufferData += line;
                }

                //get json the and iterate the values
                jsonObject = new JSONObject(bufferData);
                array = jsonObject.getJSONObject("screen");

                //add all messages into arraylist
                screen.add(array.getString("title"));
                screen.add(array.getString("fundName"));
                screen.add(array.getString("whatIs"));
                screen.add(array.getString("definition"));
                screen.add(array.getString("riskTitle"));
                screen.add(array.getInt("risk"));
                screen.add(array.getString("infoTitle"));

                //add all infos about investiment
                moreInfos.add(array.getJSONObject("moreInfo").getJSONObject("month").getDouble("fund"));
                moreInfos.add(array.getJSONObject("moreInfo").getJSONObject("month").getDouble("CDI"));
                moreInfos.add(array.getJSONObject("moreInfo").getJSONObject("year").getDouble("fund"));
                moreInfos.add(array.getJSONObject("moreInfo").getJSONObject("year").getDouble("CDI"));
                moreInfos.add(array.getJSONObject("moreInfo").getJSONObject("12months").getDouble("fund"));
                moreInfos.add(array.getJSONObject("moreInfo").getJSONObject("12months").getDouble("CDI"));

                infoArray = array.getJSONArray("info");

                //iterate the infos
                for (int i = 0; i < infoArray.length(); i++){

                    JSONObject field = infoArray.getJSONObject(i);

                    infos.add(Arrays.asList(field.get("name"), field.get("data")));

                }

                downloadInfo = array.getJSONArray("downInfo");

                //iterate the downInfo
                for (int i = 0; i < downloadInfo.length(); i++){

                    JSONObject field = downloadInfo.getJSONObject(i);

                    infos.add(Arrays.asList(field.get("name"), field.get("data")));

                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            //list indexing all results
            FundModel.data.add(screen);
            FundModel.data.add(moreInfos);
            FundModel.data.add(infos);

            return FundModel.data;
        }
    }
}
