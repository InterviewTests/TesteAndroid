package lzacheu.com.br.santanderinvestimento.data;

import java.util.List;

import lzacheu.com.br.santanderinvestimento.model.contact.InputField;

/**
 * Created by luiszacheu on 6/17/18.
 */

public interface ContactDataSource {

    interface LoadCellsCallback{
        void onCellsLoaded(List<InputField> cells);

        void onFailed();
    }

    void getCells(LoadCellsCallback callback);
}
