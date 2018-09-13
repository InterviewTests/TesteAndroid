package com.nataliafavero.santander.ui.detailFund;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nataliafavero.santander.R;

import java.util.List;

/**
 * Created by nataliafavero on 13/09/18.
 */

public class InfoBaseAdapter extends BaseAdapter {

    private final List<DetailFundInfoModel> infos;
    private final Fragment fragment;

    public InfoBaseAdapter(List<DetailFundInfoModel> infos, Fragment fragment) {
        this.infos = infos;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = fragment.getLayoutInflater().inflate(R.layout.listview_more_info, parent, false);
        DetailFundInfoModel info = infos.get(position);

        TextView name = (TextView) view.findViewById(R.id.listview_more_info_title);
        TextView fund = (TextView) view.findViewById(R.id.listview_more_info_fund);
        TextView data = (TextView) view.findViewById(R.id.listview_more_info_data);

        name.setText(info.getName());
        fund.setText(info.getFund());
        data.setText(info.getData());

        return view;
    }
}
