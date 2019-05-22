package com.example.alessandrofsouza.santanderapp.ui;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.adapter.FragmentCommunication;
import com.example.alessandrofsouza.santanderapp.adapter.ListaCellAdapter;
import com.example.alessandrofsouza.santanderapp.model.Cell;
import com.example.alessandrofsouza.santanderapp.model.ContactModel;
import com.example.alessandrofsouza.santanderapp.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactFragment extends Fragment {

    private Retrofit retrofit;
    private static final String TAG = "Santander Contact";
    private RecyclerView recyclerView;
    private ListaCellAdapter listaCellAdapter;

    public View view;
    public ConstraintLayout layout1;
    public ConstraintLayout layout2;

    public Button btnBack;

    FragmentCommunication fragmentCommunication;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.contact, container, false);
        layout1 = view.findViewById(R.id.constLayout1);
        layout2 = view.findViewById(R.id.constLayout2);

        btnBack = view.findViewById(R.id.buttonBack);

        recycle(view);//chama o recycleView
        useApi();//chama a API

        returnContactLayout(view);

        return view;
    }


    private void recycle(View view) {
        recyclerView = view.findViewById(R.id.recycleViewContact);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        fragmentCommunication = new FragmentCommunication() {
            @Override
            public void respond(View view, int position) {
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        };

        listaCellAdapter = new ListaCellAdapter(fragmentCommunication);
        recyclerView.setAdapter(listaCellAdapter);
    }



    private void returnContactLayout(View view) {
        btnBack = view.findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        });
    }


    private void useApi() {
        retrofit = new Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        getDataApi();
    }

    private void getDataApi() {
        ApiService service = retrofit.create(ApiService.class);
        final Call<ContactModel> requestCells = service.listCells();

        requestCells.enqueue(new Callback<ContactModel>() {
            @Override
            public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {

                if(response.isSuccessful()) {
                    ContactModel contactModel = response.body();
                    ArrayList<Cell> listCell = contactModel.getCells();

                    listaCellAdapter.addListCell(listCell);
                    //for (Cell c : contactModel.cells) if(c.type == 1) Log.i(TAG, c.getMessage());

                } else {
                    Log.e(TAG, "Error Unsuccessful " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ContactModel> call, Throwable t) {
                Log.e(TAG, "Error Failure " + t.getMessage());
            }
        });
    }
}
