package com.adenilson.testeandroid.investiment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.base.BaseFragment;
import com.adenilson.testeandroid.investiment.InvestmentView;
import com.adenilson.testeandroid.investiment.controller.InvestmentPresenterImp;
import com.adenilson.testeandroid.investiment.ui.adapter.InvestmentAdapter;
import com.adenilson.testeandroid.investiment.ui.adapter.section.InvestmentSection;
import com.adenilson.testeandroid.util.ThemeUtils;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public class InvestmentFragment extends BaseFragment implements InvestmentView, InvestmentAdapter.OnInvestmentClickListener, SwipeRefreshLayout.OnRefreshListener{

    private static final String KEY_DATA = "key_data";
    private static final String KEY_RECYCLER_STATE = "key_recycler_state";

    @BindView(R.id.swipe_refresh_investment)
    SwipeRefreshLayout mSwipeRefreshInvestment;
    @BindView(R.id.recycler_view_investment)
    RecyclerView mRecyclerViewInvestment;

    private InvestmentAdapter mAdapter;
    private InvestmentPresenterImp mPresenter;
    private Parcelable mRecyclerViewState;

    public static InvestmentFragment newInstance() {
        return new InvestmentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_investment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new InvestmentPresenterImp(this);
        configureElements();
        if(savedInstanceState != null){
            List<InvestmentSection> data = Parcels.unwrap(savedInstanceState.getParcelable(KEY_DATA));
            mAdapter.setData(data);
            mRecyclerViewState = savedInstanceState.getParcelable(KEY_RECYCLER_STATE);
            mRecyclerViewInvestment.getLayoutManager().onRestoreInstanceState(mRecyclerViewState);
        }else {
            initData();
        }
    }

    private void initData() {
        mPresenter.getFund();
    }

    private void configureElements() {
        ThemeUtils.changeColorProgressSwipeRefresh(mSwipeRefreshInvestment);
        mSwipeRefreshInvestment.setOnRefreshListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewInvestment.setLayoutManager(llm);
        mRecyclerViewInvestment.setHasFixedSize(true);

        mAdapter = new InvestmentAdapter(this);
        mRecyclerViewInvestment.setAdapter(mAdapter);
    }


    @Override
    public void setInvestmentAdapterData(List<InvestmentSection> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onInvestClick() {
        Toast.makeText(getContext(), R.string.message_investment_done, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShareReportClick(String report) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, report);
        startActivity(Intent.createChooser(intent, getContext().getString(R.string.share_with)));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    public void showLoading() {
        mSwipeRefreshInvestment.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshInvestment.setRefreshing(false);
    }

    @Override
    public void showError(int messageResourceId) {
        Toast.makeText(getContext(), messageResourceId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_DATA, Parcels.wrap(mAdapter.getData()));
        outState.putParcelable(KEY_RECYCLER_STATE, mRecyclerViewInvestment.getLayoutManager().onSaveInstanceState());
    }
}
