package br.banco.services.contact;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.banco.services.R;
import br.banco.services.app.config.ConfigServers;
import br.banco.services.contact.data.local.FromFile;
import br.banco.services.contact.domain.Produto;
import br.banco.services.contact.domain.ResponseJson;
import br.banco.services.datasource.local.contact.FormPreferences;

import static android.content.Context.MODE_PRIVATE;

public class LoadModel implements ILoad.Model2  {

    final String TAG = "LOADR";

    /**
     *  loadData > JsonToList
     *
     */


    public ExecutorService executor;
    public ControlTasks mThread;
    public Handler mHandler;
    final int TOTAL_TASK = 1;
    final int TOTAL_TIME = 30000; // 30s
    private String ACTUAL_TASK;


    //private WeakReference<Context> contextRef;

    public static SharedPreferences preferences;
    public static ILoad.Presenter presenter;
    public FormPreferences prefs;

    private String SERVER_URL; // web
    private String APP_AREA ="cels"; // web
    private String FILE_DATA;
    public Context context;

    public HashMap<String, String> listItens;


    public LoadModel(ILoad.Presenter presenter){

        this.presenter = presenter;


        //this.getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);
       // preferences = getSharedPreferences( getPackageName() + "_preferences", MODE_PRIVATE);


    }



    /**
     *  status das mensagens gerais
     */


    public void onStartLoad(Context c){

        this.context = c;
        Log.e(TAG, "M/onStartLoad/context:" + (context!=null)  );
        initThread();
    }

    public void onCompleted(Boolean message, Context context){


    }

    public void onError(String message, int code){


    }

    public void onNext(String message, int code){

        if(context!=null) {
            FormPreferences pref = new FormPreferences(context);

        }else{

        }

        //boolean usPref = pref.isSPStart();

        Log.d(TAG, "M/onNext/FormPreferences/context:"+(context!=null));
       // presenter.onCompletedTask("");

    }


    /**
     *
     *  caregamento web e local
     *
     */


    private void initThread() {

        mHandler = new Handler();
        mThread = new ControlTasks(TOTAL_TASK);
        mThread.start();
        Log.e(TAG, "M/initThread/context:" + (context!=null)  );

    }

    private class ControlTasks extends Thread {
        private int numTasks;

        public ControlTasks(int tasks) {
            this.numTasks = tasks;
        }
        @Override
        public void run() {
            executor =  Executors.newSingleThreadExecutor();
            for (int i = 1; i <= numTasks; i++) {
                Runnable QueueTasks = new QueueTasks("task" +i, 2);
                executor.submit(QueueTasks);
            }
            executor.shutdown();
            
            
            
            while (!executor.isTerminated()) {
                try {
                    Thread.sleep(TOTAL_TIME);
                } catch (Exception e) {
                    Log.e(TAG, "-> Erro ao carregar da web: " + (e) );
                    Thread.currentThread().interrupt();
                }
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "-> Finalizando tudo...: "  );
                    onNext("", 1);
                }
            });
        }
    }


    private class QueueTasks implements Runnable {
        private int numLoops;
        private String nameTask;
        public QueueTasks(String nameTask, int loops) {
            this.nameTask = nameTask;
            numLoops = loops;
        }
        public void run() {
            mHandler.post(new Runnable(){@Override public void run() {
                    Log.e(TAG, nameTask + "-> Iniciano: " + nameTask);
            }});

            loadData(APP_AREA);

            mHandler.post(new Runnable() { @Override public void run() {
                    Log.e(TAG, nameTask + "-> Finalizado: " + nameTask);
                }
            });
        }


        /**
         *  0 - > loadData >
         *  1 - > converte List em String
         *  2 - > Converte String em Map
         *  3 - > Salva em Pref
         *
         */


        public String loadData(String area) {
            Log.e("LOADR","M/loadData/"+ACTUAL_TASK+"->Carregando json..." + area);


            //SERVER_URL =  new ConfigServers().getDataServer(area);
            SERVER_URL = "http://www.issam.com.br/lab/acento/cells.txt";

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

            } catch (Exception e) {
               // errorData( "", 4);
                //nextData(area);
                Log.d(TAG, "Erro ao carregar arquivo!" + (e));
            }


            //gsonToList(FILE_DATA); //02
            //gsonToList(FILE_DATA); //02
            saveData(FILE_DATA, APP_AREA);

            return FILE_DATA;
        }


        public List<Produto> gsonToList(String listStr){

            if(listStr==null){return null;}
            if(listStr.length() >  2000 ){return null;}

            Log.e(TAG, "M/gsonToList/listStr=" + listStr.length());

            List<Produto> list = new ArrayList<Produto>();

            try {

                Gson GS = new Gson();
                ResponseJson response = new ResponseJson();
                response = GS.fromJson(listStr, ResponseJson.class);
                Log.e(TAG, "M/gsonToList/response="+(response.getAndroid().size())+" / " + response.getAndroid().get(0).getMessage() );

                list = response.getAndroid();

                //Log.e(TAG, "M/gsonToList/" + listStr.length());

            }
            catch (JsonParseException ep) {
               Log.e(TAG, "M/gsonToList/JsonParseException=" + ep);
            }
            catch (IndexOutOfBoundsException eb) {
                Log.e(TAG, "M/gsonToList/IndexOutOfBoundsException=" + eb);
            }
            catch (Exception ex) {
                Log.e(TAG, "M/gsonToList/Exception=" + ex);
            }


           gsonToMap(list);

            return list;
        }


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
                   // Log.e(TAG, "--- elmento " +i+"--- ");

                    for (Map.Entry<String, String> entry : strToMap.entrySet()) {

                        //Log.e(TAG, "M/gsonToMap/strToMap=" + entry.getKey() + "=" + entry.getValue());
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


        public boolean saveData(String fileContent, String fileName){
       //public boolean saveData(HashMap<String, String> createMap){

            boolean saveFile = false;
            //listItens = createMap;

            Log.d(TAG, "M/saveData/fileName=" + fileName);

            FromFile fromFile = new FromFile(context);
            fromFile.onSave(fileContent, null, fileName, context);


            return  saveFile;
        }





        
    }




}
