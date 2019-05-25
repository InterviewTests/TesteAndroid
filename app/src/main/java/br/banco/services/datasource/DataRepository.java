package br.banco.services.datasource;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import br.banco.services.app.utils.ReactAplication;

public class DataRepository {


    ReactAplication RX = new ReactAplication();
    private  final String TAG = "FUND";
    Context context;

    /**
     *
     *  teste case
     *
     */

    public DataRepository(Context c){
        this.context = c;

        // PACKAGE_NAME = context.getApplicationContext().getPackageName();
        // boolean TESTE_CASE  = testeCaseFlow();

    }

    /**
     *
     *  actions
     *
     */


    public String onLoad(Context c, String folderStr){
        boolean exits = false;

        String localStr = null;
        File nameDir = null;
        try {
            nameDir = c.getFilesDir();
            localStr = nameDir.toString();

            exits = (localStr.length() > 0);
            RX.onNext("" + exits + "->" + localStr);

        }catch (Exception e){
            RX.onError(e);
        }

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
