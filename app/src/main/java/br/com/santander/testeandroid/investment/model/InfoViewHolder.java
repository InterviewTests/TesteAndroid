package br.com.santander.testeandroid.investment.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.utils.Utils;

public class InfoViewHolder extends RecyclerView.ViewHolder {
    private TextView vSimpleInfo_tvTitle;
    private TextView vSimpleInfo_tvInfo;
    private TextView vSimpleInfo_tvDownload;

    public InfoViewHolder(View view) {
        super(view);

        vSimpleInfo_tvTitle = (TextView) view.findViewById(R.id.vSimpleInfo_tvTitle);
        vSimpleInfo_tvInfo = (TextView) view.findViewById(R.id.vSimpleInfo_tvInfo);
        vSimpleInfo_tvDownload = (TextView) view.findViewById(R.id.vSimpleInfo_tvDownload);
    }

    public static void bindData(InfoViewHolder holder, BaseInfo baseInfo) {

        holder.vSimpleInfo_tvTitle.setText(baseInfo.getName());

        if (Utils.isNotNullNorEmpty(baseInfo.getData())) {
            holder.vSimpleInfo_tvInfo.setText(baseInfo.getData());
            holder.vSimpleInfo_tvInfo.setVisibility(View.VISIBLE);
            holder.vSimpleInfo_tvDownload.setVisibility(View.GONE);
        } else {
            holder.vSimpleInfo_tvInfo.setVisibility(View.GONE);
            holder.vSimpleInfo_tvDownload.setVisibility(View.VISIBLE);
        }
    }

}
