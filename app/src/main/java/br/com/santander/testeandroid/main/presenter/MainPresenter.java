package br.com.santander.testeandroid.main.presenter;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.santander.testeandroid.base.BasePresenter;
import br.com.santander.testeandroid.main.MainContract;

public class MainPresenter extends BasePresenter {

    private MainContract contractView;

    public MainPresenter(@NonNull MainContract contractView) {
        setContractView(contractView);
    }

    public void onTabSelected(int position) {
        List<String> tabNames = getNames();

        if (tabNames != null && tabNames.size() > position)
            getContractView().setTitle(tabNames.get(position));

        getContractView().showShareButton(position == 0 ? View.VISIBLE : View.INVISIBLE);
    }

    public List<String> getNames() {
        String[] arr = getContractView().getNamesArray();

        if (arr == null || arr.length == 0) {
            return Collections.EMPTY_LIST;
        }

        return Arrays.asList(arr);
    }

    private MainContract getContractView() {
        return contractView;
    }

    private void setContractView(@NonNull MainContract contractView) {
        this.contractView = contractView;
    }
}
