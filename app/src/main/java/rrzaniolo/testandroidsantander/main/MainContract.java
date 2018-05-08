package rrzaniolo.testandroidsantander.main;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/7/2018.
 * All rights reserved.
 */

import android.support.v4.app.FragmentManager;

import java.util.List;

import rrzaniolo.testandroidsantander.main.adapters.MainViewPagerAdapter;

/**
 * The Contract definition for MainView and MainPresenter. */
public interface MainContract {

    interface View{
        /**
         * This method Provides an instance of the SupportFragmentManager.
         * */
        FragmentManager getViewFragmentManager();

        /**
         * This method provides a list of strings with the names for the tabs.
         * */
        List<String> getTabNames();
    }

    interface Presenter{

        /***
         * This method provides an instance of the MainViewPagerAdapter.
         * */
        MainViewPagerAdapter getViewPagerAdapter();
    }
}
