package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
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
import java.util.Locale;

public class ListaCellAdapter extends RecyclerView.Adapter<ListaCellAdapter.ViewHolder> {

    private FragmentCommunication mListener;
    private ArrayList<Cell> dataSet;
    private static final String TAG = "Santander ";
    View view;
    public CheckBox checkBox;
    public TextInputEditText editTextName;
    public TextInputEditText editTextMail;
    public TextInputEditText editTextPhone;
    TextInputLayout editLayout;
    Context context;
    private int textlength = 0;
    int length_before = 0;
    int MAX_SIZE = 14;

    public boolean checkCheckbox = false;
    public boolean checkEmail = false;
    public boolean checkName = false;
    public boolean checkPhone = false;

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
                    holder.textInputLayout.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);

                    if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TEXT_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                        holder.textInputEditText.setText(dataSet.get(position).getEditTextValue());
                        editTextName = holder.textInputEditText;

                        editTextName.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                dataSet.get(position).setEditTextValue(editTextName.getText().toString());
                                validateName();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {}
                        });


                    } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_EMAIL_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        editLayout = (TextInputLayout) holder.textInputLayout;
                        editTextMail = (TextInputEditText) holder.textInputEditText;

                        editTextMail.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                dataSet.get(position).setEditTextValue(editTextMail.getText().toString());
                                validateEmail();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {}
                        });

                    } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TELNUMBER_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                        editTextPhone = (TextInputEditText) holder.textInputEditText;

                        editTextPhone.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                length_before = charSequence.length();
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                dataSet.get(position).setEditTextValue(editTextPhone.getText().toString());
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                formatPhone(editable);
                                validatePhone();
                            }
                        });
                    }
                    break;

                case Utils.TYPE_SEND:
                    holder.roundedButton.setText(cell.getMessage());
                    holder.roundedButton.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.roundedButton.setOnClickListener(holder);
                    holder.roundedButton.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);

                    /*
                    holder.roundedButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Log.i(TAG, "countSends = " + getItemCount());
                        }
                    });
                    */

                    break;

                case Utils.TYPE_CHECKBOX:
                    holder.checkBox.setText(cell.getMessage());
                    holder.checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
                    holder.checkBox.setTypeface(ResourcesCompat.getFont(holder.itemView.getContext(), R.font.dinpro_medium));
                    holder.checkBox.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.checkBox.setChecked(cell.isHidden());
                    holder.checkBox.setOnCheckedChangeListener(null);
                    holder.checkBox.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                    checkBox = (CheckBox) holder.checkBox;

                    holder.checkBox.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            if(checkBox.isChecked()) {
                                checkPhone = true;
                                editLayout.setVisibility(View.VISIBLE);
                            } else {
                                checkPhone = false;
                                editLayout.setVisibility(View.GONE);
                            }
                        }
                    });
                    break;

                case Utils.TYPE_TEXT:
                    holder.textView.setText(cell.getMessage());
                    holder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
                    holder.textView.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                    break;

                default:
                    holder.textView.setText(cell.getMessage());
                    holder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                    holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.txtRegular));
                    holder.textView.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                    break;
            }
        }
    }

    private void formatPhone(Editable e) {
        textlength = editTextPhone.getText().length();
        String text = editTextPhone.getText().toString();
        StringBuilder sb = new StringBuilder(text);

        if (text.endsWith(" ")){
            //return;
            editTextPhone.setText(sb.delete(text.length() - 1, text.length()).toString());
            editTextPhone.setSelection(editTextPhone.getText().length());
        }

        if (textlength == 1) {
            if (!text.contains("(")) {
                editTextPhone.setText(sb.insert(text.length() - 1, "(").toString());
                editTextPhone.setSelection(editTextPhone.getText().length());
            }

        } else if (textlength == 4) {
            if (!text.contains(")")) {
                editTextPhone.setText(sb.insert(text.length() - 1, ") ").toString());
                editTextPhone.setSelection(editTextPhone.getText().length());
            }

        } else if (textlength == 11) {
            int i = 5;
            if (String.valueOf(sb.charAt(i)).equals(9) || String.valueOf(sb.charAt(i)).equals("9")) {
                MAX_SIZE = 15;
                if (!text.contains("-")) {
                    editTextPhone.setText(sb.insert(10, "-").toString());
                    editTextPhone.setSelection(editTextPhone.getText().length());
                }
            } else {
                MAX_SIZE = 14;
                if (!text.contains("-")) {
                    editTextPhone.setText(sb.insert(9, "-").toString());
                    editTextPhone.setSelection(editTextPhone.getText().length());
                }
            }

        }

        if (textlength > MAX_SIZE) {
            editTextPhone.setText(sb.delete(text.length() - 1, text.length()).toString());
            editTextPhone.setSelection(editTextPhone.getText().length());
        }
    }


    private boolean validateName() {
        String nameInput = editTextName.getText().toString().trim();
        if (nameInput.isEmpty()) {
            editTextName.setError("Nome não pode esta vázio");
            return false;

        } else if (!nameInput.matches("^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$")) {
            editTextName.setError("Nome não ter numeros ou caracters especiais");
            return false;
        } else {
            checkName = true;
            return true;
        }

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
            checkEmail = true;
            return true;
        }
    }

    private boolean validatePhone() {
        String phoneInput = editTextPhone.getText().toString().trim();
        String phoneNumbers = phoneInput.replaceAll("[^\\d]", "");
        Log.i(TAG, String.valueOf(phoneInput));

        if (phoneNumbers.isEmpty()) {
            editTextPhone.setError("telefone não pode esta vázio");
            return false;

        } else if (MAX_SIZE == 14) {
            if(!phoneNumbers.matches("^[+]?[0-9]{10}$")) {
                editTextPhone.setError("Favor insira um telefone válido");
                return false;
            } else {
                checkPhone = true;
                return true;
            }

        } else if (MAX_SIZE == 15) {
            if(!phoneNumbers.matches("^[+]?[0-9]{11}$")) {
                editTextPhone.setError("Favor insira um telefone válido");
                return false;
            } else {
                checkPhone = true;
                return true;
            }

        } else {
            editTextPhone.setError(null);
            checkPhone = true;
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

            textInputLayout = itemView.findViewById(R.id.editTextLayout);
            textInputEditText = itemView.findViewById(R.id.editTextInput);
            textView = itemView.findViewById(R.id.nameTV);
            roundedButton = itemView.findViewById(R.id.buttonRound);
            checkBox = itemView.findViewById(R.id.checkBox);

            fragmentCommunication = listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            fragmentCommunication.respond(itemView, getAdapterPosition());
        }
    }


}
