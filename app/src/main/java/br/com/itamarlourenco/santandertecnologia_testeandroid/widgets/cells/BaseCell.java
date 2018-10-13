package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;

public abstract class BaseCell {

    private LayoutInflater inflater;
    private Context context;
    private View view;
    private Cell cell;
    private ViewGroup parent;

    public BaseCell(Context context, Cell cell) {
        this(context, cell, null);
    }

    public BaseCell(Context context, Cell cell, ViewGroup parent) {
        this.context = context;
        this.cell = cell;
        this.inflater = LayoutInflater.from(context);
        this.parent = parent;

        onCreateView();
    }

    public abstract int idLayout();

    private void onCreateView(){
        view = inflater.inflate(idLayout(), parent, false);
        handleWithView(addMargin(view));
    }

    private View addMargin(View view) {
        int sidesMargin = 110;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(sidesMargin, (int) getCell().getTopSpacing(), sidesMargin, 20);
        view.setLayoutParams(params);
        return view;
    }

    public View getView() {
        return view;
    }

    protected abstract void handleWithView(View view);

    protected Context getContext() {
        return context;
    }

    protected Cell getCell() {
        return cell;
    }

    public static View returnViewOfTypeCell(Cell cell, Context context, ViewGroup viewGroup){
        switch (cell.getType()){
            case title:
                return new TitleCell(context, cell, viewGroup).getView();
            case field:
                return new FieldCell(context, cell, viewGroup).getView();
            case text:
                return new TextCell(context, cell, viewGroup).getView();
            case image:
                return new ImageCell(context, cell, viewGroup).getView();
            case checkbox:
                return new CheckBoxCell(context, cell, viewGroup).getView();
            case send:
                return new SendCell(context, cell, viewGroup).getView();
        }

        return null;
    }
}
