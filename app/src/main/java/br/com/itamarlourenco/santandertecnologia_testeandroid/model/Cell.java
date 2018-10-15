package br.com.itamarlourenco.santandertecnologia_testeandroid.model;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.interfaces.ICell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.ui.CellRecyclerView;

/**
 * Model by ICell
 * https://floating-mountain-50292.herokuapp.com/cells.json
 */
public class Cell implements ICell {

    private int id;
    private Type type;
    private String message;
    private TypeField typefield;
    private boolean hidden = false;
    private float topSpacing;
    private int show;
    private boolean required;

    private String value;
    private boolean isValid;

    private transient CellRecyclerView.ViewHolder view;


    public Cell(int id, Type type, String message, TypeField typeField, boolean hidden, float topSpacing, int show, boolean required) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.typefield = typeField;
        this.hidden = hidden;
        this.topSpacing = topSpacing;
        this.show = show;
        this.required = required;
    }

    public Cell(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Cell() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Type getType() {
        return type;
    }

    public CellRecyclerView.ViewHolder getView() {
        return view;
    }

    public void setView(CellRecyclerView.ViewHolder view) {
        this.view = view;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public TypeField getTypeField() {
        return typefield;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public float getTopSpacing() {
        return topSpacing;
    }

    @Override
    public int isShow() {
        return show;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTypeField(TypeField typeField) {
        this.typefield = typeField;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setTopSpacing(float topSpacing) {
        this.topSpacing = topSpacing;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isValidData(){
        if(getType() == Type.send || getType() == Type.image || getType() == Type.title){
            return true;
        }

        if(isHidden()){
            return true;
        }

        if(getTypeField() == TypeField.email && !TextUtils.isEmpty(getValue())){
            return Patterns.EMAIL_ADDRESS.matcher(getValue()).matches();
        }

        if(isRequired()){
            return !TextUtils.isEmpty(getValue());
        }


        return true;
    }


    public class Cells{
        private ArrayList<Cell> cells;

        public ArrayList<Cell> getCells() {
            return cells;
        }
    }
}
