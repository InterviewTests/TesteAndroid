package br.banco.services.fund.interactor;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;


public interface ILoadTask {

    void processFinish(String output); // load
    void convertFinish(List<Object> listScreen, String message); // object


}
