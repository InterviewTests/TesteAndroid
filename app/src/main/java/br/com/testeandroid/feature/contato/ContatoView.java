package br.com.testeandroid.feature.contato;

import java.util.ArrayList;

import br.com.testeandroid.model.Cells;

public interface ContatoView {

    void ConfigureCells(ArrayList<Cells> cells);
    void showProgress();
    void finishProgress();
    void showSucesso();
    void finishSucesso();
    void ErroLoading();
}
