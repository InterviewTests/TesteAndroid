package com.example.savio.testeandroid.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.savio.testeandroid.contract.FormContract;

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
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FormModel implements FormContract.Model {

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

        //url of cells
        private String cellsURL = "http://floating-mountain-50292.herokuapp.com/cells.json";

        private List<Object> values = new ArrayList<>();
        private final List<Object> messages = new ArrayList<>();

        private String bufferData = "";
        JSONObject jsonObject;
        JSONArray array;

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
                array = jsonObject.getJSONArray("cells");

                for (int i = 0; i < array.length(); i++) {

                    JSONObject field = array.getJSONObject(i);

                    values.add(field.getInt("id"));
                    values.add(field.getInt("type"));
                    values.add(field.getString("message"));
                    values.add(field.get("typefield"));
                    values.add(field.getBoolean("hidden"));
                    values.add(field.getInt("topSpacing"));
                    values.add(field.get("show"));
                    values.add(field.getBoolean("required"));

                    //add all messages into arraylist
                    messages.add(field.getString("message"));

                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            Log.i("FORMS_VALUES", values.toString());

            return messages;
        }
    }
}