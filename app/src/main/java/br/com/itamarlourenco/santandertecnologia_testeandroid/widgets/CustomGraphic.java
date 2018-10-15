package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;

public class CustomGraphic extends LinearLayout {

    private ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    private ArrayList<View> viewArrayList = new ArrayList<>();
    private View view;
    private int status = 0;


    public CustomGraphic(Context context) {
        super(context);
        handleView(context);
    }

    public CustomGraphic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handleView(context);
    }

    public CustomGraphic(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handleView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomGraphic(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        handleView(context);
    }

    public void setStatus(int indicator) {
        this.status = indicator-1;
        setStatusView();
    }

    private void handleView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.graphic, (ViewGroup) getParent(), true);
        setStatusView();
        addView(view);
    }

    private void setStatusView() {
        if(view == null) return;

        imageViewArrayList.add((ImageView) view.findViewById(R.id.image1));
        imageViewArrayList.add((ImageView) view.findViewById(R.id.image2));
        imageViewArrayList.add((ImageView) view.findViewById(R.id.image3));
        imageViewArrayList.add((ImageView) view.findViewById(R.id.image4));
        imageViewArrayList.add((ImageView) view.findViewById(R.id.image5));

        viewArrayList.add(view.findViewById(R.id.view1));
        viewArrayList.add(view.findViewById(R.id.view2));
        viewArrayList.add(view.findViewById(R.id.view3));
        viewArrayList.add(view.findViewById(R.id.view4));
        viewArrayList.add(view.findViewById(R.id.view5));

        if(status >= 0 && status < viewArrayList.size()){
            resetStatus();
            View viewSelected = viewArrayList.get(status);
            setHeightView(viewSelected, 20);

            ImageView imageView = imageViewArrayList.get(status);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private void resetStatus() {
        for(ImageView imageView: imageViewArrayList){
            imageView.setVisibility(View.INVISIBLE);
        }

        for(View view: viewArrayList){
            setHeightView(view, 15);
        }
    }

    private void setHeightView(View view, int height){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }


}
