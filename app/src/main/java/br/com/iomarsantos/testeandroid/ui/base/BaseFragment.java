package br.com.iomarsantos.testeandroid.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import br.com.iomarsantos.testeandroid.di.component.ActivityComponent;

public abstract class BaseFragment extends Fragment implements BaseView {

    private BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void setup() {
    }

    @Override
    public void setTitleActivity(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.setTitleActivity(resId);
        }
    }

    @Override
    public void setTitleActivity(String title) {
        if (mActivity != null) {
            mActivity.setTitleActivity(title);
        }
    }

}