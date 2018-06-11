package com.adenilson.testeandroid.contact.ui.custom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.contact.model.Cell;
import com.adenilson.testeandroid.util.DimensUtil;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class CustomCheckbox extends AppCompatCheckBox implements CustomViewInterface {

    private Cell mCell;

    public CustomCheckbox(Context context, Cell cell, final ChangeEmailVisibilityListener listener) {
        super(context);
        mCell = cell;

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setTopMargin(marginLayoutParams);
        setMessage(cell.getMessage());
        setVisibility();

        Typeface typeface = ResourcesCompat.getFont(context, R.font.din_pro_light);
        setTypeface(typeface);

        setLinkTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{}  // default
        };

        int[] colors = new int[]{0XFFFFFFFF, 0xFFEA0404, 0xFFEA0404};
        CompoundButtonCompat.setButtonTintList(this, new ColorStateList(states, colors));

        setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                listener.onCheckChanged(isChecked);
            }
        });

        setLayoutParams(marginLayoutParams);
    }

    @Override
    public void setTopMargin(ViewGroup.MarginLayoutParams layoutParams) {
        layoutParams.topMargin = DimensUtil.dpToPx(getContext(), mCell.getTopSpacing());
        layoutParams.setMarginEnd(DimensUtil.dpToPx(getContext(),24));
        layoutParams.setMarginStart(DimensUtil.dpToPx(getContext(),24));
    }

    @Override
    public void setMessage(String message) {
        setText(message);
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
    public void setRequired(boolean isRequired) {

    }

    @Override
    public boolean checkError() {
        return false;
    }

    @Override
    public void clearField() {
        setChecked(false);
    }

    public interface ChangeEmailVisibilityListener {
        
        void onCheckChanged(boolean isChecked);
    }

}
