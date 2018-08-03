package br.com.iomarsantos.testeandroid.ui.fundo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FundoActivity extends BaseActivity implements FundoView {

    @Inject
    FundoBasePresenter<FundoView> mPresenter;

    @Inject
    FundoPagerAdapter fundoPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.view_pager_fundo_tabs)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, FundoActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundo);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(FundoActivity.this);

    }

    @Override
    public void setup() {

        configureToolbar();

        configureAdapter();

        configureTabLayout();

        configureViewPager();
    }

    private void configureViewPager() {
        mViewPager.setCurrentItem(1);
        mViewPager.setSelected(true);
        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                mToolbar.setTitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void configureTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.investimento_titulo)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.contato_titulo)));
        mTabLayout.setSelected(true);
    }

    private void configureAdapter() {
        fundoPagerAdapter.setCount(2);
        mViewPager.setAdapter(fundoPagerAdapter);
    }

    private void configureToolbar() {
        setSupportActionBar(mToolbar);
        centerToolbarTitle(mToolbar);
    }

    private void centerToolbarTitle(@NonNull final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> views = new ArrayList<>(1);
        toolbar.findViewsWithText(views, title, View.FIND_VIEWS_WITH_TEXT);
        if (!views.isEmpty()) {
            final TextView titleView = (TextView) views.get(0);
            titleView.setGravity(Gravity.CENTER);
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            toolbar.requestLayout();
        }
    }

    @Override
    public void setTitleActivity(int resIdTitle) {
        this.mToolbar.setTitle(resIdTitle);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

}
