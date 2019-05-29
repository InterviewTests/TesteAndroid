package com.example.alessandrofsouza.santanderapp.presentation.pages.contact;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.domain.model.Cell;
import com.example.alessandrofsouza.santanderapp.domain.model.ContactModel;
import com.example.alessandrofsouza.santanderapp.data.service.ApiService;
import com.example.alessandrofsouza.santanderapp.presentation.utils.EmailValidator;
import com.example.alessandrofsouza.santanderapp.presentation.utils.NameValidator;
import com.example.alessandrofsouza.santanderapp.presentation.utils.PhoneValidator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class ContactPresenter extends Fragment {

    private static final String TAG = "Santander ";
    private ApiService service;
    private ContactFragment contactFragment;
    private RecyclerView recyclerView;
    private ContactContract contract;
    private ConstraintLayout layout1;
    private ConstraintLayout layout2;
    private Button btnBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.contact, container, false);

        layout1 = view.findViewById(R.id.constLayout1);
        layout2 = view.findViewById(R.id.constLayout2);
        btnBack = view.findViewById(R.id.buttonBack);

        service = new ApiService();
        getDataApi();
        recycleView(view);

        returnContactLayout(view);

        return view;
    }


    private void getDataApi() {
        service.getApi().listCells().enqueue(new Callback<ContactModel>() {
            @NonNull
            @Override
            public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {
                if (response.isSuccessful()) {
                    ContactModel contactModel = response.body();

                    ArrayList<Cell> listCell = contactModel.getCells();
                    contactFragment.addListContact(listCell);

                } else {
                    Log.e(TAG, "Error Unsuccessful " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ContactModel> call, Throwable t) {
                Log.e(TAG, "Error Failure " + t.getMessage());
            }
        });
    }


    private void recycleView(View view) {
        recyclerView = view.findViewById(R.id.recycleViewContact);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        contractActionExtra();
        contactFragment = new ContactFragment(contract);
        recyclerView.setAdapter(contactFragment);
    }


    private void contractActionExtra() {
        contract = new ContactContract() {
            @Override
            public void contract(View view, int position) {
                switch (position) {
                    case 5:
                        if (!contactFragment.checkCheckbox) {
                            if (NameValidator.NAME_PATTERN.matcher(contactFragment.editTextName.getText().toString().trim()).matches() && PhoneValidator.PHONE8_PATTERN.matcher(contactFragment.phoneNumbers.trim()).matches() ||
                                NameValidator.NAME_PATTERN.matcher(contactFragment.editTextName.getText().toString().trim()).matches() && PhoneValidator.PHONE9_PATTERN.matcher(contactFragment.phoneNumbers.trim()).matches()) {
                                layout1.setVisibility(View.GONE);
                                layout2.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (NameValidator.NAME_PATTERN.matcher(contactFragment.editTextName.getText().toString().trim()).matches() && EmailValidator.EMAIL_PATTERN.matcher(contactFragment.editTextMail.getText().toString().trim()).matches() && PhoneValidator.PHONE8_PATTERN.matcher(contactFragment.phoneNumbers.trim()).matches() ||
                                NameValidator.NAME_PATTERN.matcher(contactFragment.editTextName.getText().toString().trim()).matches() && EmailValidator.EMAIL_PATTERN.matcher(contactFragment.editTextMail.getText().toString().trim()).matches() && PhoneValidator.PHONE9_PATTERN.matcher(contactFragment.phoneNumbers.trim()).matches()) {
                                layout1.setVisibility(View.GONE);
                                layout2.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
            }
        };
    }


    private void returnContactLayout(View view) {
        btnBack = view.findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactFragment.editTextMail.setText(null);
                contactFragment.editTextName.setText(null);
                contactFragment.editTextPhone.setText(null);
                contactFragment.checkBox.setChecked(false);
                contactFragment.editLayout.setVisibility(View.GONE);

                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        });
    }

}
