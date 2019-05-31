package br.banco.services.app.design;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import br.banco.services.R;

public class TemplateForm extends AppCompatActivity  {

    final String TAG = "LOADR";



    private TextView TP_field;
    private EditText TP_name;
    private EditText TP_email;
    private EditText TP_telNumber;
    private EditText TP_password;

    private Button btnSubmit;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_form);

        registerViews();

    }





    private void registerViews() {



        TP_field = (TextView) findViewById(R.id.TP_field);
        TP_field.setText("Titulo da Mensagem aqui");

        TP_password = (EditText) findViewById(R.id.TP_password);
        //TP_password.setError("Por favor Digite um valor nesse campo");
        TP_password.setError(null);

        TP_name = (EditText) findViewById(R.id.TP_name);
        TP_name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


        // TextWatcher would let us check validation error on the fly
        TP_name.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(TP_name);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        TP_email = (EditText) findViewById(R.id.TP_email);
        TP_email.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(TP_email, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        TP_telNumber = (EditText) findViewById(R.id.TP_telNumber);
        TP_telNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(TP_telNumber, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        btnSubmit = (Button) findViewById(R.id.TP_buttom);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if ( checkValidation () )
                    submitForm();
                else
                    Toast.makeText(TemplateForm.this, "Form contains error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        // Submit your form here. your form is valid
        Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(TP_name)) ret = false;
        if (!Validation.isEmailAddress(TP_email, true)) ret = false;
        if (!Validation.isPhoneNumber(TP_telNumber, false)) ret = false;

        return ret;
    }
}





