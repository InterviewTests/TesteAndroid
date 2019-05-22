package br.banco.services.fund.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.GsonBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.banco.services.fund.domain.national.NationalFund;
import br.banco.services.fund.domain.national.Screen;



public class ConvertGsonSimple {

     //public List<NationalFund> nacional = new ArrayList<NationalFund>();
     public ConvertGsonSimple() { }

        public void convertGS(String dataOrigin) {


       try{

           String message = null;
           Gson gson = new Gson();


        }catch (Exception e){
            Log.d("FUND", "" + e);
        }


    }

}

