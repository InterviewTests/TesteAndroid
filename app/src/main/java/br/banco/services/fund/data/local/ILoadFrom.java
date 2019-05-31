package br.banco.services.fund.data.local;

/**
 *
 *  configirar como as classes devem exibir dados
 */
import java.util.List;


public interface ILoadFrom {

    void processFinish(String output); // load
    void convertFinish(List<Object> listScreen, String message); // object


}
