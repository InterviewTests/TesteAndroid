package br.banco.services.app.utils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonConvertV1 {

    public ReactAplication RX;
    public JsonConvertV1(){


    }

    public void startTask(String jsonString) {

        String jsonStr = "{" +
                "\"devmovies\":" +
                "{" +
                "\"filme\":" +
                "[" +
                "{" +
                "\"id\":1," +
                "\"titulo\":\"Os Arquivos JSON\"," +
                "\"ano\":1998," +
                "\"resumo\":\"A história dos arquivos muito leves\"," +
                "\"generos\":[\"Ação\",\"Sci-fi\",\"Drama\"]," +
                "\"elenco\":[\"Gillian Triggerson\",\"David Markupovny\"]" +
                "}," +
                "{" +
                "\"id\":2," +
                "\"titulo\":\"Sexta-feira 13: JSON Vive\"," +
                "\"ano\":1986," +
                "\"generos\":[\"Ação\",\"Horror\"]," +
                "\"elenco\":[\"Ann Labelvalue Pair\", \"Jennifer Json\", \"John Java\"]" +
                "}" +
                "]" +
                "}" +
                "}";


        try {


            JSONObject devMovies = new JSONObject(jsonStr);
            JSONObject filmes = devMovies.getJSONObject("devmovies");
            JSONArray arrFilmes = filmes.getJSONArray("filme");


            for (int i = 0; i < arrFilmes.length(); i++) {

                //recupera filme de índice "i" no array
                JSONObject f = ((JSONArray) arrFilmes).getJSONObject(i);

                System.out.println("id: " + f.getInt("id"));
                System.out.println("titulo: " + f.getString("titulo"));
                System.out.println("ano: " + f.getInt("ano"));

                Log.d("FUND","resumo: " + f.optString("resumo", "-"));

                //gêneros
                JSONArray arrGeneros = f.getJSONArray("generos");
                for (int k = 0; k < arrGeneros.length(); k++) {
                    Log.d("FUND", "genero " + (k + 1) + ": " + arrGeneros.getString(k));
                }

                //elenco
                System.out.println("elenco: ");

                JSONArray arrAtores = f.getJSONArray("elenco");
                for (int j = 0; j < arrAtores.length(); j++) {
                    Log.d( "TAG", "\t" + arrAtores.getString(j));
                }

                System.out.println();

            } // for

            RX.onNext("SUCESSO");
        }catch(JSONException e){
            RX.onError(e);
        }
        catch (Exception e){
            RX.onError(e);
        }

    }





}
