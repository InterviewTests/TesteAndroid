package br.banco.services.datasource;

import android.content.Context;


public interface ILoadTask {

    // model
    void processFinish(String output);
    //void convertFinish(List<Object> listScreen, String message); // object


     interface IFileTask{
        String  onLoad(Context c, String folder);
        boolean onSave(String contentStr, String localDir, String fileName, Context context);
        String  onRead(String localDir, String fileName, Context context);
        boolean onClear(String dirName, String fileName, Context ccontext);
    }

     interface IFileRemote{
        String onLoad(Context c, String folder);

    }




}
