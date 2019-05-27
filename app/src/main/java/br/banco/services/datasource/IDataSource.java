package br.banco.services.datasource;

import android.content.Context;

public interface IDataSource {

   // public String loadData(Context context);

    void onCompleted(String output); //onCompletedTask
    void onErrorTask(String output);
    void onNextTask(String output);

    //void convertFinish(List<Object> listScreen, String message); // object

    interface IFileTask{

        String  onLoad(Context c, String folder);
        boolean onSave(String contentStr, String localDir, String fileName, Context c);
        String  onRead(String localDir, String fileName, Context c);
        boolean onClear(String dirName, String fileName, Context c);

    }

}




