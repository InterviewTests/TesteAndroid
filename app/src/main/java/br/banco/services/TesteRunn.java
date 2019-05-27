package br.banco.services;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TesteRunn extends AppCompatActivity  {

    final String TAG = "LOADR";

    public ExecutorService executor;


    ControlTasks mThread;
    ProgressDialog mDialog;
    Handler mHandler;

    final int TOTAL_LIST_TASKS = 1;
    final int TOTAL_TIME_TASK = 10000;

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);
        button = (Button) findViewById(R.id.btTest);

        button.setOnClickListener(new  View.OnClickListener() {
            public void onClick(View v) {
                startThread();
            }
        });


        startThread();
    }

    /**
     *
     *  status das mensagens geraus
     *
     * @param message
     * @param code
     */



    public void onCompleted(String message, int code){


    }

    public void onError(String message, int code){


    }

    public void onNext(String message, int code){


    }




    /**
     *
     *  caregamento web e local
     *
     */


    private void startThread() {

        mDialog = ProgressDialog.show(this, "Aguarde",
                "Processando...", false, false);

        mHandler = new Handler();
        mThread = new ControlTasks(TOTAL_LIST_TASKS);
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

                    Thread.sleep(TOTAL_TIME_TASK);


                } catch (InterruptedException e) {

                    onError("",1);
                    Log.e(TAG, "-> Erro ao carregar da web: " + (e) );
                }
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();


                    if(1==1){

                        onCompleted("", 1 );

                    }else{

                        onError("",1);
                    }


                    Log.e(TAG, "Completada...");


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

            loadFile(nameTask);

            mHandler.post(new Runnable() { @Override public void run() {
                    Log.e(TAG, nameTask + "-> Finalizado: " + nameTask);
                }
            });


        }






        private void loadFile(String nametask) {
            Log.e("LOADR","M/loadFile/"+nametask+"->Carregando json..." + nametask);

        }


        private void saveFile(String nametask) {
            Log.e("LOADR","M/saveFile/"+nametask+"->Salvando json..." + nametask);

        }


        
    }




}
