package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.di.component.ActivityComponent;
import br.com.iomarsantos.testeandroid.ui.base.BaseFragment;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoPresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoView;

public class ContatoFragment extends BaseFragment implements
        ContatoView {

    private static final String TAG = "ContatoFragment";

    @Inject
    ContatoBasePresenter<ContatoView> mPresenter;

    public static ContatoFragment newInstance() {
        Bundle args = new Bundle();
        ContatoFragment fragment = new ContatoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contato, container, false);
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
