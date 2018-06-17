package lzacheu.com.br.santanderinvestimento.fund;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lzacheu.com.br.santanderinvestimento.R;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class FundInfoFragment extends Fragment implements FundInfoContract.View {

    public static FundInfoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FundInfoFragment fragment = new FundInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fundinfo_fragment, container, false);
        return root;
    }


    @Override
    public void setPresenter(FundInfoContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void bindValues() {

    }
}
