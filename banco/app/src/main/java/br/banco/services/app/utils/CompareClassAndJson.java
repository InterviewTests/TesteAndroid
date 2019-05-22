package br.banco.services.app.utils;

import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Field;

import br.banco.services.fund.data.template.FromFund;

import static br.banco.services.fund.domain.Configclass.SCREEN_CLASS;

public class CompareClassAndJson {


    public  boolean compareIF(JSONObject json, Class<?> clas){

        boolean compare = false;

        try {

            Field[] fields = clas.getDeclaredFields();
            int count = 0;
            String jsonNames = null;
            String fieldNames = null;

            for(Field fieldName : fields){

                //Class fielType = fieldName.getType();
                jsonNames = json.names().getString(count);


                Log.d("FUND", fieldName.getName() + " / " + jsonNames);

                count++;
            }

        }catch (Exception e){
            Log.d("FUND", "compareIF->ERROR->" + e.getMessage());
        }

        return compare ;
    }





}
