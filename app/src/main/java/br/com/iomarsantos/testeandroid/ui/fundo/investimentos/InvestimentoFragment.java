package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.di.component.ActivityComponent;
import br.com.iomarsantos.testeandroid.ui.base.BaseFragment;

public class InvestimentoFragment extends BaseFragment implements
        InvestimentoView {

    private static final String TAG = "InvestimentoFragment";

    @Inject
    InvestimentoBasePresenter<InvestimentoView> mPresenter;

    public static InvestimentoFragment newInstance() {
        Bundle args = new Bundle();
        InvestimentoFragment fragment = new InvestimentoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investimento, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
