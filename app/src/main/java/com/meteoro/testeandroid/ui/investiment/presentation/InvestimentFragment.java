package com.meteoro.testeandroid.ui.investiment.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.application.MyApplication;
import com.meteoro.testeandroid.core.di.HasComponent;
import com.meteoro.testeandroid.core.di.component.LibraryComponent;
import com.meteoro.testeandroid.core.view.BaseFragment;
import com.meteoro.testeandroid.ui.investiment.di.DaggerInvestimentComponent;
import com.meteoro.testeandroid.ui.investiment.di.InvestimentModule;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.presentation.adapter.InvestimentAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InvestimentFragment extends BaseFragment
        implements HasComponent<LibraryComponent>, InvestimentContract.View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Views
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private Unbinder unbinder;

    @BindView(R.id.state_investiment_loading)
    View stateInvestimentLoading;

    @BindView(R.id.state_investiment_error)
    View stateInvestimentError;

    @BindView(R.id.state_investiment_content)
    View stateInvestimentContent;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Dependency Injection
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    InvestimentContract.Presenter presenter;

    @Inject
    InvestimentAdapter adapter;

    public static InvestimentFragment newInstance() {
        return new InvestimentFragment();
    }

    @Override
    public LibraryComponent getComponent() {
        return MyApplication.get(getActivity()).getComponent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investiment, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeInjection();
        initializeViews();
        initializeContents();
    }

    private void initializeInjection() {
        DaggerInvestimentComponent
                .builder()
                .libraryComponent(getComponent())
                .investimentModule(new InvestimentModule(this))
                .build()
                .inject(this);
    }

    private void initializeViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initializeContents() {
        presenter.initializeContents();
    }

    @Override
    public void showLoading() {
        stateInvestimentLoading.setVisibility(View.VISIBLE);
        stateInvestimentError.setVisibility(View.GONE);
        stateInvestimentContent.setVisibility(View.GONE);
    }

    @Override
    public void showViewModel(ScreenViewModel viewModel) {
        stateInvestimentLoading.setVisibility(View.GONE);
        stateInvestimentError.setVisibility(View.GONE);
        stateInvestimentContent.setVisibility(View.VISIBLE);

        adapter.setData(viewModel);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        stateInvestimentLoading.setVisibility(View.GONE);
        stateInvestimentError.setVisibility(View.VISIBLE);
        stateInvestimentContent.setVisibility(View.GONE);
    }
}
