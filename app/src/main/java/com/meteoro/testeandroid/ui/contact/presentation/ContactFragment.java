package com.meteoro.testeandroid.ui.contact.presentation;

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
import com.meteoro.testeandroid.ui.contact.di.ContactModule;
import com.meteoro.testeandroid.ui.contact.di.DaggerContactComponent;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.presentation.adapter.ContactAdapter;
import com.meteoro.testeandroid.ui.contact.presentation.listener.OnSendClickListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactFragment extends BaseFragment
        implements HasComponent<LibraryComponent>, ContactContract.View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Views
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private Unbinder unbinder;

    @BindView(R.id.state_contact_loading)
    View stateContactLoading;

    @BindView(R.id.state_contact_error)
    View stateContactError;

    @BindView(R.id.state_contact_content)
    View stateContactContent;

    @BindView(R.id.state_contact_send)
    View stateContactSend;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Dependency Injection
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    ContactContract.Presenter presenter;

    @Inject
    ContactAdapter adapter;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Listener
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private OnSendClickListener onSendClickListener =
            this::handleSendClickListener;

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Override
    public LibraryComponent getComponent() {
        return MyApplication.get(getActivity()).getComponent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
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
        DaggerContactComponent
                .builder()
                .libraryComponent(getComponent())
                .contactModule(new ContactModule(this))
                .build()
                .inject(this);
    }

    private void initializeViews() {
        adapter.setOnSendClickListener(onSendClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initializeContents() {
        presenter.initializeContents();
    }

    @Override
    public void showLoading() {
        stateContactLoading.setVisibility(View.VISIBLE);
        stateContactError.setVisibility(View.GONE);
        stateContactContent.setVisibility(View.GONE);
        stateContactSend.setVisibility(View.GONE);
    }

    @Override
    public void showViewModel(CellsViewModel viewModel) {
        stateContactLoading.setVisibility(View.GONE);
        stateContactError.setVisibility(View.GONE);
        stateContactContent.setVisibility(View.VISIBLE);
        stateContactSend.setVisibility(View.GONE);

        adapter.setData(viewModel);
    }

    @Override
    public void showError() {
        stateContactLoading.setVisibility(View.GONE);
        stateContactError.setVisibility(View.VISIBLE);
        stateContactContent.setVisibility(View.GONE);
        stateContactSend.setVisibility(View.GONE);
    }

    private void handleSendClickListener() {
//        stateContactLoading.setVisibility(View.GONE);
//        stateContactError.setVisibility(View.GONE);
//        stateContactContent.setVisibility(View.GONE);
//        stateContactSend.setVisibility(View.VISIBLE);

        presenter.validateFields(adapter.getData());
    }

    @OnClick(R.id.tv_send_new)
    public void handleNewMessageClick() {
        presenter.initializeContents();
    }
}
