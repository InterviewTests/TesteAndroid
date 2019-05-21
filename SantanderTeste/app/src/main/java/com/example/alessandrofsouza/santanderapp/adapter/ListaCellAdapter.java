package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.MainActivity;
import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.model.Cell;
import com.example.alessandrofsouza.santanderapp.utils.Utils;

import java.util.ArrayList;

public class ListaCellAdapter extends RecyclerView.Adapter<ListaCellAdapter.ViewHolder> {

    private ArrayList<Cell> dataSet;

    private static final String TAG = "Santander ";

    public ListaCellAdapter() {
        dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View viewEdit = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_text, parent, false);
        View viewText = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        View viewButton = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button_round, parent, false);
        View viewCheck = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);


        if (viewType == Utils.TYPE_FIELD) {
            final ViewHolder vh = new ViewHolder(viewCheck);
            final Cell cell = dataSet.get(viewType);

            /*vh.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.i(TAG, "change? " + cell.getShow());

                    if(isChecked) {
                        vh.textInputLayout.setVisibility(View.VISIBLE);
                    }
                }
            });*/

            return new ViewHolder(viewEdit);

        } else if (viewType == Utils.TYPE_SEND) {

            final ViewHolder vh = new ViewHolder(viewButton);
            vh.roundedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE );
                    //inflater.inflate( R.layout.success, null );
                    Log.i(TAG, "countSends = " + getItemCount());
                }
            });

            return new ViewHolder(viewButton);

        } else if (viewType == Utils.TYPE_CHECKBOX) {
            return new ViewHolder(viewCheck);
        }




        return new ViewHolder(viewText);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Cell cell = dataSet.get(position);
        Resources res = holder.itemView.getContext().getResources();

        //Edit Texts
        if (cell.getType() == Utils.TYPE_FIELD) {
            holder.textInputLayout.setPadding(0, cell.getTopSpacing(), 0, 0);
            holder.textInputLayout.setHint(cell.getMessage());

            //Tipos de teclado
            if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TEXT_T))) {
                holder.textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                holder.textInputLayout.setVisibility(View.GONE);

            } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_EMAIL_T))) {
                holder.textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TELNUMBER_T))) {
                holder.textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
            }


        //button
        } else if (cell.getType() == Utils.TYPE_SEND) {
            holder.roundedButton.setText(cell.getMessage());
            holder.roundedButton.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());


            //checkbox
        } else if (cell.getType() == Utils.TYPE_CHECKBOX) {
            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox.setText(cell.getMessage());
            holder.checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
            holder.checkBox.setTypeface(ResourcesCompat.getFont(holder.itemView.getContext(), R.font.dinpro_medium));
            holder.checkBox.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
            holder.checkBox.setChecked(cell.isHidden());

            /*holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //set your object's last status
                    holder.textInputLayout.setVisibility(View.VISIBLE);
                }
            });*/

            //texto
        } else {
            //holder.textView.setText(cell.getMessage());
            holder.textView.setText("");
            holder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
            holder.textView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Cell cell = dataSet.get(position);
        int viewType = cell.getType();

        return viewType;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addListCell(ArrayList<Cell> listCell) {
        dataSet.addAll(listCell);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextInputLayout textInputLayout;
        private TextInputEditText textInputEditText;
        private TextView textView;
        private Button roundedButton;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textInputLayout = itemView.findViewById(R.id.editTextLayout);
            textInputEditText = itemView.findViewById(R.id.editTextInput);
            textView = itemView.findViewById(R.id.nameTV);
            roundedButton = itemView.findViewById(R.id.buttonRound);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
