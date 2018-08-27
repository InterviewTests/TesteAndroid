package br.com.santander.testeandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Base abstract class to be extended by all Fragments
 * @param <Presenter> A generic type to associate a presenter with the fragment
 */
@SuppressWarnings("unused")
public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment {

    private BaseActivity baseView;
    private Presenter mPresenter;
    private static final String TAG = "ST-LOG";

    @NonNull
    protected abstract Presenter createPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = createPresenter();

        if (context instanceof BaseActivity) {
            BaseActivity baseView = (BaseActivity) context;
            setBaseView(baseView);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        setBaseView(null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().onViewCreated(view, savedInstanceState);
    }

    public Presenter getPresenter() {
        return mPresenter;
    }

    private void setBaseView(@Nullable BaseActivity baseView) {
        this.baseView = baseView;
    }

    public String[] getStringArray(@ArrayRes int resId) {
        return getResources().getStringArray(resId);
    }

    public void log(String msg) {
        Log.d(TAG, msg);
    }

    public View findViewById(@IdRes int id) {
        return getView().findViewById(id);
    }

    public TextView getTextView(@IdRes int id) {
        return (TextView) findViewById(id);
    }

    public ImageView getImageView(@IdRes int id) {
        return (ImageView) findViewById(id);
    }

    public RecyclerView getRecyclerView(@IdRes int id) {
        return (RecyclerView) findViewById(id);
    }

}
