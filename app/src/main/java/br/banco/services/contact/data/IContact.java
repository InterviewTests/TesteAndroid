package br.banco.services.contact.data;

import android.content.Context;

public interface IContact {

    public String loadData(Context context);


    interface IFileTask{
        String onLoad(Context c, String folder);
        boolean onSave(String contentStr, String localDir, String fileName, Context c);
        String onRead(String localDir, String fileName, Context c);
        boolean onClear(String dirName, String fileName, Context c);
    }

}




