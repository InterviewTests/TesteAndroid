package br.com.santander.testeandroid.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.base.BaseActivity;
import br.com.santander.testeandroid.main.MainContract;
import br.com.santander.testeandroid.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract {

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpTabs();
    }

    private void setUpTabs() {
        ViewPager vp = findViewById(R.id.view_pager);
        TabLayout tl = findViewById(R.id.tab_layout);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getViewFragmentManager(),
                getPresenter().getNames());
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new OnPageSelected());
        tl.setupWithViewPager(vp);

        getPresenter().onTabSelected(0);
    }

    @Override
    public String[] getNamesArray() {
        return getStringArray(R.array.tabNames);
    }

    @Override
    public void setTitle(String title) {
        ((TextView) (findViewById(R.id.title))).setText(title);
    }

    @Override
    public void showShareButton(int visibility) {
        findViewById(R.id.share_button).setVisibility(visibility);
    }

    private class OnPageSelected extends ViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            getPresenter().onTabSelected(position);
        }
    }

}
