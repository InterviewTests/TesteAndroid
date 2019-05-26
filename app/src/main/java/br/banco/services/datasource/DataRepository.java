package br.banco.services.datasource;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import br.banco.services.app.config.ConfigServers;
import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.ILoad;
import br.banco.services.contact.LoadModel;
import br.banco.services.contact.LoadPresenter;
import br.banco.services.datasource.remote.RestTask;

public class DataRepository implements IDataSource {

    /**
     *  1 - carrega do web
     *  2 - carrega da cache
     *  3 - salva arquivo
     *
     */

    private ConfigServers servers;
    public static ILoad.Presenter presenter;

    private String SERVER_URL = null;
    private String SERVER_AREA = null;

    private  final String TAG = "FUND";
    ReactAplication RX = new ReactAplication();
    Context context;


    public DataRepository(Context c){

        this.context = c;
        presenter = new LoadPresenter();




        RX.onNext("@context = " + (context!=null) );

         SERVER_AREA = "cells";
         servers = new ConfigServers();
         SERVER_URL = servers.getDataServer(SERVER_AREA) ;

        if (SERVER_URL != null) {

             RX.onNext("@SERVIDOR = " + SERVER_URL + " = " + SERVER_AREA);

        }else{
            //RX.onNext("nao encontrada...");
        }


        // PACKAGE_NAME = context.getApplicationContext().getPackageName();
        // boolean TESTE_CASE  = testeCaseFlow();

    }




    public void onCompleted(String output){

        RX.onNext("onCompleted ..... output -> " + output);

        LoadModel model = new LoadModel(presenter);
        model.processFinish(output);



    }

    public void onErrorTask(String output){

        RX.onNext("erro");
    }

    public void onNextTask(String output){

        onCompleted(output);
        RX.onNext("sucess..");
    }




    /**
     * carrega web, local, prefs
     *
     *
     */



    public String onLoad(Context context, String area){
        boolean exits = false;

        RestTask rest = new RestTask(context);
        rest.delegate = this;
        rest.execute(SERVER_URL);

        RX.onNext("Carregar da web... "  );
        String localStr = null;
        return localStr;

    }

    public  boolean onSave(String contentStr, String localDir, String fileName, Context c){
        boolean saveBool = false;

        File file;
        FileOutputStream outputStream;

        try {
            file = new File(localDir, fileName);

            outputStream = new FileOutputStream(file);
            outputStream.write(contentStr.getBytes());
            outputStream.close();

            saveBool = true;
            RX.onNext("" + saveBool);

        } catch (IOException e) {
            RX.onError(e);
        }

        return saveBool;
    }

    public  String onRead(String localDir, String fileName,  Context c){

        boolean read = false;
        String fileStr = null;
        StringBuilder builder = new StringBuilder();

        File file;
        BufferedReader buffer;

        try {

            file = new File(localDir, fileName);
            buffer = new BufferedReader(new FileReader(file));
            String line;

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
                RX.onNext("" + line);
            }

            fileStr = (builder!=null) ? builder.toString() : null;
            buffer.close();

            read = (fileStr.length() > 0);
            RX.onNext("" + read);

        } catch (IOException e) {
            RX.onError(e);
        }

        return fileStr;

    }

    public  boolean onClear(String dirName, String fileName, Context c){
        boolean clear = false;

        File file;
        FileOutputStream outputStream;
        try {
            file = new File(dirName, fileName);
            outputStream = new FileOutputStream(file);
            outputStream.close();
            //c.deleteFile(filePath);
            // file.delete();

            clear = (file.delete());
            RX.onNext("" + clear);

        } catch (IOException e) {
            RX.onError(e);
        }
        return  clear;
    }


    /**
     *
     *  teste case ation
     *
     *
     */


    public boolean testeCaseFlow(){

        boolean tCase = false;
        String fileName = "cells_test.json";
        String contentStr = "CONTEUDO DO TEXTO AQUI";
        String jsonFromLocal = null;
        String strSubFolder = "contact";

        //  start
        String localDir = onLoad(context, strSubFolder);

        //  save
        boolean onSaveBool =  onSave(contentStr, localDir, fileName,  context);

        //  read
        String onReadBool =  onRead(localDir, fileName, context);

        //  clear
        boolean onClearBool =  onClear(localDir,  fileName,  context);

        return tCase;
    }



}
