package br.banco.services.fund.interactor;

import java.util.List;


public interface ILoadTask {

    void processFinish(String output); // load
    void convertFinish(List<Object> listScreen, String message); // object


}
