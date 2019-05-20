package com.example.alessandrofsouza.santanderapp.ui;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.adapter.InfoAdapter;
import com.example.alessandrofsouza.santanderapp.adapter.ListaCellAdapter;
import com.example.alessandrofsouza.santanderapp.adapter.ListaScreenAdapter;
import com.example.alessandrofsouza.santanderapp.model.Cell;
import com.example.alessandrofsouza.santanderapp.model.ContactModel;
import com.example.alessandrofsouza.santanderapp.model.Infos;
import com.example.alessandrofsouza.santanderapp.model.InvestmentModel;
import com.example.alessandrofsouza.santanderapp.model.MoreInfo;
import com.example.alessandrofsouza.santanderapp.model.Screen;
import com.example.alessandrofsouza.santanderapp.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvestmentFragment extends Fragment {

    private Retrofit retrofit;
    private static final String TAG = "Santander Investment";
    private RecyclerView recyclerView;
    private ListaScreenAdapter listaScreenAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.investment, container, false);

        TextView tv = view.findViewById(R.id.textTitle);
        tv.setText(R.string.investimento);

        recycle(view);//chama o recycleView

        useApi();//chama a API

        return view;
    }



    private void recycle(View view) {
        recyclerView = view.findViewById(R.id.recycleViewInvestment);
        listaScreenAdapter = new ListaScreenAdapter();
        recyclerView.setAdapter(listaScreenAdapter);
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
        final Call<InvestmentModel> requestCells = service.listInvestment();

        requestCells.enqueue(new Callback<InvestmentModel>() {
            @Override
            public void onResponse(Call<InvestmentModel> call, Response<InvestmentModel> response) {

                if(response.isSuccessful()) {
                    InvestmentModel investmentModel = response.body();
                    Screen listScreen = investmentModel.getScreen();
                    listaScreenAdapter.addListScreen(listScreen);

                    //ArrayList<Infos> listInfo = screen.getInfo();
                    //adapter.addListInfo(listScreen.getInfo());

                    /*
                    Log.i(TAG, investmentModel.screen.infoTitle);

                    for (Infos i : investmentModel.screen.info) {
                        Log.i(TAG, String.format("%s : %s", i.name, i.data));
                        Log.i(TAG, "___________");
                    }

                    for (Infos i : investmentModel.screen.downInfo) {
                        Log.i(TAG, String.format("%s : %s", i.name, i.data));
                        Log.i(TAG, "___________");
                    }

                    Log.i(TAG, investmentModel.screen.moreInfo.month.CDI.toString());
                    */


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

}
