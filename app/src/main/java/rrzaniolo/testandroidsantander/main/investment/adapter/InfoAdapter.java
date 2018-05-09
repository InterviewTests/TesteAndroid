package rrzaniolo.testandroidsantander.main.investment.adapter;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.network.investment.models.BaseInfo;
import rrzaniolo.testandroidsantander.utils.Utils;

/**
 * RecyclerView adapter for InfoList.
 * */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>{
    //region --- Variables
    private List<BaseInfo> infoList;
    public List<BaseInfo> getInfoList() {
        return infoList;
    }
    public void setInfoList(List<BaseInfo> infoList) {
        this.infoList = infoList;
    }
    //endregion

    //region --- Constructors
    public InfoAdapter(List<BaseInfo> infoList) {
        setInfoList(infoList);
    }
    //endregion

    //region --- RecyclerView Adapter methods
    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_simple_info, viewGroup, false);

        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        holder.fromInfo(getInfoList().get(position));

        ((TextView)holder.getView().findViewById(R.id.vSimpleInfo_tvTitle)).setText(holder.getTitle());

        if(Utils.isNotNullNorEmpty(holder.getInfo())) {
            ((TextView)holder.getView().findViewById(R.id.vSimpleInfo_tvInfo)).setText(holder.getInfo());
            holder.getView().findViewById(R.id.vSimpleInfo_tvInfo).setVisibility(View.VISIBLE);
            holder.getView().findViewById(R.id.vSimpleInfo_tvDownload).setVisibility(View.GONE);
        }else{
            holder.getView().findViewById(R.id.vSimpleInfo_tvInfo).setVisibility(View.GONE);
            holder.getView().findViewById(R.id.vSimpleInfo_tvDownload).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return getInfoList().size();
    }
    //endregion

    //region --- ViewHolder
    class InfoViewHolder extends RecyclerView.ViewHolder{
        //region --- Variables
        private View view;
        public View getView() {
            return view;
        }
        public void setView(View view) {
            this.view = view;
        }

        private String title;
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        private String info;
        public String getInfo() {
            return info;
        }
        public void setInfo(String info) {
            this.info = info;
        }
        //endregion


        //region --- Constructors
        InfoViewHolder(View view) {
            super(view);
            setView(view);
        }

        public InfoViewHolder(View view, String title, String info) {
            super(view);
            setView(view);
            setTitle(title);
            setInfo(info);
        }
        //endregion

        //region --- Public Methods
        void fromInfo(BaseInfo baseInfo){
            setTitle(baseInfo.getName());
            setInfo(baseInfo.getData());
        }
        //endregion
    }
    //endregion
}
