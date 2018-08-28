package br.com.santander.testeandroid.utils;

import android.content.Context;
import android.view.View;

import br.com.santander.testeandroid.contact.model.CellResponse;

public interface CellContract {

    View createCell(CellResponse cell);

}
