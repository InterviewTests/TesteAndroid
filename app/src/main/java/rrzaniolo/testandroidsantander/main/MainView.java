package rrzaniolo.testandroidsantander.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.base.BaseView;

/**
 * Created by Rodrigo Rodrigues Zaniolo on 5/7/2018.
 * All rights reserved.
 */
public class MainView extends BaseView implements MainContract.View{

    //region --- Variables
    private MainContract.Presenter contractPresenter;
    @Nullable public MainContract.Presenter getContractPresenter() {
        return contractPresenter;
    }
    public void setContractPresenter(@NonNull MainContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }
    //endregion
    //region --- Life Cycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_main);

        setContractPresenter(new MainPresenter(MainView.this));

        setUpTabs();
    }
    //endregion

    //region --- Private Methods
    /**
     * This method sets the Title text.
     * */
    private void setTitle(String title){
        ((TextView)(findViewById(R.id.aMain_atbTitle))).setText(title);
    }

    /**
     * This method hides the share button.
     * */
    private void showShareButton(int visibility){
        findViewById(R.id.aMain_atbShare).setVisibility(visibility);
    }

    /**
     * This method configures the ViewPager and TabLayout.
     * */
    private void setUpTabs(){
        ViewPager vp = findViewById(R.id.aMain_vp);

        if(getContractPresenter() != null)
            vp.setAdapter(getContractPresenter().getViewPagerAdapter());

        vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                onTabSelected(position);
            }
        });

        TabLayout tl = findViewById(R.id.aMain_tl);
        tl.setupWithViewPager(vp);

        onTabSelected(0);
    }

    /**
     * This method adjusts the MainView for the selected page.
     * */
    private void onTabSelected(int position){
        if(getTabNames() != null && getTabNames().size() > position)
            setTitle(getTabNames().get(position));

        showShareButton(position == 0 ? View.VISIBLE : View.INVISIBLE);
    }
    //endregion

    /**
     * Implementation of the Contract Methods.
     * */
    //region --- Contract Methods
    @Override
    public List<String> getTabNames() {
        List<String> tabNames = new ArrayList<>();
        tabNames.add(getString(R.string.main_tab_investment));
        tabNames.add(getString(R.string.main_tab_contact));

        return tabNames;
    }

    @Override
    public FragmentManager getViewFragmentManager() {
        return getSupportFragmentManager();
    }
    //endregion
}
