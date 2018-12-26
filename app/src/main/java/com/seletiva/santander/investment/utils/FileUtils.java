package com.seletiva.santander.investment.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utilitarios para leitura de files
 */
public class FileUtils {
    /**
     * Carrega arquivo (para fins de mock)
     * @param inFile nome fo file
     * @param context contexto
     * @return conteudo do file em formato de string
     */
    public static String loadData(String inFile, Context context) {
        String fileContent = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            fileContent = new String(buffer);
        } catch (IOException e) {}

        return fileContent;
    }
}
