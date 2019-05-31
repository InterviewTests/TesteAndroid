package br.banco.services;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.banco.services.contact.domain.Produto;
import br.banco.services.contact.domain.ResponseJson;

public class Teste extends AppCompatActivity {

    final String TAG = "LOADR";

    public ExecutorService executor;
    public String FILE_DATA;
    Button button;
    TextView tvTest;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);
        button = (Button) findViewById(R.id.btTest);
        tvTest = (TextView) findViewById(R.id.tvTest);

        button.setOnClickListener( new View.OnClickListener() {

        @Override
        public void onClick(View v)  {

            userCaseTeste();

            }
        });



        userCaseTeste();

    }



    public void userCaseTeste(){

        String  loadDataTest; // 01>
        List<Produto> gsonTolistTest = new ArrayList<>(); // 02>
        HashMap<String, String> listToMapTest = new HashMap<>(); // 03>
        Map<String, String> gsonToMapTest = new HashMap<>(); //03 <


        //loadDataTest =   loadDataLocal(null);
        //loadDataFrom = loadData(null);
         gsonTolistTest = gsonToList(loadDataLocal(null));
        // listToMapTest =  listToMap(gsonTolistTest);
         gsonToMapTest = gsonToMap(gsonTolistTest);

       // Log.e(TAG,"TESTCASE > listToMapTest=" + (listToMapTest != null));


    }




    public void showData(ArrayList<Produto> produto){



        String testeItens =  "Produto{" +
                "id='" + produto.get(0).getId() + '\'' +
                ", type='" + produto.get(0).getType() + '\'' +
                ", message='" + produto.get(0).getMessage() + '\'' +
                ", typefield='" + produto.get(0).getTypefield() + '\'' +
                ", hidden='" + produto.get(0).getTopSpacing() + '\'' +
                ", topSpacing='" + produto.get(0).getTopSpacing() + '\'' +
                ", show='" + produto.get(0).getShow() + '\'' +
                ", required='" + produto.get(0).isRequired() + '\'' +
                '}';


        //tvTest.setText(testeItens);

        Log.d(TAG, "M/TESTE/response/" + (produto.size()));

    }


    public String loadData(String area) {

        Log.e("LOADR","M/loadFile/>Carregando json..." + area);


        //SERVER_URL =  new ConfigServers().getDataServer(area);
        String SERVER_URL = "http://www.issam.com.br/lab/acento/cels1.txt";

        try {
            URL url = new URL(SERVER_URL);
            StringBuilder SB = new StringBuilder();
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            // Log.d(TAG, "buffer = " + (buffer!=null));
            String line = null;
            while ((line = buffer.readLine()) != null) {

                SB.append(line);
                // Log.d(TAG, "" + line);
            }
            buffer.close();


            FILE_DATA = SB.toString();
            // Log.d(TAG, "FILE_DATA = " + FILE_DATA);

            //  Log.d(TAG, "M/loadData/listaArray/" + listaArray.size() );

        } catch (Exception e) {
            // errorData( "", 4);
            //nextData(area);
            Log.d(TAG, "Erro ao carregar arquivo!" + (e));
        }


        return FILE_DATA;
    }


    public List<Produto> gsonToList(String listStr){

          if(listStr==null){return null;}
          if(listStr.length()< 100 ){return null;}
          if(listStr.length()> 1000 ){return null;}

          //Log.e(TAG, "M/gsonToList/listStr=" + listStr.length());

          List<Produto> list = new ArrayList<Produto>();

         try {

                Gson GS = new Gson();
                ResponseJson response = new ResponseJson();
                response = GS.fromJson(listStr, ResponseJson.class);
                Log.e(TAG, "M/gsonToList/response="+(response.getAndroid().size())+" / " + response.getAndroid().get(0).getMessage() );

                list = response.getAndroid();

                //Log.e(TAG, "M/gsonToList/" + listStr.length());

              }
            catch (JsonParseException ex) { }
            catch (IndexOutOfBoundsException ex) { }
            catch (Exception ex) { }


        return list;
    }

    /**
     *
     *  1 - > converte List em String
     *  2 - > Converte String em Map
     *  3 - > Salva em Pref
     *
     * @param list
     * @return
     */


    public HashMap<String, String> gsonToMap(List<Produto> list) {

        HashMap<String, String> map = new HashMap<>();
        if(list==null){return null;}
         if(!(list.size() == 6)){return null;}

        try {

            Gson gson;
            Type type;
            String gsonToStr;

            for(int i=0; i<list.size(); i++){
                gson = new Gson();
                type = new TypeToken<Map<String, String>>(){}.getType();

                gsonToStr = gson.toJson(list.get(i));
                Map<String, String> strToMap = gson.fromJson(gsonToStr, type);

                //Log.e(TAG, "M/gsonToMap/list=" + gsonToStr);
                Log.e(TAG, "--- elmento " +i+"--- ");

                for (Map.Entry<String, String> entry : strToMap.entrySet()) {

                    Log.e(TAG, "M/gsonToMap/strToMap=" + entry.getKey() + "=" + entry.getValue());
                }

            }



        }

        catch (JsonParseException ep) {
            Log.e(TAG, "M/gsonToMap/parser=" + ep);
        }
        catch (IndexOutOfBoundsException eb) {
            Log.e(TAG, "M/gsonToMap/bound=" + eb);
        }
        catch (Exception ex) {
            Log.e(TAG, "M/gsonToMap/ecep=" + ex);
        }
        return map;
    }


    public HashMap<String, String> listToMap(List<Produto> list) {
        HashMap<String, String> map = new HashMap<>();

        Log.e(TAG,"M/listToMap/list:" + (list.size()));

        for (Produto produto : list) {
             // HashMap<String, String> map = new HashMap<>();
              //map.put(KEY_VER, conteudo.getId());
             // Log.d(TAG, "dataToMap:" + produto.getId());
              Log.d(TAG, "listToMap:" + produto.getMessage());
        }

        return map;
    }








    public String loadDataLocal(String area){


           //FILE_DATA = "{'android':[{'id':1,'type':2,'message':'Olá,primeiro se apresente com o seu nome:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false}]}";
         FILE_DATA = "{'android':[{'id':1,'type':2,'message':'Olá,primeiro se apresente comoseunome:','typefield':null,'hidden':false,'topSpacing':60.0,'show':null,'required':false},{'id':2,'type':1,'message':'Nomecompleto','typefield':1,'hidden':false,'topSpacing':35.0,'show':null,'required':true},{'id':4,'type':1,'message':'Email','typefield':3,'hidden':true,'topSpacing':35.0,'show':null,'required':true},{'id':6,'type':1,'message':'Telefone','typefield':'telnumber','hidden':false,'topSpacing':10.0,'show':null,'required':true},{'id':3,'type':4,'message':'Gostaria de cadastr armeu email','typefield':null,'hidden':false,'topSpacing':35.0,'show':4,'required':false},{'id':7,'type':5,'message':'Enviar','typefield':null,'hidden':false,'topSpacing':10.0,'show':null,'required':true}]}";


        return FILE_DATA;
    }







    String itens = null;

    public void onNext() {
        itens = null;

        //loadData(null);
    }

    public void onError() {
        itens = null;
    }

    public void onComplete() {

        itens = "1";
    }





    public Object objectToClass(Class c){

        Object object = null;

        try {
            object = c.newInstance();
        } catch (InstantiationException ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
           // Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  object;
    }

    }

