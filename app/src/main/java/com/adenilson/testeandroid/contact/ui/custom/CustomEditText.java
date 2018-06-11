package com.adenilson.testeandroid.contact.ui.custom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.base.EditTextMask;
import com.adenilson.testeandroid.contact.model.Cell;
import com.adenilson.testeandroid.contact.model.TypeFieldEnum;
import com.adenilson.testeandroid.util.DimensUtil;
import com.adenilson.testeandroid.util.StringUtils;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class CustomEditText extends TextInputEditText implements CustomViewInterface {

    private Cell mCell;

    public CustomEditText(Context context, Cell cell) {
        super(context);
        mCell = cell;
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setTopMargin(layoutParams);

        setVisibility();
        setMessage(cell.getMessage());

        TypeFieldEnum type = cell.getTypeField();
        if (type == TypeFieldEnum.TEXT) {
            setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (type == TypeFieldEnum.TEL_NUMBER) {
            setInputType(InputType.TYPE_CLASS_PHONE);
            EditTextMask editTextMask = new EditTextMask();
            addTextChangedListener(editTextMask.insert(this));
        } else if (type == TypeFieldEnum.EMAIL) {
            setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            //set first requirement
            setRequired(false);
        }

        Typeface typeface = ResourcesCompat.getFont(context, R.font.din_pro_light);
        setTypeface(typeface);

        ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(Color.GRAY));


        setLayoutParams(layoutParams);
    }

    @Override
    public void setTopMargin(ViewGroup.MarginLayoutParams layoutParams) {
        layoutParams.topMargin = DimensUtil.dpToPx(getContext(), mCell.getTopSpacing());
        layoutParams.setMarginEnd(DimensUtil.dpToPx(getContext(), 24));
        layoutParams.setMarginStart(DimensUtil.dpToPx(getContext(), 24));
    }

    @Override
    public void setMessage(String message) {
        setHint(mCell.getMessage());
    }

    @Override
    public void setVisibility() {
        setVisibility(mCell.isHidden() ? GONE : VISIBLE);
    }

    @Override
    public boolean isRequired() {
        return mCell.isRequired();
    }

    @Override
    public void setRequired(boolean required) {
        mCell.setRequired(required);
    }

    @Override
    public boolean checkError() {
        if (!isRequired()) {
            return false;
        }
        boolean error;
        TypeFieldEnum typeField = mCell.getTypeField();
        if (typeField == TypeFieldEnum.EMAIL) {
            error = !StringUtils.isValidEmail(getText().toString());
        } else if (typeField == TypeFieldEnum.TEL_NUMBER) {
            EditTextMask editTextMask = new EditTextMask();
            int length = editTextMask.unmask(getText().toString()).length();
            error = length != 11 & length != 10 && length != 9 && length != 8;
        } else {
            error = TextUtils.isEmpty(getText().toString());
        }


        if (error) {
            ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(Color.RED));
        } else {
            ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(Color.GREEN));
        }
        return error;
    }

    @Override
    public void clearField() {
        setText("");
        ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(Color.GRAY));
    }


}
