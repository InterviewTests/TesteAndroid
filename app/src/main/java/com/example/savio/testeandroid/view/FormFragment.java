package com.example.savio.testeandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.savio.testeandroid.R;
import com.example.savio.testeandroid.SuccessActivity;
import com.example.savio.testeandroid.contract.FormContract;
import com.example.savio.testeandroid.mask.PhoneMask;
import com.example.savio.testeandroid.presenter.FormPresenter;

import java.lang.ref.WeakReference;
import java.util.List;

public class FormFragment extends Fragment implements FormContract.View{

    private FormPresenter formPresenter;

    PhoneMask phoneMask;

    View view;

    TextView title;
    EditText name, email, phone;
    CheckBox check;
    Button btnSend;

    public FormFragment() {}


//-----------------------------------LifeCycle------------------------------------------------------//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form, container, false);

        //instance the presenter
        formPresenter = new FormPresenter(this);

        //call the function to get de datas
        formPresenter.requestData();

        return view;

    }//onCreate ends


//-----------------------------------Override-------------------------------------------------------//

    @Override
    public void initView() {

        title = view.findViewById(R.id.form_title);
        name = view.findViewById(R.id.form_name);
        email = view.findViewById(R.id.form_email);
        phone = view.findViewById(R.id.form_phone);
        check = view.findViewById(R.id.form_check);
        btnSend =  view.findViewById(R.id.form_btn);

        //add the mask of the phone number
        phoneMask = new PhoneMask(new WeakReference<>(phone));
        phone.addTextChangedListener(phoneMask);

        //wait the button click
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fieldValidation();
            }
        });
    }

    @Override
    public void setViewData(List<Object> messages) {

        title.setText(messages.get(0).toString());
        name.setHint(messages.get(1).toString());
        email.setHint(messages.get(2).toString());
        phone.setHint(messages.get(3).toString());
        check.setText(messages.get(4).toString());
        btnSend.setText(messages.get(5).toString());
    }


//-----------------------------------My Functions---------------------------------------------------//

    //resume all valitions of the fields
    public void fieldValidation(){

        //empty validation
        if(!TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(phone.getText())){

            //email address validation
            if(Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches() ){

                if(phone.getText().toString().length() > 13){

                    //open successful screen
                    startActivity(new Intent(getContext(), SuccessActivity.class));
                }
                else{

                    validationMessage("Não é um telefone válido.");
                }
            }
            else{

                validationMessage("Não é um email válido.");
            }
        }
        else{

            validationMessage("Todos os campos são obrigatórios.");
        }
    }

    //show tooltip message
    public void validationMessage(String message){

        Snackbar snackbar = Snackbar.make(view.findViewById(R.id.layout_form), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
