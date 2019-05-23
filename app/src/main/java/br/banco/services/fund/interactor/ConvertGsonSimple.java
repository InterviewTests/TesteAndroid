package br.banco.services.fund.interactor;

import android.util.Log; 

import com.google.gson.Gson;


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

