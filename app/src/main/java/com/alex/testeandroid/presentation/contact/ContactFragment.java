package com.alex.testeandroid.presentation.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alex.testeandroid.R;
import com.alex.testeandroid.data.entities.Cell;

import java.util.List;

/**
 * Created by Alex on 27/08/18.
 */
public class ContactFragment extends Fragment implements ContactView {

    //region FIELDS
    private ContactPresenter presenter;
    private ProgressBar pgbLoading;
    //endregion

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    //region LIFECYCLE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        pgbLoading = view.findViewById(R.id.fragment_contact_pgb_loading);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ContactPresenter(this);
        presenter.getContactForm();
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
    public void setupCells(List<Cell> cells) {

    }
    //endregion
    //endregion
}
