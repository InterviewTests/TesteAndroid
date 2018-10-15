package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;

public class CustomCheckBox extends AppCompatCheckBox {
    public CustomCheckBox(Context context) {
        super(context);
        setFont();
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public CustomCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }


    public void setFont(){
        if(getTypeface() != null){
            if(getTypeface().isBold()) {
                this.setTypeface(App.Fonts.normal());
            } else {
                this.setTypeface(App.Fonts.normal());
            }
        }
    }
}

