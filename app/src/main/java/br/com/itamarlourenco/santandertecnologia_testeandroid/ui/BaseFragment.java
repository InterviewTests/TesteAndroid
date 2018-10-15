package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;

public abstract class BaseFragment  extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(idLayoutFragment(), container, false);
    }

    protected abstract int idLayoutFragment();

    protected void alterFragment(BaseFragment baseFragment){
        ((BaseActivity) getActivity()).alterFragments(baseFragment);
    }
}
