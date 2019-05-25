package br.banco.services.datasource;

import android.content.Context;

public interface IDataSource {

    public String loadData(Context context);

    /**
     *
     *  Gerencia o carregamento de dados
     *
     *
     */

    interface IFileTask{

        String  onLoad(Context c, String folder);
        boolean onSave(String contentStr, String localDir, String fileName, Context c);
        String  onRead(String localDir, String fileName, Context c);
        boolean onClear(String dirName, String fileName, Context c);

    }

}




