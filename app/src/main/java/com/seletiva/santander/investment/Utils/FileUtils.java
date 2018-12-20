package com.seletiva.santander.investment.Utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    public static String loadData(String inFile, Context context) {
        String fileContent = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            fileContent = new String(buffer);
        } catch (IOException e) {
        }

        return fileContent;

    }
}
