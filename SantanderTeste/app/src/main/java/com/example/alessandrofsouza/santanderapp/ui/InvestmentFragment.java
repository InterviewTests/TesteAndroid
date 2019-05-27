package com.example.alessandrofsouza.santanderapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.adapter.ListaScreenAdapter;
import com.example.alessandrofsouza.santanderapp.model.InvestmentModel;
import com.example.alessandrofsouza.santanderapp.model.Screen;
import com.example.alessandrofsouza.santanderapp.service.ApiService;
import com.vipul.hp_hp.library.Layout_to_Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    public Button btnPrint;

    private View view;

    /*Layout_to_Image layout_to_image;
    ConstraintLayout constraintLayout;
    Bitmap bitmap;
    ImageView imageView;*/

    //private FrameLayout mView;
    //private Bitmap bitmap;
    //private ScrollView mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.investment, container, false);

        recycle(view);//chama o recycleView

        useApi();//chama a API

        btnPrint = view.findViewById(R.id.buttonPrint);
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i(TAG, "PRINT");
                Toast.makeText(getContext(), "Serviço indisponivel no momento", Toast.LENGTH_SHORT).show();
                //Log.d(TAG," "+bitmap.getWidth() +"  "+bitmap.getWidth());

/*
                String timeStamp = System.currentTimeMillis() + "";
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "share"+timeStamp+".png");
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    listaScreenAdapter.bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                Uri uri = FileProvider.getUriForFile(getActivity(), "com.example.alessandrofsouza.santanderapp", file);
                shareIntent.setType("image/*");
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                shareIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(shareIntent, "Compartilhe: "));
*/
            }
        });

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
