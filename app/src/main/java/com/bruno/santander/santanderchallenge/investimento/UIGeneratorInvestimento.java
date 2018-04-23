package com.bruno.santander.santanderchallenge.investimento;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bruno.santander.santanderchallenge.R;
import com.bruno.santander.santanderchallenge.investimento.model.Info;

import java.util.List;

public class UIGeneratorInvestimento {

    public static void setGrauRisco(LinearLayout linearGrausRisco, int risk){
        View view = linearGrausRisco.getChildAt(risk - 1);
        view.getLayoutParams().height = 60;
    }

    public static void createInfoLayout(Context context, LinearLayout linearInfo, List<Info> infos, List<Info> downInfos){
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        relativeParams.setMargins(0, 0, 0, 20);

        RelativeLayout.LayoutParams textNameParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textNameParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        RelativeLayout.LayoutParams textDataParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textDataParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/DINEngschriftStd.otf");

        for(Info info : infos){
            RelativeLayout relativeLayout = new RelativeLayout(context);
            relativeLayout.setLayoutParams(relativeParams);

            TextView textViewName = new TextView(context);
            textViewName.setTypeface(face);
            textViewName.setTextColor(ContextCompat.getColor(context, R.color.text_investimento));
            textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textViewName.setLayoutParams(textNameParams);
            textViewName.setText(info.getName());

            relativeLayout.addView(textViewName);

            textDataParams.addRule(RelativeLayout.RIGHT_OF, textViewName.getId());
            TextView textViewData = new TextView(context);
            textViewData.setTypeface(face);
            textViewData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textViewData.setLayoutParams(textDataParams);
            textViewData.setText(info.getData());
            textViewData.setGravity(Gravity.END);

            relativeLayout.addView(textViewData);

            linearInfo.addView(relativeLayout);
        }

        RelativeLayout.LayoutParams textNameDownParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textNameParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        RelativeLayout.LayoutParams textDataDownParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textDataParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        for(Info downInfo : downInfos){
            RelativeLayout relativeLayout = new RelativeLayout(context);
            relativeLayout.setLayoutParams(relativeParams);

            TextView textViewName = new TextView(context);
            textViewName.setTypeface(face);
            textViewName.setTextColor(ContextCompat.getColor(context, R.color.text_investimento));
            textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textViewName.setLayoutParams(textNameDownParams);
            textViewName.setText(downInfo.getName());

            relativeLayout.addView(textViewName);

            TextView textViewData = new TextView(context);
            textViewData.setTypeface(face);
            textViewData.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            textViewData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textViewData.setLayoutParams(textDataDownParams);
            textViewData.setText("Baixar");
            textViewData.setGravity(Gravity.END);
            Linkify.addLinks(textViewData, Linkify.WEB_URLS);
            textViewData.setLinksClickable(true);
            textViewData.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.download, 0);

            relativeLayout.addView(textViewData);

            linearInfo.addView(relativeLayout);
        }
    }

}
