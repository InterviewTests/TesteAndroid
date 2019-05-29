package com.example.alessandrofsouza.santanderapp.presentation.pages.contact;

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
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.domain.model.Cell;
import com.example.alessandrofsouza.santanderapp.presentation.utils.Constants;
import com.example.alessandrofsouza.santanderapp.presentation.utils.EmailValidator;
import com.example.alessandrofsouza.santanderapp.presentation.utils.NameValidator;
import com.example.alessandrofsouza.santanderapp.presentation.utils.PhoneValidator;

import java.util.ArrayList;

public class ContactFragment extends RecyclerView.Adapter<ContactFragment.ViewHolder> {

    private static final String TAG = "Santander ";
    private ContactContract contract;
    private EmailValidator emailValidator;
    private ArrayList<Cell> dataSet;
    private View view;
    private Cell cell;
    private Resources resources;

    public CheckBox checkBox;
    public TextInputLayout editLayout;
    public TextInputEditText editTextMail;
    public TextInputEditText editTextName;
    public TextInputEditText editTextPhone;
    public String phoneNumbers;

    private int textlength = 0;
    private int length_before = 0;
    private int MAX_SIZE = 13;

    public boolean checkCheckbox = false;



    public ContactFragment(ContactContract listener) {
        dataSet = new ArrayList<>();
        cell = new Cell();
        contract = listener;
        emailValidator = new EmailValidator();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case Constants.TYPE_FIELD:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_edit_text, viewGroup, false);
                break;

            case Constants.TYPE_SEND:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_button_round, viewGroup, false);
                break;

            case Constants.TYPE_CHECKBOX:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_checkbox, viewGroup, false);
                break;

            case Constants.TYPE_TEXT:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false);
                break;

            default:
                //TODO: change to ImageView or create 1 more field to
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false);
                break;
        }

        return new ViewHolder(view, contract);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        resources = viewHolder.itemView.getContext().getResources();

        switch (cell.getType()){

            case Constants.TYPE_FIELD:
                viewHolder.textInputLayout.setHint(cell.getMessage());
                viewHolder.textInputLayout.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                viewHolder.textInputLayout.setId(cell.getId());
                viewHolder.textInputLayout.setPadding(0, cell.getTopSpacing(), 0, 0);
                viewHolder.textInputLayout.clearFocus();

                switch (cell.getTypefield()) {
                    case Constants.TYPEFIELD_TEXT_T:
                        viewHolder.textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                        viewHolder.textInputEditText.setText(cell.getEditTextValue());
                        editTextName = viewHolder.textInputEditText;
                        editTextName.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                //NameValidator.validateName(charSequence);
                                //styleNameField();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                NameValidator.validateName(editable);
                                styleNameField();
                            }
                        });
                        break;

                    case Constants.TYPEFIELD_TELNUMBER_T:
                        viewHolder.textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                        editTextPhone = viewHolder.textInputEditText;
                        editTextPhone.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                length_before = charSequence.length();
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                formatPhone();
                                //PhoneValidator.validatePhone(charSequence);
                                //stylePhoneField();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                //formatPhone();
                                PhoneValidator.validatePhone(editable);
                                stylePhoneField();
                            }
                        });

                        break;

                    case Constants.TYPEFIELD_EMAIL_T:
                        viewHolder.textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        editLayout = viewHolder.textInputLayout;
                        editTextMail = viewHolder.textInputEditText;
                        editTextMail.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                //EmailValidator.validateEmail(charSequence);
                                //styleEmailField();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                EmailValidator.validateEmail(editable);
                                styleEmailField();
                            }
                        });
                        break;

                    default:
                        viewHolder.textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                }
                break;

            case Constants.TYPE_SEND:
                viewHolder.roundedButton.setText(cell.getMessage());
                viewHolder.roundedButton.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                viewHolder.roundedButton.setId(cell.getId());
                viewHolder.roundedButton.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                viewHolder.roundedButton.setOnClickListener(viewHolder);
                break;

            case Constants.TYPE_CHECKBOX:
                viewHolder.checkBox.setText(cell.getMessage());
                viewHolder.checkBox.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                viewHolder.checkBox.setId(cell.getId());
                viewHolder.checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.txtRegular));
                viewHolder.checkBox.setTypeface(ResourcesCompat.getFont(viewHolder.itemView.getContext(), R.font.dinpro_medium));
                viewHolder.checkBox.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                viewHolder.checkBox.setChecked(cell.isHidden());
                viewHolder.checkBox.setOnCheckedChangeListener(null);
                checkBox = viewHolder.checkBox;

                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
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

            case Constants.TYPE_TEXT:
                viewHolder.textView.setText(cell.getMessage());
                viewHolder.textView.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                viewHolder.textView.setId(cell.getId());
                viewHolder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                viewHolder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.txtRegular));
                break;

            default:
                //TODO: change to ImageView or create 1 more field to
                viewHolder.textView.setText(cell.getMessage());
                viewHolder.textView.setVisibility(cell.isHidden() ? View.GONE : View.VISIBLE);
                viewHolder.textView.setId(cell.getId());
                viewHolder.textView.setPadding(0, cell.getTopSpacing(), 0, cell.getTopSpacing());
                viewHolder.textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.txtRegular));
                break;
        }

    }



    public boolean styleNameField() {
        String nameInput = editTextName.getText().toString().trim();
        if (nameInput.isEmpty()) {
            editTextName.setError("Está vázio");
            editTextName.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "empty");
            return false;

        } else if (!NameValidator.NAME_PATTERN.matcher(nameInput).matches()) {
            editTextName.setError("Está incorreto");
            editTextName.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "incorrect");
            return false;

        } else {
            editTextName.setError(null);
            editTextName.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "OK!");
            return true;
        }
    }


    //TODO: change for utils folder
    public void formatPhone() {
        textlength = editTextPhone.getText().length();
        String text = editTextPhone.getText().toString();
        StringBuilder sb = new StringBuilder(text);

        if (text.endsWith(" ") || text.endsWith("-") || text.endsWith(")") || text.endsWith("(")){
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

        } else if (textlength == 10) {
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

    public boolean stylePhoneField() {
        String phoneInput = editTextPhone.getText().toString().trim();
        phoneNumbers = phoneInput.replaceAll("[^\\d]", "");

        if (phoneNumbers.isEmpty()) {
            editTextPhone.setError("Está vázio");
            editTextPhone.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "empty");
            return false;

        } else if (MAX_SIZE == 13) {

            if (!PhoneValidator.PHONE8_PATTERN.matcher(phoneNumbers).matches()) {
                editTextPhone.setError("Está incorreto");
                editTextPhone.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
                Log.i(TAG, "incorrect");
                return false;

            } else {
                editTextPhone.setError(null);
                editTextPhone.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
                Log.i(TAG, "OK!");
                return true;
            }

        } else if (MAX_SIZE == 14) {

            if (!PhoneValidator.PHONE9_PATTERN.matcher(phoneNumbers).matches()) {
                editTextPhone.setError("Está incorreto");
                editTextPhone.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
                Log.i(TAG, "incorrect");
                return false;

            } else {
                editTextPhone.setError(null);
                editTextPhone.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
                Log.i(TAG, "OK!");
                return true;
            }

        } else {
            editTextPhone.setError(null);
            editTextPhone.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "OK!");
            return true;
        }
    }

    public boolean styleEmailField() {
        String emailInput = editTextMail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            editTextMail.setError("Está vázio");
            editTextMail.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "empty");
            return false;

        } else if (!EmailValidator.EMAIL_PATTERN.matcher(emailInput).matches()) {
            editTextMail.setError("Está incorreto");
            editTextMail.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderRed), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "incorrect");
            return false;

        } else {
            editTextMail.setError(null);
            editTextMail.getBackground().mutate().setColorFilter(resources.getColor(R.color.santanderCorrect), PorterDuff.Mode.SRC_ATOP);
            Log.i(TAG, "OK!");
            return true;
        }
    }






    @Override
    public int getItemViewType(int position) {
        cell = dataSet.get(position);
        int viewType = cell.getType();
        return viewType;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void addListContact(ArrayList<Cell> listContact) {
        dataSet.clear();
        dataSet.addAll(listContact);
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private TextInputLayout textInputLayout;
        private TextInputEditText textInputEditText;
        private Button roundedButton;
        private CheckBox checkBox;

        private ContactContract contactContract;


        public ViewHolder(@NonNull View itemView, ContactContract listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textInputLayout = itemView.findViewById(R.id.editTextLayout);
            textInputEditText = itemView.findViewById(R.id.editTextInput);
            roundedButton = itemView.findViewById(R.id.buttonRound);
            checkBox = itemView.findViewById(R.id.checkBox);

            contactContract = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            contactContract.contract(itemView, getAdapterPosition());
        }
    }
}
