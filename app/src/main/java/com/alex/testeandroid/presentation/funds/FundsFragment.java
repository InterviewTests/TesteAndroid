package com.alex.testeandroid.presentation.funds;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alex.testeandroid.R;
import com.alex.testeandroid.data.entities.contact.Cell;
import com.alex.testeandroid.data.entities.contact.Type;
import com.alex.testeandroid.data.entities.contact.TypeField;
import com.alex.testeandroid.data.entities.funds.Funds;
import com.alex.testeandroid.presentation.common.TextMask;

import java.util.List;

/**
 * Created by Alex on 27/08/18.
 */
public class FundsFragment extends Fragment implements FundsView {

    //region FIELDS
    private FundsPresenter presenter;
    private ConstraintLayout consForm;
    private ProgressBar pgbLoading;
    //endregion

    public static FundsFragment newInstance() {
        return new FundsFragment();
    }

    //region LIFECYCLE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_funds, container, false);
        consForm = view.findViewById(R.id.fragment_funds_cons_form);
        pgbLoading = view.findViewById(R.id.fragment_funds_pgb_loading);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new FundsPresenter(this);
        presenter.getFunds();
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) presenter.detachView();
        super.onDestroyView();
    }
    //endregion

    //region METHODS
    //region OVERRIDES METHODS
    @Override
    public void showProgress(boolean show) {
        pgbLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessageErrorRequest() {
        Toast.makeText(getContext(), "falha ao montar formulário", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setupCells(Funds funds) {
    }
    //endregion

    //region PRIVATE METHODS

    //endregion
    //endregion
}
