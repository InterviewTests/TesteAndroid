package android.teste.com.br.testeandroidapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.teste.com.br.testeandroidapp.R;
import android.teste.com.br.testeandroidapp.entity.Info;
import android.teste.com.br.testeandroidapp.entity.Screen;
import android.teste.com.br.testeandroidapp.utils.RiskBarUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScreenAdapter {
    private Context context;
    private LinearLayout relativeLayout;
    private Screen screen;

    public ScreenAdapter(Context context, LinearLayout relativeLayout, Screen screen) {
        this.context = context;
        this.relativeLayout = relativeLayout;
        this.screen = screen;
    }

    public void preencherLayout() {

        TextView txtTitle       = (TextView) relativeLayout.findViewById(R.id.txt_title);
        TextView txtFundName    = (TextView) relativeLayout.findViewById(R.id.txt_fund_name);
        TextView txtWhatIs      = (TextView) relativeLayout.findViewById(R.id.txt_what_is);
        TextView txtDefinition  = (TextView) relativeLayout.findViewById(R.id.txt_definition);
        TextView txtRiskTitle   = (TextView) relativeLayout.findViewById(R.id.txt_risk_title);
        LinearLayout riskBar    = (LinearLayout) relativeLayout.findViewById(R.id.risk_bar);
        TextView txtInfoTitle   = (TextView) relativeLayout.findViewById(R.id.txt_info_title);

        TextView txtFundMes         = (TextView) relativeLayout.findViewById(R.id.txt_fundo_mes);
        TextView txtFundAno         = (TextView) relativeLayout.findViewById(R.id.txt_fundo_ano);
        TextView txtFundDozeMeses   = (TextView) relativeLayout.findViewById(R.id.txt_fundo_doze_meses);
        TextView txtCdiMes          = (TextView) relativeLayout.findViewById(R.id.txt_cdi_mes);
        TextView txtCdiAno          = (TextView) relativeLayout.findViewById(R.id.txt_cdi_ano);
        TextView txtCdiDozeMeses    = (TextView) relativeLayout.findViewById(R.id.txt_cdi_doze_meses);

        LinearLayout infoContainer = (LinearLayout) relativeLayout.findViewById(R.id.info_container);



        txtTitle.setText(screen.getTitle());
        txtFundName.setText(screen.getFundName());
        txtWhatIs.setText(screen.getWhatIs());
        txtDefinition.setText(screen.getDefinition());
        txtRiskTitle.setText(screen.getRiskTitle());
        RiskBarUtils.select(riskBar, screen.getRisk());

        txtInfoTitle.setText(screen.getInfoTitle());

        txtFundMes.setText(String.format("%s%%", screen.getFundMes().toString()));
        txtFundAno.setText(String.format("%s%%", screen.getFundAno().toString()));
        txtFundDozeMeses.setText(String.format("%s%%", screen.getFundDozeMeses().toString()));
        txtCdiMes.setText(String.format("%s%%", screen.getCdiMes().toString()));
        txtCdiAno.setText(String.format("%s%%", screen.getCdiAno().toString()));
        txtCdiDozeMeses.setText(String.format("%s%%", screen.getCdiDozeMeses().toString()));


        for (Info info : screen.getInfo()) {
            adicionarInfoField(infoContainer, info);
        }

        for (Info info : screen.getDownInfo()) {
            adicionarDownInfoField(infoContainer, info);
        }
    }

    private void adicionarInfoField(LinearLayout infoContainer, Info info) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView txtName = new TextView(context);
        TextView txtData = new TextView(context);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        lp.weight = 0.6f;
        txtName.setLayoutParams(lp);
        lp.weight = 0.4f;
        txtData.setLayoutParams(lp);

        txtName.setText(info.getName());
        txtData.setText(info.getData());

        txtData.setTextColor(Color.BLACK);
        txtData.setGravity(Gravity.END);

        linearLayout.addView(txtName);
        linearLayout.addView(txtData);
        infoContainer.addView(linearLayout);
    }

    private void adicionarDownInfoField(LinearLayout infoContainer, Info info) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView txtName = new TextView(context);
        ImageView btnDownload = new ImageView(context);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        lp.weight = 0.6f;
        txtName.setLayoutParams(lp);
        lp.weight = 0.4f;

        txtName.setText(info.getName());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                45
        );
        btnDownload.setLayoutParams(layoutParams);
        btnDownload.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_baixar));

        linearLayout.addView(txtName);
        linearLayout.addView(btnDownload);
        infoContainer.addView(linearLayout);
    }
}
