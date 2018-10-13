package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.MainContract;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Type;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.Intractors.GetCellsIntractorImpl;

public class FormFragment extends BaseFragment implements MainContract.MainView{

    private MainContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recycleViewCell;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MainPresenterImpl(this, new GetCellsIntractorImpl());
        presenter.requestData();
        progressBar = view.findViewById(R.id.progressBar);
        recycleViewCell = view.findViewById(R.id.recycle_view_cell);
        recycleViewCell.setLayoutManager(new LinearLayoutManager(getActivity()));

        showProgress();

    }

    @Override
    protected int idLayoutFragment() {
        return R.layout.form_fragment;
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<Cell> cellArrayList) {
        cellArrayList.add(0, new Cell(Type.title, App.getContext().getString(R.string.contact)));
        recycleViewCell.setAdapter(new CellRecyclerView(cellArrayList, getActivity()));
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), R.string.error_request_cell,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
