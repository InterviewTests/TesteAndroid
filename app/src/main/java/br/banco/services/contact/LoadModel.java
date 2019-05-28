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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.banco.services.R;
import br.banco.services.app.config.ConfigServers;
import br.banco.services.contact.domain.Produto;
import br.banco.services.contact.domain.ResponseJson;
import br.banco.services.datasource.local.contact.FormPreferences;

import static android.content.Context.MODE_PRIVATE;

public class LoadModel implements ILoad.Model2  {

    final String TAG = "LOADR";


    public ExecutorService executor;
    public ControlTasks mThread;
    public Handler mHandler;
    final int TOTAL_TASK = 1;
    final int TOTAL_TIME = 10000; // 10s
    private String ACTUAL_TASK;


    //private WeakReference<Context> contextRef;

    public static SharedPreferences preferences;
    public static ILoad.Presenter presenter;
    public FormPreferences prefs;

    private String SERVER_URL;
    private String FILE_DATA;
    private String APP_AREA;
    public Context context;

    public HashMap<String, String> listItens;


    public LoadModel(ILoad.Presenter presenter){

        this.presenter = presenter;

        //

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
                Runnable RemoteTasks = new RemoteTasks("task" +i, 2);
                executor.submit(RemoteTasks);
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





    private class RemoteTasks implements Runnable {
        private int numLoops;
        private String nameTask;
        public RemoteTasks(String nameTask, int loops) {
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

        public String loadData(String area) {
            Log.e("LOADR","M/loadFile/"+ACTUAL_TASK+"->Carregando json..." + area);


            //SERVER_URL =  new ConfigServers().getDataServer(area);
            SERVER_URL = "http://www.issam.com.br/lab/acento/cels1.txt";

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

                /**
                 *
                 *  salvar json, converter em map, salvar prefs
                 *
                 */

                        FILE_DATA = SB.toString();


            } catch (Exception e) {
               // errorData( "", 4);
                //nextData(area);
                Log.d(TAG, "Erro ao carregar arquivo!" + (e));
            }




            return FILE_DATA;
        }


        public HashMap<String, String> createMap(List<Produto> list){
            HashMap<String, String> map = new HashMap<>();
            Log.d(TAG, "M/createMap/list/" + (list != null) );

            if(list.size()>0) {

                for (Produto conteudo : list) {
                    map.put("getId","" + conteudo.getId());
                    map.put("getMessage", conteudo.getMessage());
                    //Log.d(TAG, "NOME = " + conteudo.getNome());
                    //Log.d(TAG, "CODIGO = " + conteudo.getCodigo());
                }
            }else {
                Log.d(TAG, "#erro ao criar mapa->" + (list.size()));
            }

            saveData(map);
            return map;
        }

        public boolean saveData(HashMap<String, String> createMap){

            boolean saveFile = false;
            listItens = createMap;

            new FormPreferences(context).onEdit(listItens, context);

            return  saveFile;
        }





        
    }




}
