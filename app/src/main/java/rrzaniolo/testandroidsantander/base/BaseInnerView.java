package rrzaniolo.testandroidsantander.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Rodrigo Rodrigues Zaniolo on 5/7/2018.
 * All rights reserved.
 */
public class BaseInnerView extends Fragment {

    //region --- Variables
    private BaseView baseView;
    @Nullable public BaseView getBaseView() {
        return baseView;
    }
    private void setBaseView(@Nullable BaseView baseView) {
        this.baseView = baseView;
    }
    //endregion

    //region --- Life Cycle
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseView) {
            BaseView baseView = (BaseView) context;
            setBaseView(baseView);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        setBaseView(null);
    }
    //endregion

    public interface InnerViewCallback{
        void onInnerViewAttached();
    }
}
