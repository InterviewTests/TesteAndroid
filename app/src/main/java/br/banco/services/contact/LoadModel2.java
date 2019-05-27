package br.banco.services.contact;

import android.app.ProgressDialog;
import android.content.Context;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.banco.services.R;
import br.banco.services.contact.domain.Produto;
import br.banco.services.contact.domain.ResponseJson;

public class LoadModel2  implements ILoad.Model2  {

    final String TAG = "LOADR";

    public ExecutorService executor;
    public ControlTasks mThread;
    public Handler mHandler;
    final int TOTAL_TASK = 1;
    private String ACTUAL_TASK;

    public static ILoad.Presenter presenter;

    private String SERVER_URL;
    private String FILE_DATA;
    private String APP_AREA;
    public Context context;
    

    /*
    @Override
    public void LoadModel2(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.teste);
        setContentView(R.layout.activity_load);

        initThread();
    }
    */



    public LoadModel2(ILoad.Presenter presenter){

        this.presenter = presenter;

        initThread();

    }



    /**
     *  status das mensagens gerais
     */

    public void onCompleted(Boolean message, Context context){


    }

    public void onError(String message, int code){


    }

    public void onNext(String message, int code){

        presenter.onCompletedTask("");

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

                    Thread.sleep(5000);


                } catch (InterruptedException e) {
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

            /**
             *
             *  carregar web
             *
             */

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


            SERVER_URL = "http://www.issam.com.br/lab/acento/produto3.txt";

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

            } catch (Exception e) {
               // errorData( "", 4);
                //nextData(area);
                Log.d(TAG, "Erro ao carregar arquivo!" + (e));
            }

            return FILE_DATA;
        }



        
    }




}
