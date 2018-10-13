package br.com.itamarlourenco.santandertecnologia_testeandroid.model.interfaces;

import br.com.itamarlourenco.santandertecnologia_testeandroid.exceptions.ValidateException;

public interface IBase {
    boolean isValidData() throws ValidateException;
}
