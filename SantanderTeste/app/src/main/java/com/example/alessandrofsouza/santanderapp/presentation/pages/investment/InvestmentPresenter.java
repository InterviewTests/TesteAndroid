package com.example.alessandrofsouza.santanderapp.presentation.pages.investment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.data.service.ApiService;
import com.example.alessandrofsouza.santanderapp.domain.model.InvestmentModel;
import com.example.alessandrofsouza.santanderapp.domain.model.Screen;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestmentPresenter extends Fragment {

    private static final String TAG = "Santander ";
    private ApiService service;
    private InvestmentFragment investmentFragment;
    private RecyclerView recyclerView;
    public Button btnPrint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.investment, container, false);

        service = new ApiService();
        getDataApi();
        recycleView(view);

        btnPrint = view.findViewById(R.id.buttonPrint);
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), R.string.disable, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void getDataApi() {
        service.getApi().listInvestment().enqueue(new Callback<InvestmentModel>() {
            @NonNull
            @Override
            public void onResponse(Call<InvestmentModel> call, Response<InvestmentModel> response) {
                if (response.isSuccessful()) {
                    InvestmentModel investmentModel = response.body();

                    Screen listScreen = investmentModel.getScreen();
                    investmentFragment.addListInvestment(listScreen);

                } else {
                    Log.e(TAG, "Error Unsuccessful " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<InvestmentModel> call, Throwable t) {
                Log.e(TAG, "Error Failure " + t.getMessage());
            }
        });
    }

    private void recycleView(View view) {
        recyclerView = view.findViewById(R.id.recycleViewInvestment);
        investmentFragment = new InvestmentFragment();
        recyclerView.setAdapter(investmentFragment);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
