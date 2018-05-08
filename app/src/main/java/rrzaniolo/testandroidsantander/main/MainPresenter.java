package rrzaniolo.testandroidsantander.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rrzaniolo.testandroidsantander.base.BasePresenter;
import rrzaniolo.testandroidsantander.main.adapters.MainViewPagerAdapter;

/**
 * Created by Rodrigo Rodrigues Zaniolo on 5/7/2018.
 * All rights reserved.
 */
public class MainPresenter extends BasePresenter implements MainContract.Presenter {

    //region --- Variables
    MainContract.View contractView;
    public @Nullable MainContract.View getContractView() {
        return contractView;
    }
    public void setContractView(@NonNull MainContract.View contractView) {
        this.contractView = contractView;
    }

    private MainViewPagerAdapter viewPagerAdapter;
    @Override
    public @Nullable MainViewPagerAdapter getViewPagerAdapter() {
        return viewPagerAdapter;
    }
    public void setViewPagerAdapter(@NonNull MainViewPagerAdapter viewPagerAdapter) {
        this.viewPagerAdapter = viewPagerAdapter;
    }
    //endregion

    //region --- Constructors
    public MainPresenter(@NonNull MainContract.View contractView){
        setContractView(contractView);

        if(getContractView() != null)
            setViewPagerAdapter(new MainViewPagerAdapter(getContractView().getViewFragmentManager(), getContractView().getTabNames()));
    }
    //endregion
}
