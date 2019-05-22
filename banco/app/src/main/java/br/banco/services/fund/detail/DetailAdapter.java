package br.banco.services.fund.detail;

/**
 *
 *  Estas CLASSES devem ser externas para DESACOPLAR o layout e ser reaproveitavel
 *
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.banco.services.R;
import br.banco.services.fund.domain.national.Fund;
import br.banco.services.fund.domain.national.ScreenFundTemplate;


public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ScreenFundTemplate> dataSet;
    Context mContext;
    int total_types;
    private int adapterPosition;


    /**
     *
     *  INICIO DO LAYOUT - FD_header, FD_body, FD_footer
     *
     */


    // inicio do body

    public static class FDFundVH extends RecyclerView.ViewHolder {

        TextView DT_fd_title;
        TextView DT_fd_fundName;
        TextView DT_fd_whatIs;
        TextView DT_fd_definition;
        TextView DT_fd_riskTitle;
        LinearLayout  DT_fd_chartBarLevelTemplate;
        LinearLayout  DT_fd_chartBarLevelTemplate_Aply;
        View DT_fd_chart_arrow_4;
        View DT_fd_chart_frame_4;

        public FDFundVH(View itemView) {
            super(itemView);

            this.DT_fd_title = (TextView) itemView.findViewById(R.id.DT_fd_title);
            this.DT_fd_fundName = (TextView) itemView.findViewById(R.id.DT_fd_fundName);
            this.DT_fd_whatIs = (TextView) itemView.findViewById(R.id.DT_fd_whatIs);
            this.DT_fd_definition = (TextView) itemView.findViewById(R.id.DT_fd_definition);
            this.DT_fd_riskTitle = (TextView) itemView.findViewById(R.id.DT_fd_riskTitle);

            this.DT_fd_chart_arrow_4 = (View) itemView.findViewById(R.id.DT_fd_chart_arrow_4);
            this.DT_fd_chart_frame_4 = (View) itemView.findViewById(R.id.DT_fd_chart_frame_4);

        }

    }

    public static class FDMoreinfoVH extends RecyclerView.ViewHolder {


        TextView MI_lb_co1_titulo;
        TextView MI_lb_co1_titulo1;
        TextView MI_lb_co1_titulo2;
        TextView MI_lb_co1_titulo3;

        TextView MI_lb_co2_titulo;
        TextView MI_lb_co2_titulo1;
        TextView MI_lb_co2_titulo2;
        TextView MI_lb_co2_titulo3;

        TextView MI_lb_co3_titulo;
        TextView MI_lb_co3_titulo1;
        TextView MI_lb_co3_titulo2;
        TextView MI_lb_co3_titulo3;


        //TextView TV2;
      //  LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.template_linearlayout, null);

        public FDMoreinfoVH(View itemView) {
            super(itemView);

             this.MI_lb_co1_titulo = (TextView) itemView.findViewById(R.id.MI_lb_co1_titulo);
             this.MI_lb_co1_titulo1 = (TextView) itemView.findViewById(R.id.MI_lb_co1_titulo1);
             this.MI_lb_co1_titulo2 = (TextView) itemView.findViewById(R.id.MI_lb_co1_titulo2);
             this.MI_lb_co1_titulo3 = (TextView) itemView.findViewById(R.id.MI_lb_co1_titulo3);

             this.MI_lb_co2_titulo = (TextView) itemView.findViewById(R.id.MI_lb_co2_titulo);
             this.MI_lb_co2_titulo1 = (TextView) itemView.findViewById(R.id.MI_lb_co2_titulo1);
             this.MI_lb_co2_titulo2 = (TextView) itemView.findViewById(R.id.MI_lb_co2_titulo2);
             this.MI_lb_co2_titulo3 = (TextView) itemView.findViewById(R.id.MI_lb_co2_titulo3);

             this.MI_lb_co3_titulo = (TextView) itemView.findViewById(R.id.MI_lb_co3_titulo);
             this.MI_lb_co3_titulo1 = (TextView) itemView.findViewById(R.id.MI_lb_co3_titulo1);
             this.MI_lb_co3_titulo2 = (TextView) itemView.findViewById(R.id.MI_lb_co3_titulo2);
             this.MI_lb_co3_titulo3 = (TextView) itemView.findViewById(R.id.MI_lb_co3_titulo3);

        }

    }

    public static class FDInfoVH extends RecyclerView.ViewHolder {

        TextView IF_lb_co1_titulo;
        TextView IF_lb_co2_titulo;

        public FDInfoVH(View itemView) {
            super(itemView);

            this.IF_lb_co1_titulo = (TextView) itemView.findViewById(R.id.IF_lb_co1_titulo);
            this.IF_lb_co2_titulo = (TextView) itemView.findViewById(R.id.IF_lb_co2_titulo);

        }

    }

    public static class FDDownInfoVH extends RecyclerView.ViewHolder {


        TextView DL_lb_co1_titulo;
        Button DL_lb_co2_titulo;

        public FDDownInfoVH(View itemView) {
            super(itemView);

            this.DL_lb_co1_titulo = (TextView) itemView.findViewById(R.id.DL_lb_co1_titulo);
            this.DL_lb_co2_titulo = (Button) itemView.findViewById(R.id.DL_lb_co2_titulo);

        }

    }

    /**
     *
     *  FINAL DO LAYOUT - parte generica
     *
     */


    public static class FD_footer extends RecyclerView.ViewHolder {

        Button btDetailView;

        public FD_footer(View itemView) {
            super(itemView);
            this.btDetailView = (Button) itemView.findViewById(R.id.btDetailView);

        }

    }

    public DetailAdapter(ArrayList<ScreenFundTemplate> data, Context context) {

        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {

            /**
             *  aqui caberia um header
             *
             */

            case ScreenFundTemplate.FUND_TYPE:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_detail_fund_type, parent, false);
                return new FDFundVH(view);

            case ScreenFundTemplate.MOREINFO_TYPE:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_detail_moreinfo_type, parent, false);
                return new FDMoreinfoVH(view);

            case ScreenFundTemplate.INFO_TYPE:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_detail_info_type, parent, false);
                return new FDInfoVH(view);

            case ScreenFundTemplate.DOWNLOAD_TYPE:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_detail_download_type, parent, false);
                return new FDDownInfoVH(view);
            case ScreenFundTemplate.FOOTER_TYPE:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_detail_footer_type, parent, false);
                return new FD_footer(view);

        }
        return null;

    }

    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).getType()) {
            case 0:
                return ScreenFundTemplate.FUND_TYPE;
            case 1:
                return ScreenFundTemplate.MOREINFO_TYPE;
            case 2:
                return ScreenFundTemplate.INFO_TYPE;
            case 3:
                return ScreenFundTemplate.DOWNLOAD_TYPE;
            case 12:
                return ScreenFundTemplate.FOOTER_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        ScreenFundTemplate model = dataSet.get(listPosition);


        if (model != null) {
            switch (model.type) {
                case ScreenFundTemplate.FUND_TYPE:

                    ((FDFundVH) holder).DT_fd_title.setText(model.getSession()); // fundos de..
                    ((FDFundVH) holder).DT_fd_fundName.setText(model.getTitle()); // Vince..
                    ((FDFundVH) holder).DT_fd_whatIs.setText(model.getSubtitle()); // o Que..
                    ((FDFundVH) holder).DT_fd_definition.setText(model.getDescription()); // O fundo...
                    ((FDFundVH) holder).DT_fd_riskTitle.setText(model.getChartTitle()); // Grau de ..

                   // LinearLayout layout = new LinearLayout(this);

                   // ((FDFundVH) holder).DT_fd_chart_arrow_4.setBackgroundTintMode();

                   //((FDFundVH) holder).DT_fd_chart_arrow_4.setLayoutParams(params);


                    break;
                case ScreenFundTemplate.MOREINFO_TYPE:

                    /**
                     *  aqui deve ser uma CLASSE INJETADA em oura classe
                     *
                     */

                      ((FDMoreinfoVH) holder).MI_lb_co1_titulo.setText("");
                      ((FDMoreinfoVH) holder).MI_lb_co1_titulo1.setText(""+model.getTitle());
                      ((FDMoreinfoVH) holder).MI_lb_co1_titulo2.setText(""+model.getTableTitle());
                      ((FDMoreinfoVH) holder).MI_lb_co1_titulo3.setText(""+model.getTableValue());

                    ((FDMoreinfoVH) holder).MI_lb_co2_titulo.setText(""+model.getChartTitle());
                    ((FDMoreinfoVH) holder).MI_lb_co2_titulo1.setText("0.3%");
                    ((FDMoreinfoVH) holder).MI_lb_co2_titulo2.setText("12.01%");
                    ((FDMoreinfoVH) holder).MI_lb_co2_titulo3.setText("17.9%");

                    ((FDMoreinfoVH) holder).MI_lb_co3_titulo.setText(""+model.getSession());
                    ((FDMoreinfoVH) holder).MI_lb_co3_titulo1.setText("03%");
                    ((FDMoreinfoVH) holder).MI_lb_co3_titulo2.setText("12%");
                    ((FDMoreinfoVH) holder).MI_lb_co3_titulo3.setText("17.6%");

                    break;
                case ScreenFundTemplate.INFO_TYPE:

                    ((FDInfoVH) holder).IF_lb_co1_titulo.setText(model.getTableTitle());
                    ((FDInfoVH) holder).IF_lb_co2_titulo.setText(model.getTableValue());

                    break;
                case ScreenFundTemplate.DOWNLOAD_TYPE:

                    ((FDDownInfoVH) holder).DL_lb_co1_titulo.setText(model.getTableTitle());
                    ((FDDownInfoVH) holder).DL_lb_co2_titulo.setText(model.getAdressLabel());
                    // Adress Link

                    ((FDDownInfoVH) holder).DL_lb_co2_titulo.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Toast.makeText(mContext, "Baixando Arquivo...", Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
                    case ScreenFundTemplate.FOOTER_TYPE:

                    ((FD_footer) holder).btDetailView.setText(model.getAdressLabel());

                    break;




            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
