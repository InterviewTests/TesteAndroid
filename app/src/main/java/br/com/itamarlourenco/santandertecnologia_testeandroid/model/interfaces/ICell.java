package br.com.itamarlourenco.santandertecnologia_testeandroid.model.interfaces;


import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Type;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.TypeField;

public interface ICell extends IBase{

    int getId();

    Type getType();

    String getMessage();

    TypeField getTypeField();

    boolean isHidden();

    float getTopSpacing();

    int isShow();

    boolean isRequired();
}
