package rrzaniolo.testandroidsantander.network.contact.models.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v4.view.ViewCompat;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.base.custom.CustomTextInputLayout;
import rrzaniolo.testandroidsantander.network.contact.models.data.EmailCell;
import rrzaniolo.testandroidsantander.network.contact.models.data.FieldCell;
import rrzaniolo.testandroidsantander.network.contact.models.data.PhoneCell;
import rrzaniolo.testandroidsantander.network.contact.models.data.TextCell;
import rrzaniolo.testandroidsantander.network.contact.models.ui.base.FieldCellView;
import rrzaniolo.testandroidsantander.utils.Utils;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */
public class TextFieldView  extends FieldCellView{

    //region --- Variables
    private TextInputEditText editText;
    public TextInputEditText getEditText() {
        return editText;
    }
    public void setEditText(TextInputEditText editText) {
        this.editText = editText;
    }
    //endregion

    //region --- Constructor
    public TextFieldView(FieldCell cell, ViewGroup viewGroup) {
        super(cell, viewGroup);

        inflateView();
    }
    //endregion

    //region --- FieldCellView Methods
    @Override
    public Boolean isValidAnswer() {
        if(getView().getVisibility() == View.GONE)
            return true;

        return ((FieldCell)getCell()).validateAnswer(getEditText().getText().toString());
    }

    @Override
    public void showError() {
        ColorStateList colorStateList = ColorStateList.valueOf(Color.RED);
        ViewCompat.setBackgroundTintList(getEditText(), colorStateList);
    }

    @Override
    public void hideError() {
        ColorStateList colorStateList = ColorStateList.valueOf(Color.GREEN);
        ViewCompat.setBackgroundTintList(getEditText(), colorStateList);
    }

    @Override
    public void clearField() {
        getEditText().setText("", TextView.BufferType.EDITABLE);

        ColorStateList colorStateList = ColorStateList.valueOf(Color.GRAY);
        ViewCompat.setBackgroundTintList(getEditText(), colorStateList);
    }

    @Override
    protected void inflateView() {
        inflateLayout(R.layout.cell_text);

        setEditText((TextInputEditText) getView().findViewById(R.id.cText_tiet));

        CustomTextInputLayout customTextInputLayout = getView().findViewById(R.id.cText_ctil);
        customTextInputLayout.setHint(getCell().getMessage());

        if(getCell() instanceof EmailCell)
            setUpEmail();
        if(getCell() instanceof PhoneCell)
            setUpPhone();
        if(getCell() instanceof TextCell)
            setUpSimpleTExt();

        ColorStateList colorStateList = ColorStateList.valueOf(Color.GRAY);
        ViewCompat.setBackgroundTintList(getEditText(), colorStateList);
    }
    //endregion

    //region --- Private Methods
    private void setUpEmail(){
        getEditText().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    }

    private void setUpPhone(){
        getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        getEditText().addTextChangedListener(Utils.phoneMask(getEditText()));

    }

    private void setUpSimpleTExt(){
        getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
    }
    //endregion
}
