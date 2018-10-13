package br.com.itamarlourenco.santandertecnologia_testeandroid.model;

import android.app.admin.SystemUpdatePolicy;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;
import br.com.itamarlourenco.santandertecnologia_testeandroid.exceptions.ValidateException;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.interfaces.ICell;

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

    @Override
    public boolean isValidData() throws ValidateException {
        if(getMessage() == null || getMessage().isEmpty())
            throw new ValidateException(App.getContext().getString(R.string.error_validate_cell_message));
        if(getMessage().length() < 10)
            throw new ValidateException(App.getContext().getString(R.string.error_validate_cell_message_size));

        return true;
    }

    public class Cells{
        private ArrayList<Cell> cells;

        public ArrayList<Cell> getCells() {
            return cells;
        }
    }
}
