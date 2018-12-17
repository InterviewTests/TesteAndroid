package br.com.tisoares.app.testeandroid.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.tisoares.app.testeandroid.Holder.InfoHolder;
import br.com.tisoares.app.testeandroid.Model.Info;
import br.com.tisoares.app.testeandroid.R;

/**
 * Created by TIAGO SOARES on 17/12/2018.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoHolder>  {


    private final List<Info> mInfos;

    public InfoAdapter(ArrayList infos) {
        mInfos = infos;
    }

    @NonNull
    @Override
    public InfoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new InfoHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.info_line_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InfoHolder infoHolder, int i) {
        infoHolder.txtName.setText(mInfos.get(i).getName());
        infoHolder.txtData.setText(mInfos.get(i).getData());
    }

    @Override
    public int getItemCount() {
        return mInfos != null ? mInfos.size() : 0;
    }
}
