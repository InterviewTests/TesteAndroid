package br.banco.services.fund.interactor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.banco.services.app.utils.ReactAplication;

// screen=str,  info=array, downInfo=array , moreInfo=str

public class ConvertJsonToArray {

    public JSONObject objZero;
    public JSONObject objOne ;
    public JSONObject objTwo ;
    public JSONObject objTree ;

     JSONArray arrayOne;
     JSONArray arrayTwo;



    public ReactAplication RX;
    public ConvertJsonToArray(){
        RX = new ReactAplication();



    }

    //------------------ BUSCA OBJETOS ----------------


    //  level 3
    public JSONObject searchLevelTree(String jsonString,
                                      String clssString,
                                      String fieldString,
                                      int maxSize) {

        String levelZero = clssString;
        String nameZero = null;
        String nameOne = null;

        boolean resultOne = false;
        boolean resultTwo = false;

        try {

            objZero = new JSONObject(jsonString);
            nameZero = objZero.names().getString(0);

            objOne = objZero.getJSONObject(nameZero);
            //  objTwo = objOne.getJSONArray(nameZero);

            // Log.e("FUND","nameZero = " + nameZero);

            resultOne = (jsonString.length()>0 && jsonString.length()< maxSize);
            // resultTwo = (jsonString.length()>0 && jsonString.length()< maxSize);

            String msgFlow = ("SEARCH:" +clssString +", " +
                    " RESULT:" +  resultOne  + "," +
                    " SIZE:"+jsonString.length()+"," +
                    " MAX SIZE:" + maxSize);

            // RX.onNext("objZero.length():" + objZero.length());

            if(resultOne) {

                for (int i = 0; i < objOne.length(); i++) {

                    String key = objOne.names().getString(i) +"";
                    String val = objOne.get(objOne.names().getString(i)) + "";
                    // Log.v("FUND",  "@KEY: " +key + " @VALUE:" + val);

                    if(key.equals(clssString+"")) {

                        // objTwo = objOne.getJSONArray(key);
                        objTwo = objOne.getJSONObject(key);
                       // Log.v("FUND",  "@objTwo: " +key + " @length:" + objTwo.length());

                        for (int i2 = 0; i2 < objTwo.length(); i2++) {

                            String key2 = objTwo.names().getString(i2) +"";
                            String val2 = objTwo.get(objTwo.names().getString(i2)) + "";
                            //Log.v("FUND",  "@KEY: " +key2 + " @VALUE:" + val2);

                            if(key2.equals(fieldString+"")) {

                                objTree = objTwo.getJSONObject(key2);
                                Log.v("FUND",  "@objTree: " +key + " @length:" + objTree.length());

                                for (int i3 = 0; i3 < objTree.length(); i3++) {

                                    String key3 = objTree.names().getString(i3) +"";
                                    String val3 = objTree.get(objTree.names().getString(i3)) + "";

                                   Log.v("FUND",  "@KEY: " +key3 + " @VALUE:" + val3);

                                }

                            }


                        }

                        //nameOne = objZero.names().getString(0);
                    }

                }

                // RX.onNext(msgFlow);
                RX.onNext("resultOne:" + resultOne);
            }else{
                RX.onNext("resultOne:" + resultOne);
            }

        }catch(JSONException e){
            Log.e("FUND", e.getMessage());
            // RX.onError(e);
        }catch (Exception e){
            RX.onError(e);
        }

        return objZero;
    }


    // level 1
    public JSONObject searchLevelOne(String jsonString,
                                     String clssString,
                                     int maxSize) {

        String levelZero = clssString;
        String nameZero = null;
        String nameOne = null;

        boolean resultOne = false;
        boolean resultTwo = false;

        try {

             objZero = new JSONObject(jsonString);
             nameZero = objZero.names().getString(0);

             objOne = objZero.getJSONObject(nameZero);
            //  objTwo = objOne.getJSONArray(nameZero);

           // Log.e("FUND","nameZero = " + nameZero);

             resultOne = (jsonString.length()>0 && jsonString.length()< maxSize);
            // resultTwo = (jsonString.length()>0 && jsonString.length()< maxSize);

             String msgFlow = ("SEARCH:" +clssString +", " +
                                " RESULT:" +  resultOne  + "," +
                                " SIZE:"+jsonString.length()+"," +
                                " MAX SIZE:" + maxSize);

           // RX.onNext("objZero.length():" + objZero.length());

           if(resultOne) {

               for (int i = 0; i < objOne.length(); i++) {

                    String key = objOne.names().getString(i) +"";
                    String val = objOne.get(objOne.names().getString(i)) + "";
                   // Log.v("FUND",  "@KEY: " +key + " @VALUE:" + val);

                    if(key.equals(clssString+"")) {

                       // objTwo = objOne.getJSONArray(key);
                       objTwo = objOne.getJSONObject(key);
                       Log.v("FUND",  "@objTwo: " +key + " @length:" + objTwo.length());

                        for (int i2 = 0; i2 < objTwo.length(); i2++) {

                           String key2 = objTwo.names().getString(i2) +"";
                           String val2 = objTwo.get(objTwo.names().getString(i2)) + "";
                           Log.v("FUND",  "@KEY: " +key2 + " @VALUE:" + val2);

                         }

                        //nameOne = objZero.names().getString(0);
                    }

              }

              // RX.onNext(msgFlow);
               RX.onNext("resultOne:" + resultOne);
           }else{
               RX.onNext("resultOne:" + resultOne);
           }

        }catch(JSONException e){
            Log.e("FUND", e.getMessage());
           // RX.onError(e);
        }catch (Exception e){
            RX.onError(e);
        }

        return objZero;
    }


    // level 0
    public JSONObject searchLevelZero(String jsonString,
                                      String clssString,
                                      int maxSize) {

        String levelZero = clssString;
        boolean resultOne = false;

        try {

            objZero = new JSONObject(jsonString);
            objOne = objZero.getJSONObject(clssString);
            objOne = objZero.getJSONObject(clssString);

            resultOne = (jsonString.length()>0 && jsonString.length()< maxSize);

            String msgFlow = ("SEARCH:" +clssString +", " +
                    " RESULT:" +  resultOne  + "," +
                    " SIZE:"+jsonString.length()+"," +
                    " MAX SIZE:" + maxSize);

            if(resultOne) {

                RX.onNext(msgFlow);

                //Log.v("FUND", "" +mapKey + " = " + mapValue);

                for (int i = 0; i < objOne.names().length(); i++) {

                    String key = objOne.names().getString(i) +"";
                    String val = objOne.get(objOne.names().getString(i)) + "";
                    Log.v("FUND",  "KEY:" +key + " VALUE:" + val);

                }

                RX.onNext("SUCESSO");
            }else{
                RX.onNext("resultOne:" + resultOne);
            }

        }catch(JSONException e){
            RX.onError(e);
        }catch (Exception e){
            RX.onError(e);
        }

        return objZero;
    }



    //------------------ BUSCA ARRAYS ----------------


    //level 2
    public JSONObject searchLevelOneArray(String jsonString,
                                          String clssString,
                                          int maxSize) {

        String levelZero = clssString;
        String nameZero = null;
        String nameOne = null;

        boolean resultOne = false;
        boolean resultTwo = false;
        JSONArray arraTwo;

        try {

            objZero = new JSONObject(jsonString);
            nameZero = objZero.names().getString(0);
            objOne = objZero.getJSONObject(nameZero);
            //  objTwo = objOne.getJSONArray(nameZero);
            // Log.d("FUND","nameZero = " + nameZero);

            resultOne = (jsonString.length()>0 && jsonString.length()< maxSize);
            // resultTwo = (jsonString.length()>0 && jsonString.length()< maxSize);

            String msgFlow = ("SEARCH:" +clssString +", " +
                    " RESULT:" +  resultOne  + "," +
                    " SIZE:"+jsonString.length()+"," +
                    " MAX SIZE:" + maxSize);

            // RX.onNext("msgFlow:" + msgFlow);

            if(resultOne) {

                for (int i = 0; i < objOne.length(); i++) {

                    String key = objOne.names().getString(i) +"";
                    String val = objOne.get(objOne.names().getString(i)) + "";
                    //Log.v("FUND",  "@KEY: " +key + " @VALUE:" + val);

                    //RX.onNext("msgFlow:" + msgFlow);
                    if(key.equals(clssString+"")) {

                        //objTwo = objOne.getJSONArray(key);
                        // objTwo = objOne.getJSONObject(key);
                        arrayOne = objOne.getJSONArray(key);
                        //Log.d("FUND",  "@arrayOne: " +arrayOne + " @length:" + arrayOne.length() + "");

                        for (int i2 = 0; i2 < arrayOne.length(); i2++) {


                            // String key2 = objTwo.names().getString(i2) +"";
                            //String key2 = objTwo.names().getString(i2) +"";

                            Log.d( "FUND", "OBJ = " + arrayOne.get(i2).toString());

                            // String val2 = objTwo.get(objOne.names().getString(i)) + "";

                            // Log.v("FUND",  "@KEY: " +key2 + " @VALUE:" + key2);

                        }

                        //nameOne = objZero.names().getString(0);
                    }

                }

                // RX.onNext(msgFlow);
                RX.onNext("resultOne:" + resultOne);
            }else{
                RX.onNext("resultOne:" + resultOne);
            }

        }catch(JSONException e){
            RX.onError(e);
        }catch (Exception e){
            RX.onError(e);
        }

        return objZero;
    }




    //level 3
    public JSONObject searchLevelTreeArray(String jsonString,
                                          String clssString,
                                           String fieldString,
                                          int maxSize) {

        String levelZero = clssString;
        String nameZero = null;
        String nameOne = null;

        boolean resultOne = false;
        boolean resultTwo = false;
        JSONArray arraTwo;

        try {

            objZero = new JSONObject(jsonString);
            nameZero = objZero.names().getString(0);
            objOne = objZero.getJSONObject(nameZero);
            //  objTwo = objOne.getJSONArray(nameZero);
            // Log.d("FUND","nameZero = " + nameZero);

            resultOne = (jsonString.length()>0 && jsonString.length()< maxSize);
            // resultTwo = (jsonString.length()>0 && jsonString.length()< maxSize);

            String msgFlow = ("SEARCH:" +clssString +", " +
                    " RESULT:" +  resultOne  + "," +
                    " SIZE:"+jsonString.length()+"," +
                    " MAX SIZE:" + maxSize);

            // RX.onNext("msgFlow:" + msgFlow);

            if(resultOne) {

                for (int i = 0; i < objOne.length(); i++) {

                    String key = objOne.names().getString(i) +"";
                    String val = objOne.get(objOne.names().getString(i)) + "";
                    //Log.v("FUND",  "@KEY: " +key + " @VALUE:" + val);

                    //RX.onNext("msgFlow:" + msgFlow);
                    if(key.equals(clssString+"")) {

                        //objTwo = objOne.getJSONArray(key);
                        // objTwo = objOne.getJSONObject(key);
                        arrayOne = objOne.getJSONArray(key);
                        //Log.d("FUND",  "@arrayOne: " +arrayOne + " @length:" + arrayOne.length() + "");

                        for (int i2 = 0; i2 < arrayOne.length(); i2++) {

                            // String key2 = objTwo.names().getString(i2) +"";
                            //String key2 = objTwo.names().getString(i2) +"";

                             String key2 = arrayOne.get(i2).toString() +"";
                            Log.d( "FUND", "ARRAY = " + key2);

                            if(key2.equals(fieldString+"")) {


                                // nameZero = arrayOne.getString(key2.toS);

                                //objTwo = arrayOne.getJSONObject(key2);
                                // arrayTwo = arrayOne.getJSONArray(key2);
                               //  JSONArray arrAtores = arrayTwo.getJSONObject();

                                //Log.d( "FUND", "objTwo = " + key2.length());

                               // for (int i3 = 0; i3< objTwo.length(); i3++) {

                                    // String key2 = objTwo.names().getString(i3) +"";
                                    //String key2 = objTwo.names().getString(i3) +"";

                                  //  Log.v("FUND", "@KEY: " + key2 + " @VALUE:" + key2);
                               // }

                            }

                        }

                        //nameOne = objZero.names().getString(0);
                    }

                }

                // RX.onNext(msgFlow);
                RX.onNext("resultOne:" + resultOne);
            }else{
                RX.onNext("resultOne:" + resultOne);
            }

        }catch(JSONException e){
            RX.onError(e);
        }catch (Exception e){
            RX.onError(e);
        }

        return objZero;
    }









}

