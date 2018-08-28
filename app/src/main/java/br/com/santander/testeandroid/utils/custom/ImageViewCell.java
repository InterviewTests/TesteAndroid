package br.com.santander.testeandroid.utils.custom;

import android.content.Context;
import android.view.View;

import br.com.santander.testeandroid.contact.model.CellResponse;
import br.com.santander.testeandroid.utils.CellContract;

public class ImageViewCell extends BaseCell implements CellContract {

    public ImageViewCell(Context context) {
        super(context);
    }

    @Override
    public View createCell(CellResponse cell) {
        return null;
    }

}
