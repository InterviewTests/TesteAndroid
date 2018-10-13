package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells.BaseCell;

public class CellRecyclerView  extends RecyclerView.Adapter<CellRecyclerView.ViewHolder> {

    private ArrayList<Cell> cells;
    private Context context;

    CellRecyclerView(ArrayList<Cell> cells, Context context) {
        this.cells = cells;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Cell cell = cells.get(viewType);
        return new ViewHolder(
                BaseCell.returnViewOfTypeCell(cell, context, viewGroup)
        );
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int viewType) {
    }

    @Override
    public int getItemCount() {
        return this.cells != null ? this.cells.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }
}
