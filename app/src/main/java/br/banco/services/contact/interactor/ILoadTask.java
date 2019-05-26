package br.banco.services.contact.interactor;

import android.content.Context;

import java.util.List;


public interface ILoadTask {

    // model
    void processFinish(String output);
    void convertFinish(List<Object> listScreen, String message); // object


     interface IFileTask{
        String onLoad(Context c, String folder);
        boolean onSave(String contentStr, String localDir, String fileName, Context c);
        String onRead(String localDir, String fileName,  Context c);
        boolean onClear(String dirName, String fileName, Context c);
    }

     interface IFileRemote{
        String onLoad(Context c, String folder);

    }




}
