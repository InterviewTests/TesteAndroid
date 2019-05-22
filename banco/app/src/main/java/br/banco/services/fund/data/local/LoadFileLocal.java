package br.banco.services.fund.data.local;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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




