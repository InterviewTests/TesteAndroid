package br.banco.services.contact.data.local;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.interactor.ILoadTask;

/**
 * Gerencia arquivo local devolve String
 * onLoad, onSave, onRead , onClear
 *
 */

public class FromFile implements ILoadTask.IFileTask {

    private  final String TAG = "LOADR";
    private String fileExtension = ".json";
    Context context;
    //static final int MAX_SIZE_CHARS = 1000;
  //  public static String PACKAGE_NAME;

    ReactAplication RX = new ReactAplication();

    public FromFile(Context c){
        this.context = c;

       // Log.d(TAG, "M/FromFile/fileName/context=" + (context!=null));

       // PACKAGE_NAME = context.getApplicationContext().getPackageName();
       // boolean TESTE_CASE  = testeCaseFlow();
    }

    public String onLoad(Context context, String folderStr){
        boolean exits = false;

        String localStr = null;
        File nameDir = null;
        try {
            nameDir = context.getFilesDir();
            localStr = nameDir.toString();

            exits = (localStr.length() > 0);
            //Log.d(TAG, "M/FromFile/onLoad/context=" + (context!=null));

        }catch (Exception e){
            RX.onError(e);
        }

        return localStr;

    }

    public  boolean onSave(String contentStr, String localDir, String fileName, Context c){

        boolean saveBool = false;
        localDir =  onLoad( context, null);
        if(contentStr==null || fileName==null) return false;
        fileName += fileExtension;

        File file;
        FileOutputStream fileOut;

            try {
                file = new File(localDir, fileName);

                fileOut = new FileOutputStream(file);
                fileOut.write(contentStr.getBytes());
                fileOut.close();

                saveBool = true;
                Log.d(TAG, "M/FromFile/fileName/onSave=" + (fileName));

            } catch (IOException e) {
                RX.onError(e);
            }

        return saveBool;
    }

    public  String onRead(String localDir, String fileName,  Context c){
        boolean saveBool = false;
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

            saveBool = (fileStr.length() > 0);
            RX.onNext("" + saveBool);

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



    public boolean testeCaseFlow(){

        boolean tCase = false;
        String fileName = "cells_test.json";
        String contentStr = "CONTEUDO DO TEXTO AQUI CONTEUDO DO TEXTO AQUI CONTEUDO DO TEXTO AQUI";
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




