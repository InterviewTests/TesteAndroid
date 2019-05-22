package br.banco.services.fund.data.local;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadFileLocalReal {


    public String loadExternalJson(String jsonURL) throws IOException {

      //  Log.d("FUND","MODEL / "+getClass().getName()+" -> " + jsonURL);

        //FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);


        StringBuilder SB = new StringBuilder();
        String fileName = "/sdcard/PROD_COD.txt";

        String line = null;

        try {

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                SB.append(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.e("FUND",  "Arquivo nao pode ser aberto: '" + fileName + "'");
        }
        catch(IOException ex) {
            Log.e("FUND", "O arquivo nao pode ser lido '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }


        return SB.toString();

    } //loadExternalJson





}




