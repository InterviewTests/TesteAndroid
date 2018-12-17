package br.com.tisoares.app.testeandroid.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.tisoares.app.testeandroid.R;

/**
 * Created by TIAGO SOARES on 17/12/2018.
 */
public class InfoHolder extends RecyclerView.ViewHolder {

    public TextView txtName, txtData;

    public InfoHolder(View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txt_name);
        txtData = itemView.findViewById(R.id.txt_down_data);
    }
}