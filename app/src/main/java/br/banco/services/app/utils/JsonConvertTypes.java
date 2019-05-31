package br.banco.services.app.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonConvertTypes {


    public Map<String, String> dataToMap = new HashMap<String, String>();

    public  void convertTo(String origin) {



        String className = "screen";
       // origin = "xxxx";

        JSONObject jsonObject = convertStringJson( origin, className );
        // dataToMap = convertToMap(origin);

       // String TES = convertClassName(jsonObject, className);

    }



    // OK


    // OK
    public JSONObject convertStringJson(String origin, String className) {

        try{

            if( origin.length() > 0 ) {

                Log.d("FUND", "convertStringJson -> SUCESS");

                JSONObject json = new JSONObject(origin);
                JSONObject parts = json.getJSONObject(className);

                return parts;
            }
            else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("FUND", "convertStringJson -> ERROR - >" + e.toString());
            return  null;
        }

    }

    // OK
    public  String convertClassName(JSONObject json, String className){

        String classFinal = "A";
       // String ClassConvert = className.substring(0, 1).toUpperCase() + className.substring(1);

        try {
            JSONObject mainObj = json.getJSONObject("screen");

            if (mainObj.length() == 10) {

                for(int i = 0; i<mainObj.names().length(); i++){

                    String mapKey = mainObj.names().getString(i) +"";
                    String mapValue = mainObj.get(mainObj.names().getString(i)) + "";
                    dataToMap.put(mapKey, mapValue);

                    Log.v("FUND", "" +mapKey + " = " + mapValue);
                }
                Log.d("FUND", "convertClassName ->  SUCESS" + classFinal );

            } else {
                Log.d("FUND", "convertClassName ->  ERROR"  );
                return  null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("FUND", "convertClassName -> ERROR - >" + e.toString());
            return  null;
        }






        /*

        try {
            // Field[] fields = classeTry.getFields();
            //Object fieldType = field.getType();


            Screen screen = new Screen();
            Class classeTry = screen.getClass();



            Field nameField = classeTry.getField("title");

            Log.v("FUND", "convertClassName -> SUCESSO " + nameField);

        } catch(NoSuchFieldException e) {
            Log.v("FUND", "convertClassName -> ERRO -> " +e.toString() + "  ");
        }
        */

        return  classFinal;
    }


    public static Map<String, Object> loadMap(JSONObject json) throws JSONException {

        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = jsonToMap(json);
        }
        return retMap;
    }

    public Map<String, String> convertToMap(String origin){

        Map<String, String> dataToMap = new HashMap<String, String>();
        boolean error = false;

        try {
             JSONObject json = new JSONObject(origin);
             JSONObject mainObj = json.getJSONObject("screen");

            if (mainObj.length() == 10) {

                for(int i = 0; i<mainObj.names().length(); i++){

                    String mapKey = mainObj.names().getString(i) +"";
                    String mapValue = mainObj.get(mainObj.names().getString(i)) + "";
                    dataToMap.put(mapKey, mapValue);

                   Log.v("FUND", "" +mapKey + " = " + mapValue);
                }
                Log.d("FUND", "convertToMap ->  SUCESS"  );

            } else {
                Log.d("FUND", "convertToMap ->  ERROR < 10"  );
                return  null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("FUND", "convertToMap -> ERROR - >" + e.toString());
            return  null;
        }
        return  dataToMap;
    }

    public String jsonMapToSring (HashMap<String, String> mainObj){

        String destiny = null;

        if (mainObj.size() == 10) {

            for(int i = 0; i < mainObj.size(); i++){

                String mapKey = mainObj.get(i) +"";
                String mapValue = mainObj.get(i) + "";

                 dataToMap.put(mapKey, mapValue);

                Log.v("FUND", "" +mapKey + " = " + mapValue);
            }
            Log.d("FUND", "convertToMap ->  SUCESS"  );

        } else {
            Log.d("FUND", "convertToMap ->  ERROR < 10"  );
            return  null;
        }

        return destiny;
    }

    public static Map<String, Object> jsonToMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> jsonToList(JSONArray array) throws JSONException {

        List<Object> list = new ArrayList<Object>();

        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            }
            list.add(value);
        }

        return list;
    }

    public String[] convertToArray(String start){
        String[] resultValue = {};

        return resultValue;
    }

    public ArrayList convertToString(String start){
        ArrayList resultValue = null ;

        return resultValue;
    }

}


/*
dataToMap.put("fundName", mainObj.getString("fundName"));
dataToMap.put("whatIs", mainObj.getString("whatIs"));
dataToMap.put("definition", mainObj.getString("definition"));
dataToMap.put("riskTitle", mainObj.getString("riskTitle"));
dataToMap.put("risk", mainObj.getString("risk"));
dataToMap.put("infoTitle", mainObj.getString("infoTitle"));
dataToMap.put("moreInfo", mainObj.getString("moreInfo"));
dataToMap.put("info", mainObj.getString("info"));
dataToMap.put("downInfo", mainObj.getString("downInfo"));
*/

