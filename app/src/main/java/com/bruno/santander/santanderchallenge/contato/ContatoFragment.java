package com.bruno.santander.santanderchallenge.contato;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bruno.santander.santanderchallenge.BaseFragment;
import com.bruno.santander.santanderchallenge.IEditTextContato;
import com.bruno.santander.santanderchallenge.MainActivity;
import com.bruno.santander.santanderchallenge.R;
import com.bruno.santander.santanderchallenge.UIValidationUtils;
import com.bruno.santander.santanderchallenge.contato.di.InjectContato;
import com.bruno.santander.santanderchallenge.contato.model.Cell;
import com.bruno.santander.santanderchallenge.contato.model.ListCell;
import com.bruno.santander.santanderchallenge.contato.presentation.ContatoContract;
import com.bruno.santander.santanderchallenge.contato.presentation.ContatoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContatoFragment extends BaseFragment implements ContatoContract.View, IEditTextContato {

    @BindView(R.id.coordinator_contato)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.root)
    LinearLayout linearLayout;

    @BindView(R.id.relative_sucesso)
    RelativeLayout relativeSucesso;

    private ContatoContract.Presenter presenter;
    private List<EditText> editTexts;

    public ContatoFragment() {
        // Required empty public constructor
    }

    public static ContatoFragment newInstance(){
        return new ContatoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contato, container, false);

        setUnBinder(ButterKnife.bind(this, view));
        InjectContato.inject(getActivity(), this);

        presenter.setEditTextContato(this);
        presenter.getCells();

        return view;
    }

    @Override
    public void onSuccessGetCells(ListCell cells) {
        editTexts = new ArrayList<>();
        presenter.setScreenData(coordinatorLayout, cells);
    }

    @OnClick(R.id.enviar_nova)
    public void enviarNova(){
        linearLayout.setVisibility(View.VISIBLE);
        relativeSucesso.setVisibility(View.GONE);

        if(editTexts.size() > 0){
            for(EditText editText : editTexts){
                editText.setText("");
            }
        }
    }

    @Override
    public void onSuccessSaveContato() {
        Snackbar.make(coordinatorLayout, getString(R.string.success_save_contato), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(ContatoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onError(String msg) {
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void addEditText(EditText editText) {
        editTexts.add(editText);
    }

    @Override
    public List<EditText> getEditTextList() {
        return editTexts;
    }
}
