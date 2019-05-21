package com.example.alessandrofsouza.santanderapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.adapter.ListaCellAdapter;
import com.example.alessandrofsouza.santanderapp.model.Cell;
import com.example.alessandrofsouza.santanderapp.model.ContactModel;
import com.example.alessandrofsouza.santanderapp.service.ApiService;

import java.util.ArrayList;
import java.util.List;

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

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.contact, container, false);

        TextView tv = view.findViewById(R.id.textTitle);
        tv.setText(R.string.contato);

        recycle(view);//chama o recycleView

        useApi();//chama a API

        return view;
    }

    private void recycle(View view) {
        recyclerView = view.findViewById(R.id.recycleViewContact);
        listaCellAdapter = new ListaCellAdapter();
        recyclerView.setAdapter(listaCellAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
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
