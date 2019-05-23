package br.banco.services.fund.data.local;

import android.util.Log;

import java.io.IOException;

public class LoadFileLocal {


    public String loadExternalJson(String jsonURL) throws IOException {

        StringBuilder SB = new StringBuilder();

        String fileName = "NOME_ARQUIVO_LOCAL.txt";

        try {


        } catch(Exception ex) {
            Log.e("FUND", "O arquivo nao pode ser lido '" + fileName + "'");
        }

        return SB.toString();

    } //loadExternalJson



}




