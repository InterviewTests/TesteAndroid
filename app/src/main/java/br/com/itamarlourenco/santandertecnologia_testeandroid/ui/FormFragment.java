package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.ViewContract;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Type;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.Intractors.GetCellsIntractorImpl;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomCheckBox;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells.BaseCell;

public class FormFragment extends BaseFragment implements ViewContract.ViewActions<ArrayList<Cell>> {

    private ViewContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recycleViewCell;

    public static FormFragment newInstance(){
        return new FormFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ViewPresenterImpl<ArrayList<Cell>>(this, new GetCellsIntractorImpl());
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
    public void setDataToRecyclerView(final ArrayList<Cell> dataArrayList) {
        dataArrayList.add(0, new Cell(Type.title, App.getContext().getString(R.string.contact)));
        recycleViewCell.setAdapter(new CellRecyclerView(dataArrayList, getActivity(), new BaseCell.OnClickListener() {
            @Override
            public void onClick(View view, Cell cell) {

                if(cell.getType() == Type.checkbox && view instanceof CustomCheckBox){
                    CustomCheckBox customCheckBox = (CustomCheckBox) view;
                    handleShow(cell, dataArrayList, customCheckBox);
                }


                if(cell.getType() == Type.send){
                    getValidatesOfCells(dataArrayList);
                }
            }
        }));
    }


    private void handleShow(Cell cell, ArrayList<Cell> dataArrayList, CustomCheckBox customCheckBox) {
        if(cell.isShow() > 0){
            for(Cell cellItem: dataArrayList){
                if(cell.isShow() == cellItem.getId()){

                    if (customCheckBox.isChecked()) {
                        cellItem.getView().show();
                        cellItem.setHidden(false);
                    } else {
                        cellItem.getView().hide();
                        cellItem.setHidden(true);
                    }

                }
            }

            recycleViewCell.getAdapter().notifyDataSetChanged();
        }
    }


    private void getValidatesOfCells(ArrayList<Cell> cellArrayList) {
        for(Cell cell: cellArrayList){
            if(!cell.isValidData()){
                Toast.makeText(getContext(), String.format(getString(R.string.error_validate_form), cell.getMessage()), Toast.LENGTH_LONG).show();
                return;
            }
        }

        StringBuilder stringBuffer = new StringBuilder("Valores dos campos: \n\n");
        for(Cell cell: cellArrayList){
            if(cell.getValue() == null) continue;
            stringBuffer.append(cell.getMessage()).append(": ").append(cell.getValue()).append("\n\n");
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(stringBuffer.toString());
        builder1.setPositiveButton(
                "Fechar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        alterFragment(ContactSentFragment.newInstance());
                    }
                });
        builder1.create().show();
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
