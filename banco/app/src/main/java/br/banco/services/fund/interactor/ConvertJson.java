package br.banco.services.fund.interactor;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collections;
import java.lang.reflect.Method;


import br.banco.services.app.utils.ReactAplication;
import br.banco.services.fund.domain.national.Screen;


public class ConvertJson {

    public Map<String, String> dataToMap;
    public JSONObject jsonObject;
    public String stringClass;
    public String arrayCompare;
    public int integerTotal;
    public Screen screen;

    public String messageTask;
    ReactAplication RX;

   //private Throwable throwable;
    // private static Method method;


    public  ArrayList<String> arrayClass;
    public  ArrayList<String> arrayJson;
    public  boolean compareArrays;

    public  ConvertJson(){

        this.RX = new ReactAplication();
        jsonObject = new JSONObject();
        dataToMap =  new HashMap<String, String>();
        stringClass = new String();
        arrayClass = new ArrayList<>();
        arrayJson = new ArrayList<>();
        compareArrays = false;
    }


    // BUSINESS //Class<?> classFrom
    public void onStartTask(String packageStr, String jsonStr){

        try {

            Class classTo = Class.forName(packageStr);
            String className = classTo.getSimpleName().toLowerCase();

             jsonObject = stringToJson(jsonStr, className);
             dataToMap = jsonToMap(jsonObject);
             arrayClass = classToArray(jsonObject , classTo);
             arrayJson = jsonToArray(jsonObject);
             compareArrays = compareArrays(arrayClass, arrayJson);

            messageTask = className;

            RX.onNext(messageTask);
        } catch (Exception e) {
            onErrorTask(e, messageTask);
        }


    }

    public void onErrorTask(Throwable e, String message){

       // Log.d("FUND", arrayClass.size() + " onErrorTask: " + message );

    }

    public void onSucessTask(){
        // delegate


        Log.d("FUND", arrayClass.size() + " onSucess: " + messageTask );
    }





    // CONVERTER  ---------------------





    public JSONObject createJson(String strValue) {

        try{

            JSONObject json = new JSONObject(strValue);
            //json.put(strValue);

            RX.onNext("" + (json!=null));
            return json;

        } catch (Exception e) {
            //onError(e);

            RX.onError(e);
            return  null;
        }

    }

    public JSONObject createJsonTree(String K1, String V1, String K2, String V2) {

       JSONObject json = null;

       try {

           json = new JSONObject();
           json.put(K1, V1);

           JSONArray jsonSon = new JSONArray();
           jsonSon.put(K2);
           jsonSon.put(V2);

           json.put("SON", jsonSon);

           RX.onNext(""+(json!=null));
       }catch (JSONException e){
           RX.onError(e);
       }

        return  json;
    }

    // OK
    public JSONObject stringToJson(String classString, String className) {

        try{

            JSONObject json = new JSONObject(classString);
            JSONObject jsonConvert = json.getJSONObject(className);

            RX.onNext("" + (jsonConvert!=null));
            return jsonConvert;

        } catch (Exception e) {
            //onError(e);

            RX.onError(e);
            return  null;
        }

    }

    // OK
    public Map<String, String> jsonToMap(JSONObject json){
        Map<String, String> dataMap = new HashMap<String, String>();
        try {
             JSONObject mainObj = json;
                for(int i = 0; i<mainObj.names().length(); i++){
                    String mapKey = mainObj.names().getString(i) +"";
                    String mapValue = mainObj.get(mainObj.names().getString(i)) + "";
                    dataMap.put(mapKey, mapValue);
                  //Log.v("FUND", "" +mapKey + " = " + mapValue);
                }
            RX.onNext( "BOOL: " + (dataMap!=null) );
        } catch (Exception e) {
            //e.printStackTrace();
            RX.onError(e);
            return  null;
        }
        return  dataMap;
    }

    // OK
    public ArrayList<String> classToArray(JSONObject json, Class<?> clas){

        ArrayList<String> arrayClass = null;

        try {
            arrayClass = new ArrayList<>();
            Field[] fields = clas.getDeclaredFields();
            int fieldCount = fields.length;
            for(Field f : fields){
                f.setAccessible(true);
                if(!(f.getName().equals("id")  ||
                     f.getName().equals("$change")  ||
                     f.getName().equals("serialVersionUID"))){
                     arrayClass.add(f.getName());
                }
               // Log.d("FUND", f.getName() + " / " + f.getType() );
            }
           // Log.d("FUND", arrayClass.size() + " / " + fieldCount );
            RX.onNext("size: " +(arrayClass.size() > 0));
        }catch ( Exception e){
            RX.onError(e);
        }
        return  arrayClass;
    }

    // OK
    public ArrayList<String>  jsonToArray(JSONObject json){
        ArrayList<String> dataArray = null;
        try {
            dataArray =new ArrayList<String>();
            JSONObject mainObj = json;
            for(int i = 0; i<mainObj.names().length(); i++){
                String mKey = mainObj.names().getString(i) +"";
                String mValue = mainObj.get(mainObj.names().getString(i)) + "";
                dataArray.add(mKey);
               // Log.v("FUND", "" +mKey + " = " + mValue);
            }
            RX.onNext("bool: " + (dataArray!=null));
        } catch (Exception e) {
            RX.onError(e);
        }
        return dataArray;
    }


    // COMPARAR  ---------------------


      public boolean  compareArrays(ArrayList<String> array1, ArrayList<String> array2){
          boolean compare = false;
          try {
              Collections.sort(array1);
              Collections.sort(array2);
              compare = (array1.equals(array2));
              RX.onNext("bool:" + (compare));
          } catch (Exception e) {
             // e.printStackTrace();
              RX.onError(e);
          }
        return (compare);
      }


      //UTILIZAR   ---------------------


    // -> AINDA EM TESTE

    public int getColumnFromClass(Class<?> classActual) {
        int count = 0;

        try{

        Field[] fields = classActual.getDeclaredFields();
        String fieldName;
        Class<?> fieldType;
        int fieldCount = fields.length;


            for (Field field : fields) {

                if (!field.isAccessible()) field.setAccessible(true);
                fieldName = field.getName();
                fieldType = field.getType();

                if (fieldName.compareTo("") == 0) {
                    continue;
                }

                if (count == fieldCount - 1) {
                    Log.d("FUND", field.getName() + " == " +  fieldType);
                } else {

                }

                count++;

            }
        }catch (Exception e){
            RX.onError(e);
        }

        return count;
    }

    public static Map<String, Object> loadMap(JSONObject json) throws JSONException {

        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = jsonToMap2(json);
        }
        return retMap;
    }

    public static Map<String, Object> jsonToMap2(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = jsonToMap2((JSONObject) value);
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
                value = jsonToMap2((JSONObject) value);
            }
            list.add(value);
        }

        return list;
    }

}

