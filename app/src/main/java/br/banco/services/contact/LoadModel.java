package br.banco.services.contact;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.banco.services.app.config.ConfigServers;
import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.domain.ContactForm;
import br.banco.services.contact.domain.Produto;
import br.banco.services.contact.domain.ResponseJson;
import br.banco.services.datasource.DataRepository;
import br.banco.services.datasource.local.contact.FormPreferences;
import br.banco.services.datasource.local.contact.UserPreferences;

public class LoadModel {  //implements ILoad.Model

   private WeakReference<Context> contextRef;
   public final String TAG = "LOADR";

   public static ILoad.Presenter presenter;
   public DataRepository repository;
   public Context context;

   private String SERVER_URL;
   private String FILE_DATA;

    public Handler handler;
    public Runnable runnable;


    public ArrayList<Produto> produtos = new ArrayList<Produto>();
    public HashMap<String, String> listItens;

   ReactAplication RX = new ReactAplication();

   ContactForm form;


    public LoadModel(ILoad.Presenter presenter){

     this.presenter = presenter;


    // form = new ContactForm();

     // presenter = new LoadPresenter()
       //contextRef = new WeakReference<>(presenter.g);

      //RX.onNext("[context=" + (context!=null) +"]" );
      //this.context = presenter.getContext();




   }

    public String loadData2(String area, Context context){

       this.context = context;
       final String fileStr;
       SERVER_URL =  new ConfigServers().getDataServer(area);

       SERVER_URL = "http://www.issam.com.br/lab/acento/produto3.txt";
       RX.onMessage(TAG, "M/loadData / SERVER_URL=" + SERVER_URL +" / " , context );



        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

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

           Gson GS = new Gson();

           ResponseJson response = new ResponseJson();
           response = GS.fromJson(FILE_DATA, ResponseJson.class);

           List<Produto> listaArray = new ArrayList<Produto>();
           //listaArray = (new ResponseJson().getAndroid());

           listaArray = response.getAndroid();

           //createMap(new ResponseJson().getAndroid());
           //createMap(listaArray);


           Log.d(TAG, "M/loadData/Handler/" + listaArray.size() );

           // errorData( "", 4);
           // savetData(FILE_DATA);



           } catch (Exception e) {

               errorData( "", 4);
               //nextData(area);

               Log.d(TAG, "Erro ao carregar arquivo!" + (e));
           }



            }
        };
        // handler.post(runnable);
        handler.removeCallbacks(runnable);
       // Looper myLooper = Looper.myLooper();
        //if (myLooper!=null) {
           // myLooper.quit();
            //handler.getLooper().quit();
       // }



       fileStr = FILE_DATA;
       return fileStr;
   }

    public HashMap<String, String> createMap(List<Produto> list){
        HashMap<String, String> map = new HashMap<>();

        if(list.size()>0) {

            for (Produto conteudo : list) {
                map.put("nome", conteudo.getNome());
                map.put("codigo", conteudo.getCodigo());
                //Log.d(TAG, "NOME = " + conteudo.getNome());
                //Log.d(TAG, "CPDOGP = " + conteudo.getCodigo());
            }
        }
        saveData(map);
        return map;
    }

    public boolean saveData(HashMap<String, String> createMap){

         boolean saveFile = false;
         listItens = createMap;


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                new FormPreferences(context).onEdit(listItens, context);
            }
        };
        // handler.post(runnable);
        handler.removeCallbacks(runnable);



        FormPreferences pref = new FormPreferences(context);
        saveFile = pref.isSPStart();

        Log.d(TAG, "Arquivo Salvo com sucesso! " + saveFile);

        onCompletedData( saveFile, context);


        return  saveFile;
    }



    public String loadData(String area, Context context) {
      String teste = "1";


        Log.e(TAG,"antes..aaaaa.....");


        ExecutorService exec = Executors.newSingleThreadExecutor();



        return  teste;

    }




        /**
         *
         *
         * verifica as pefs locais
         *
         *
         */



    public void errorData(String message, int msgCode){

        presenter.onCompletedTask(message);

    }


    public void onCompletedData(boolean status, Context context){

        if(status){

            presenter.onErrorTask("",2);

        }else{

            presenter.onErrorTask("",2);

        }

        //RX.onNext("[context=" + (context!=null) +"]" );
        // presenter.onCompleted( form, 1);

   }


    public void clearData(ContactForm form, Context context){


   }






    public void setView(ILoad.Presenter p){
        this.presenter = p;
       // contextRef = new WeakReference<>(getContext());
    }

    public Context getContext() {
        return (Context) presenter;
    }


}








    /*


    public void run() {
          Looper.prepare();

          mHandler = new Handler() {
              public void handleMessage(Message msg) {
                  // process incoming messages here
              }
          };

          Looper.loop();
      }









    handler = new Handler();
    runnable = new Runnable() {
        @Override
        public void run() {



        }
    };
    handler.post(timer);
    handler.removeCallbacks(runnable);







         //Looper.prepare();
        handler.post(new Runnable()
       {
           @Override
           public void run()
           {





           }

       });
        handler.removeCallbacks(runnable);
       // Looper.loop();











            new Thread(new Runnable() {
                @Override
                public void run() {

               }

           }).start();




   new Handler(Looper.getMainLooper()).post(new Runnable() {
            #Override
            public void run() {
                // this will run in the main thread
            }
        });











 */