package br.banco.services.app.alert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.banco.services.R;


public class AlertAdapter extends BaseAdapter {

    public String[] values;
    public Context context;
    public LayoutInflater inflater;
    public View createView;
    public ViewHolder holder;

    //public TextView tvLoad;

    public AlertAdapter(Context context, String[] itens) {
        this.values = itens;
        this.context = context;
        this.inflater = inflater;
      //  this.createView = createView;
        //this.holder = holder;
        //this.tvLoad = tvLoad;

    }

    public View getView(int posicao, View convertView, ViewGroup parent) {



        if( convertView == null) {
            createView = LayoutInflater.from(context)
                    .inflate(R.layout.teste, parent, false);

        } else {
            createView = convertView;
        }

        holder = new ViewHolder(createView);
       // holder.tvLoad.setText("SUCESSO HOLDER");


        return  convertView;
    }

    public int getCount() {
        return values.length;
    }

    public Object getItem(int postition) {
        return values[postition];
    }

    public long getItemId(int posicao) {
        return 0;
    }


    public class ViewHolder {

       // final TextView tvLoad;

        public ViewHolder(View view) {
           // tvLoad = (TextView) view.findViewById(R.id.tvLoading);
        }

    }
}