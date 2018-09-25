package com.santander.wesleyalves.santandercode.fundosinvestimento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.santander.wesleyalves.santandercode.R;
import com.santander.wesleyalves.santandercode.fundosinvestimento.domain.model.Info;

import java.util.ArrayList;

public class FundosInvestimentoInformacoesListAdapter extends ArrayAdapter<Info> {

    private ArrayList<Info> dataSet;
    Context context;

    private static class ViewHolder {
        TextView txt_nome;
        TextView txt_data;
    }


    public FundosInvestimentoInformacoesListAdapter(Context context, ArrayList<Info> data) {
        super(context, R.layout.list_info_fundos, data);
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Info dataModel = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_info_fundos, parent, false);
            viewHolder.txt_nome = (TextView) convertView.findViewById(R.id.txt_info_nome);
            viewHolder.txt_data = (TextView) convertView.findViewById(R.id.txt_info_data);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txt_nome.setText(dataModel.getName());
        viewHolder.txt_data.setText(dataModel.getData());

        return convertView;
    }
}
