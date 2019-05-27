package com.example.alessandrofsouza.santanderapp.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import java.util.regex.Pattern;

public class ListaCellAdapter extends RecyclerView.Adapter<ListaCellAdapter.ViewHolder> {

    private static final String TAG = "Santander ";

    private FragmentCommunication mListener;
    private ArrayList<Cell> dataSet;

    public TextInputEditText editTextName;
    public TextInputEditText editTextMail;
    public TextInputEditText editTextPhone;
    public TextInputLayout editLayout;

    public CheckBox checkBox;
    public boolean checkCheckbox = false;

    private int textlength = 0;
    private int length_before = 0;
    private int MAX_SIZE = 13;
    private View view;
    private Context context;
    private Resources res;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    "){1,2}")
    );

    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$"
    );

    private static final Pattern PHONE8_PATTERN = Pattern.compile(
            "^[+]?[0-9]{10}$"
    );

    private static final Pattern PHONE9_PATTERN = Pattern.compile(
            "^[+]?[0-9]{11}$"
    );



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
        res = holder.itemView.getContext().getResources();

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
                        final String name = editTextName.getText().toString();

                        editTextName.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                //dataSet.get(position).setEditTextValue(editTextName.getText().toString());
                                validateName(name);
                                styleNameField();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                validateName(name);
                                styleNameField();
                            }
                        });


                    } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_EMAIL_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        editLayout = (TextInputLayout) holder.textInputLayout;
                        editTextMail = (TextInputEditText) holder.textInputEditText;
                        //editTextMail.addTextChangedListener(emailValidator);
                        //dataSet.get(position).setEditTextValue(editTextMail.getText().toString());
                        final String mail = editTextMail.getText().toString();

                        editTextMail.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                validateEmail(mail);
                                styleEmailField();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                validateEmail(mail);
                                styleEmailField();
                            }
                        });


                    } else if (cell.getTypefield().equals(String.valueOf(Utils.TYPEFIELD_TELNUMBER_T))) {
                        holder.textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                        editTextPhone = (TextInputEditText) holder.textInputEditText;
                        final String phone = editTextPhone.getText().toString();

                        editTextPhone.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                length_before = charSequence.length();
                                //validatePhone();
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                //dataSet.get(position).setEditTextValue(editTextPhone.getText().toString());
                                //validatePhone();
                                formatPhone();
                                validatePhone(phone);
                                stylePhoneField();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                formatPhone();
                                validatePhone(phone);
                                stylePhoneField();
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

                            if (!emailValidator.isValid()) {
                                editTextMail.setError("Erro: "+emailValidator.textValidatorEmailReturn);
                                Log.w(TAG, "Erro: "+emailValidator.textValidatorEmailReturn);
                                checkEmail = false;
                                return;
                            } else {
                                checkEmail = true;
                            }
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
                                checkCheckbox = true;
                                editLayout.setVisibility(View.VISIBLE);
                            } else {
                                checkCheckbox = false;
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





    public static boolean validateName(CharSequence name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public boolean styleNameField() {
        String nameInput = editTextName.getText().toString().trim();

        if (nameInput.isEmpty()) {
            editTextName.setError("Nome não pode esta vázio");
            editTextName.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            return false;

        } else if (!NAME_PATTERN.matcher(nameInput).matches()) {
            editTextName.setError("Nome não ter numeros ou caracters especiais");
            editTextName.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            return false;
        } else {
            editTextName.setError(null);
            editTextName.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
            return true;
        }
    }







    public static boolean validateEmail(CharSequence email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean styleEmailField() {
        String emailInput = editTextMail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            editTextMail.setError("E-mail não pode esta vázio");
            editTextMail.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            return false;

        } else if (!EMAIL_PATTERN.matcher(emailInput).matches()) {
            editTextMail.setError("Favor insira um E-mail válido");
            editTextMail.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            return false;

        } else {
            editTextMail.setError(null);
            editTextMail.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
            return true;
        }
    }





    public void formatPhone() {
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
                editTextPhone.setText(sb.insert(text.length() - 1, ")").toString());
                editTextPhone.setSelection(editTextPhone.getText().length());
            }

        } else if (textlength == 9) {
            int i = 4;
            if (String.valueOf(sb.charAt(i)).equals(9) || String.valueOf(sb.charAt(i)).equals("9")) {
                MAX_SIZE = 14;
                if (!text.contains("-")) {
                    editTextPhone.setText(sb.insert(9, "-").toString());
                    editTextPhone.setSelection(editTextPhone.getText().length());
                }
            } else {
                MAX_SIZE = 13;
                if (!text.contains("-")) {
                    editTextPhone.setText(sb.insert(8, "-").toString());
                    editTextPhone.setSelection(editTextPhone.getText().length());
                }
            }

        }

        if (textlength > MAX_SIZE) {
            editTextPhone.setText(sb.delete(text.length() - 1, text.length()).toString());
            editTextPhone.setSelection(editTextPhone.getText().length());
        }
    }

    public static boolean validatePhone(CharSequence phone) {
        return phone != null && PHONE8_PATTERN.matcher(phone).matches() || phone != null && PHONE9_PATTERN.matcher(phone).matches();
    }

    public boolean stylePhoneField() {
        String phoneInput = editTextPhone.getText().toString().trim();
        String phoneNumbers = phoneInput.replaceAll("[^\\d]", "");

        if (phoneNumbers.isEmpty()) {
            editTextPhone.setError("telefone não pode esta vázio");
            editTextPhone.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            return false;

        } else if (MAX_SIZE == 13) {
            if (!PHONE8_PATTERN.matcher(phoneNumbers).matches()) {
                editTextPhone.setError("Favor insira um telefone válido");
                editTextPhone.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
                return false;

            } else {
                editTextPhone.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
                return true;
            }

        } else if (MAX_SIZE == 14) {
            if (!PHONE9_PATTERN.matcher(phoneNumbers).matches()) {
                editTextPhone.setError("Favor insira um telefone válido");
                editTextPhone.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
                return false;

            } else {
                editTextPhone.getBackground().mutate().setColorFilter(res.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
                return true;
            }

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
