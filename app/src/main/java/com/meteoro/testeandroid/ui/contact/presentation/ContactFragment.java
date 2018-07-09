package com.meteoro.testeandroid.ui.contact.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Dependency Injection
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Inject
    ContactContract.Presenter presenter;

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
    }

    private void initializeInjection() {
        DaggerContactComponent
                .builder()
                .libraryComponent(getComponent())
                .contactModule(new ContactModule(this))
                .build()
                .inject(this);
    }
}
