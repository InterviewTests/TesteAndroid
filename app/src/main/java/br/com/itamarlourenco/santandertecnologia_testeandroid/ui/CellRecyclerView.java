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
    private BaseCell.OnClickListener onClickListener;

    CellRecyclerView(ArrayList<Cell> cells, Context context, BaseCell.OnClickListener onClickListener) {
        this.cells = cells;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Cell cell = cells.get(viewType);
        ViewHolder viewHolder = new ViewHolder(BaseCell.returnViewOfTypeCell(cell, context, viewGroup, onClickListener));
        cell.setView(viewHolder);

        if(cell.isHidden()){
            viewHolder.hide();
        }

        return viewHolder;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public void hide(){
            this.view.setVisibility(View.GONE);
            this.view.getLayoutParams().height = 0;
        }

        public void show(){
            this.view.setVisibility(View.VISIBLE);
            this.view.getLayoutParams().height = -2;
        }
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }
}
