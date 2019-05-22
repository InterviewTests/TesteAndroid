package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.model.Cell;
import com.example.alessandrofsouza.santanderapp.utils.Utils;

import java.util.ArrayList;

public class ListaCellAdapter extends RecyclerView.Adapter<ListaCellAdapter.ViewHolder> {

    private FragmentCommunication mListener;
    private ArrayList<Cell> dataSet;
    private static final String TAG = "Santander ";
    View view;
    CheckBox checkBox;
    TextInputEditText editTextName;
    TextInputEditText editTextMail;
    TextInputEditText editTextPhone;
    TextInputLayout editLayout;
    Context context;


    public ListaCellAdapter(FragmentCommunication listener) {
        dataSet = new ArrayList<>();
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder( final ViewGroup parent, int viewType) {

        switch (viewType){
            case Utils.TYPE_FIELD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_text, parent, false);
                break;

            case Utils.TYPE_SEND:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button_round, parent, false);
                break;

            case Utils.TYPE_CHECKBOX:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkbox, parent, false);
                break;

            case Utils.TYPE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
                break;

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
                break;
        }

        return new ViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Cell cell = dataSet.get(position);
        Resources res = holder.itemView.getContext().getResources();


        if (holder instanceof ViewHolder) {
            ViewHolder rowHolder = (ViewHolder) holder;

            switch (cell.getType()){
                case Utils.TYPE_FIELD:
                    holder.textInputLayout.setHint(cell.getMessage());
                    holder.textInputLayout.setPadding(0, cell.getTopSpacing(), 0, 0);
                    holder.textInputLayout.clearFocus();

                    if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TEXT_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                        editTextName = holder.textInputEditText;
                        editTextName = (TextInputEditText) holder.textInputEditText;
                        validateName();

                    } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_EMAIL_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        editLayout = (TextInputLayout) holder.textInputLayout;
                        editLayout.setVisibility(View.GONE);
                        editTextMail = (TextInputEditText) holder.textInputEditText;
                        validateEmail();

                    } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TELNUMBER_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                        editTextPhone = (TextInputEditText) holder.textInputEditText;
                        validatePhone();
                    }
                    break;

                case Utils.TYPE_SEND:
                    holder.roundedButton.setText(cell.getMessage());
                    holder.roundedButton.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.roundedButton.setOnClickListener(holder);
                    break;

                case Utils.TYPE_CHECKBOX:
                    holder.checkBox.setText(cell.getMessage());
                    holder.checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
                    holder.checkBox.setTypeface(ResourcesCompat.getFont(holder.itemView.getContext(), R.font.dinpro_medium));
                    holder.checkBox.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.checkBox.setChecked(cell.isHidden());
                    holder.checkBox.setOnCheckedChangeListener(null);

                    holder.checkBox.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            checkBox = (CheckBox) v;
                            if(checkBox.isChecked()) {
                                editLayout.setVisibility(View.VISIBLE);
                            } else {
                                editLayout.setVisibility(View.GONE);
                            }
                        }
                    });
                    break;

                case Utils.TYPE_TEXT:
                    holder.textView.setText("");
                    holder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
                    break;

                default:
                    holder.textView.setText("");
                    holder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
                    break;
            }
        }
    }


    private boolean validateName() {
        return false;
    }

    private boolean validateEmail() {
        String emailInput = editTextMail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            editTextMail.setError("E-mail não pode esta vázio");
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            editTextMail.setError("Favor insira um E-mail válido");
            return false;

        } else {
            editTextMail.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String phoneInput = editTextPhone.getText().toString().trim();

        if (phoneInput.isEmpty()) {
            editTextPhone.setError("E-mail não pode esta vázio");
            return false;

        } else if(phoneInput.matches("^[+]?[0-9]{10,13}$")) {
            editTextPhone.setError("Favor insira um E-mail válido");
            return false;

        } else {
            editTextPhone.setError(null);
            return true;
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
        dataSet.clear();
        dataSet.addAll(listCell);
        notifyDataSetChanged();
    }





    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private TextInputLayout textInputLayout;
        private TextInputEditText textInputEditText;
        private TextView textView;
        private Button roundedButton;
        private CheckBox checkBox;

        private FragmentCommunication fragmentCommunication;

        public ViewHolder(@NonNull View itemView, FragmentCommunication listener) {
            super(itemView);

            fragmentCommunication = listener;
            itemView.setOnClickListener(this);

            textInputLayout = itemView.findViewById(R.id.editTextLayout);
            textInputEditText = itemView.findViewById(R.id.editTextInput);
            textView = itemView.findViewById(R.id.nameTV);
            roundedButton = itemView.findViewById(R.id.buttonRound);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        @Override
        public void onClick(View v) {
            fragmentCommunication.respond(itemView, getAdapterPosition());
        }
    }


}
