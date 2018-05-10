package android.teste.com.br.testeandroidapp.utils;

import android.teste.com.br.testeandroidapp.entity.Cell;
import android.teste.com.br.testeandroidapp.entity.Screen;
import android.teste.com.br.testeandroidapp.utils.exception.JsonParserException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Classe para realizar o parse dos JSON's necessários à aplicação
 */
public class JsonParser {

    /**
     * Faz o parse do json recebido para um List de {@link Cell}
     * @param json json a ser transformado
     * @return List com {@link Cell}
     * @throws JsonParserException
     */
    public static List<Cell> toListCell(String json) throws JsonParserException {
        try {
            Gson gson = new GsonBuilder().create();
            Cells cells = gson.fromJson(json, Cells.class);

            if (cells == null || cells.getCells() == null) {
                return null;
            }

            return Arrays.asList(cells.getCells());
        } catch (Exception ex) {
            Log.e("JsonParser", ex.getMessage());
            throw new JsonParserException("Erro ao fazer parse do JSON");
        }
    }

    public static Screen toScreen(String json) throws JsonParserException {
        try {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(json, ScreenWrapper.class).getScreen();
        } catch (Exception ex) {
            Log.e("JsonParser", ex.getMessage());
            throw new JsonParserException("Erro ao fazer parse do JSON");
        }
    }

    /**
     * Classe para auxiliar no parse das células
     */
    private class Cells{
        private Cell[] cells;

        public Cell[] getCells() {
            return cells;
        }
    }

    private class ScreenWrapper {
        private Screen screen;

        public Screen getScreen() {
            return screen;
        }
    }
}
