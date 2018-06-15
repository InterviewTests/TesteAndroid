package br.com.accenture.santander.wallacebaldenebre.ui.main.fragments.contact;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import br.com.accenture.santander.wallacebaldenebre.model.Cell;
import br.com.accenture.santander.wallacebaldenebre.ui.base.BasePresenter;
import br.com.accenture.santander.wallacebaldenebre.utils.Helper;

public class ContactPresenter<V extends ContactContract.View> extends BasePresenter<V> implements ContactContract.Presenter<V> {

    @Override
    public void showDataFormFields(final ContactFragment confra, final ContactCallback<HashMap<String, Cell[]>> callback) {
        new GetFormFields(callback, confra).execute();
    }

    public class GetFormFields extends AsyncTask<Void, Void, Void> {
        private ContactCallback<HashMap<String, Cell[]>> callback;
        private ContactFragment confra;

        public GetFormFields(ContactCallback<HashMap<String, Cell[]>> callback, ContactFragment confra) {
            this.callback = callback;
            this.confra = confra;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            confra.showProgress();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String jsonString = Helper.callAPI("https://floating-mountain-50292.herokuapp.com/cells.json");

            if (jsonString != null) {
                try {
                    JSONObject jo = new JSONObject(jsonString);

                    //  JSON Array do Cells
                    Cell[] cellsArray = new Cell[7];
                    JSONArray cells = jo.getJSONArray("cells");
                    for (int i = 0; i < cells.length(); i++) {
                        JSONObject cell = cells.getJSONObject(i);

                        final int id = cell.getInt("id");
                        int type = cell.getInt("type");
                        final String message = cell.getString("message");
                        final String typeField = String.valueOf(cell.getString("typefield"));
                        boolean hidden = cell.getBoolean("hidden");
                        final int topSpacing = cell.getInt("topSpacing");
                        String show = String.valueOf(cell.getString("show"));
                        boolean required = cell.getBoolean("required");
                        cellsArray[i] = new Cell(id, type, message, typeField, hidden, topSpacing, show, required);
                    }

                    HashMap<String, Cell[]> temp = new HashMap<>();
                    temp.put("cells", cellsArray);

                    callback.onSuccess(temp);
                    confra.hideProgress();
                } catch (final JSONException e) {
                    e.printStackTrace();
                    confra.hideProgress();
                }
            } else {
                confra.hideProgress();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            confra.hideProgress();
        }
    }
}
