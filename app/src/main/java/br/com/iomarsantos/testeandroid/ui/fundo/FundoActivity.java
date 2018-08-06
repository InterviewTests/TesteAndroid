package br.com.iomarsantos.testeandroid.ui.fundo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.ui.base.BaseActivity;
import br.com.iomarsantos.testeandroid.utils.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FundoActivity extends BaseActivity implements FundoView {

    @Inject
    FundoBasePresenter<FundoView> mPresenter;

    @Inject
    FundoPagerAdapter fundoPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.text_view_toolbar_title)
    TextView textViewToolbarTitle;

    @BindView(R.id.image_view_toolbar_share)
    ImageView imageViewToolbarShare;

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

        mPresenter.onAttach(this);

        setup();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTabLayout.getTabAt(1).select();
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
        mViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), true);
                textViewToolbarTitle.setText(tab.getText());
                configuraVisibilityShare(tab);
                mToolbar.requestLayout();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void configuraVisibilityShare(TabLayout.Tab tab) {
        imageViewToolbarShare.setVisibility(View.GONE);
        if (tab.getPosition() == 0) {
            imageViewToolbarShare.setVisibility(View.VISIBLE);
        }
    }

    private void configureTabLayout() {
        TabLayout.Tab tabInvestimento = mTabLayout.newTab().setText(getString(R.string.investimento_titulo));
        mTabLayout.addTab(tabInvestimento);

        TabLayout.Tab tabContato = mTabLayout.newTab().setText(getString(R.string.contato_titulo));
        mTabLayout.addTab(tabContato);

    }

    private void configureAdapter() {
        fundoPagerAdapter.setCount(2);
        mViewPager.setAdapter(fundoPagerAdapter);
    }

    private void configureToolbar() {
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        ViewUtils.centerToolbarTitle(mToolbar);
        textViewToolbarTitle.setText(R.string.contato_titulo);
    }

    @Override
    public void setTitleActivity(String titleActivity) {

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
