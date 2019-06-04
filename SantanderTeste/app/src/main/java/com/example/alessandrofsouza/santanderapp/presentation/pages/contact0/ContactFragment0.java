package com.example.alessandrofsouza.santanderapp.presentation.pages.contact0;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.domain.model.Cell;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ContactFragment0 extends Fragment implements ContactContract0.View {

    private static final String TAG = "Santander ";
    private ContactContract0.Presenter presenter;
    private ContactAdapter0 contactAdapter0;
    private RecyclerView recyclerView;
    private ContactListPresenter0 listPresenter0;
    private ContactRowView0 rowView0;

    private ConstraintLayout layout1;
    private ConstraintLayout layout2;
    private Button btnBack;


    public static ContactFragment0 newInstance() {
        return new ContactFragment0();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.contact, container, false);

        layout1 = view.findViewById(R.id.constLayout1);
        layout2 = view.findViewById(R.id.constLayout2);
        btnBack = view.findViewById(R.id.buttonBack);
        recycleView(view);
        returnContactLayout(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.init();
    }

    @Override
    public void setPresenter(ContactContract0.Presenter contractPresenter) {
        presenter = contractPresenter;
    }

    @Override
    public void showContactCells(ArrayList<Cell> cellList) {
        //Envia pro recycleView
        contactAdapter0.addListContact(cellList);
    }

    private void recycleView(View view) {
        contractActionExtra();
        recyclerView = view.findViewById(R.id.recycleViewContact);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter0 = new ContactAdapter0(rowView0);
        recyclerView.setAdapter(contactAdapter0);
    }



    private void contractActionExtra() {
        rowView0 = new ContactRowView0() {
            @Override
            public void click(View itemView, int position) {
//                Log.i(TAG, "->: " + contactAdapter0.getValidatorForm());
                switch (position) {
                    case 5:
                        if (contactAdapter0.getValidatorForm()) {
                            layout1.setVisibility(View.GONE);
                            layout2.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        };
    }

    private void returnContactLayout(View view) {
        btnBack = view.findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contactAdapter0.clearListContact();
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        });
    }

}
