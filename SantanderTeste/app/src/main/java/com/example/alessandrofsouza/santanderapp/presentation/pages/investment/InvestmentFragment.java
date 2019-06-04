package com.example.alessandrofsouza.santanderapp.presentation.pages.investment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.domain.model.Infos;
import com.example.alessandrofsouza.santanderapp.domain.model.Screen;

import java.util.ArrayList;
import java.util.Collections;

public class InvestmentFragment extends RecyclerView.Adapter<InvestmentFragment.ViewHolder> {

    private static final String TAG = "Santander ";
    public ArrayList<Screen> dataScreenSet;
    private RecyclerView recyclerView;
    private InvestmentInfoAdapter investmentInfoAdapter;
    private Context context;
    private Resources resources;
    private View view;
    private Screen screen;


    //public ImageView imageView;
    //public Bitmap bitmap;
    //public ConstraintLayout constraintLayout;
    //public Layout_to_Image layout_to_image;


    public InvestmentFragment() {
        dataScreenSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.frame_layout_invest, viewGroup, false);
        context = view.getContext();

        recycleInfo(view, context);//chama o recycleView
        recycleDownInfo(view, context);//chama o recycleView

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        resources = viewHolder.itemView.getContext().getResources();
        screen = dataScreenSet.get(position);
        View view = viewHolder.itemView.getRootView();

        viewHolder.investTitle.setText(screen.getTitle());
        viewHolder.investTitle.setPadding(0, ((int) resources.getDimension(R.dimen.lMargin)), 0, 0);

        viewHolder.investFundName.setText(screen.getFundName());
        viewHolder.investFundName.setPadding(0, ((int) resources.getDimension(R.dimen.mMargin)), 0, 0);

        viewHolder.whatIs.setText(screen.getWhatIs());
        viewHolder.whatIs.setPadding(0, ((int) resources.getDimension(R.dimen.sMargin)), 0, 0);

        viewHolder.definition.setText(screen.getDefinition());
        viewHolder.definition.setPadding(0, ((int) resources.getDimension(R.dimen.sMargin) / 2), 0, 0);

        viewHolder.riskTitle.setText(screen.getRiskTitle());
        viewHolder.riskTitle.setPadding(0, ((int) resources.getDimension(R.dimen.sMargin)), 0, 0);


//        viewHolder.infoTitle.setText(screen.getInfoTitle());
//        viewHolder.moreInfo_fundo_month.setText(screen.getMoreInfo().getMonth().getFund().toString().concat("%"));
//        viewHolder.moreInfo_cdi_month.setText(screen.getMoreInfo().getMonth().getCDI().toString().concat("%"));
//        viewHolder.moreInfo_fundo_year.setText(screen.getMoreInfo().getYear().getFund().toString().concat("%"));
//        viewHolder.moreInfo_cdi_year.setText(screen.getMoreInfo().getYear().getCDI().toString().concat("%"));
//        viewHolder.moreInfo_fundo_12months.setText(screen.getMoreInfo().getMonths12().getFund().toString().concat("%"));
//        viewHolder.moreInfo_cdi_12months.setText(screen.getMoreInfo().getMonths12().getCDI().toString().concat("%"));


        final ViewTreeObserver observer = viewHolder.viewColors1.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                switch (screen.getRisk()){
                    case 1:
                        viewHolder.viewColors1.setScaleY(2);
                        viewHolder.arrowRisk.setX(viewHolder.viewColors1.getX() + viewHolder.viewColors1.getPivotX() - viewHolder.arrowRisk.getWidth() / 5);
                        break;
                    case 2:
                        viewHolder.viewColors2.setScaleY(2);
                        viewHolder.arrowRisk.setX(viewHolder.viewColors2.getX() + viewHolder.viewColors2.getPivotX() - viewHolder.arrowRisk.getWidth() / 5);
                        break;
                    case 3:
                        viewHolder.viewColors3.setScaleY(2);
                        viewHolder.arrowRisk.setX(viewHolder.viewColors3.getX() + viewHolder.viewColors3.getPivotX() - viewHolder.arrowRisk.getWidth() / 5);
                        break;
                    case 4:
                        viewHolder.viewColors4.setScaleY(2);
                        viewHolder.arrowRisk.setX(viewHolder.viewColors4.getX() + viewHolder.viewColors4.getPivotX() - viewHolder.arrowRisk.getWidth() / 5);
                        break;
                    case 5:
                        viewHolder.viewColors5.setScaleY(2);
                        viewHolder.arrowRisk.setX(viewHolder.viewColors1.getX() + viewHolder.viewColors1.getPivotX() - viewHolder.arrowRisk.getWidth() / 5);
                        break;
                    default:
                        viewHolder.arrowRisk.setX(viewHolder.viewColors1.getX() - viewHolder.viewColors1.getPivotX() - viewHolder.arrowRisk.getWidth() / 5);
                        break;
                }
            }
        });


        ArrayList<Infos> listInfo = screen.getInfo();
        investmentInfoAdapter.addListInfo(listInfo);

        ArrayList<Infos> listDownInfo = screen.getDownInfo();
        investmentInfoAdapter.addListInfo(listDownInfo);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataScreenSet.size();
    }

    public void addListInvestment(Screen listScreen) {
        dataScreenSet.clear();
        dataScreenSet.addAll(Collections.singleton(listScreen));
        notifyDataSetChanged();
    }


    private void recycleInfo(View root, Context context) {
        recyclerView = root.findViewById(R.id.recycleViewInfo);
        investmentInfoAdapter = new InvestmentInfoAdapter();
        recyclerView.setAdapter(investmentInfoAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void recycleDownInfo(View root, Context context) {
        recyclerView = root.findViewById(R.id.recycleViewDownInfo);
        investmentInfoAdapter = new InvestmentInfoAdapter();
        recyclerView.setAdapter(investmentInfoAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView investTitle;
        private TextView investFundName;
        private TextView whatIs;
        private TextView definition;
        private TextView riskTitle;

//        private TextView infoTitle;
//        private TextView moreInfo_fundo_month;
//        private TextView moreInfo_cdi_month;
//        private TextView moreInfo_fundo_year;
//        private TextView moreInfo_cdi_year;
//        private TextView moreInfo_fundo_12months;
//        private TextView moreInfo_cdi_12months;

        public RecyclerView recycleViewInfo;

        private View viewColors1;
        private View viewColors2;
        private View viewColors3;
        private View viewColors4;
        private View viewColors5;

        private ImageView arrowRisk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            investTitle = itemView.findViewById(R.id.investTitle);
            investFundName = itemView.findViewById(R.id.investFundName);
            whatIs = itemView.findViewById(R.id.whatIs);
            definition = itemView.findViewById(R.id.definition);
            riskTitle = itemView.findViewById(R.id.riskTitle);

//            infoTitle = itemView.findViewById(R.id.infoTitle);
//            moreInfo_fundo_month = itemView.findViewById(R.id.moreInfo_fundo_month);
//            moreInfo_cdi_month = itemView.findViewById(R.id.moreInfo_cdi_month);
//            moreInfo_fundo_year = itemView.findViewById(R.id.moreInfo_fundo_year);
//            moreInfo_cdi_year = itemView.findViewById(R.id.moreInfo_cdi_year);
//            moreInfo_fundo_12months = itemView.findViewById(R.id.moreInfo_fundo_12months);
//            moreInfo_cdi_12months = itemView.findViewById(R.id.moreInfo_cdi_12months);

            recycleViewInfo = itemView.findViewById(R.id.recycleViewInfo);


//            viewColors1 = itemView.findViewById(R.id.viewColors1);
//            viewColors2 = itemView.findViewById(R.id.viewColors2);
//            viewColors3 = itemView.findViewById(R.id.viewColors3);
//            viewColors4 = itemView.findViewById(R.id.viewColors4);
//            viewColors5 = itemView.findViewById(R.id.viewColors5);
//
//            arrowRisk = itemView.findViewById(R.id.arrowRisk);
        }
    }
}


