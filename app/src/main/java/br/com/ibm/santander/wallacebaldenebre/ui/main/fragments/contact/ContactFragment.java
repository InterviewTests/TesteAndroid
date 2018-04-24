package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.contact;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.ibm.santander.wallacebaldenebre.R;
import br.com.ibm.santander.wallacebaldenebre.model.Cell;
import br.com.ibm.santander.wallacebaldenebre.model.TypeField;
import br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.contact.success.SuccessFragment;
import br.com.ibm.santander.wallacebaldenebre.utils.Helper;
import br.com.ibm.santander.wallacebaldenebre.utils.MaskPhone;

public class ContactFragment extends Fragment implements ContactContract.View, View.OnClickListener, View.OnTouchListener {
    TextView tvwIntroduce;
    CheckBox cbxRegister;

    TextInputLayout tilName;
    TextInputEditText tietName;

    TextInputLayout tilEmail;
    TextInputEditText tietEmail;

    TextInputLayout tilPhone;
    TextInputEditText tietPhone;

    Button btnSend;
    ProgressBar pbr;
    ContactPresenter presenter;

    private ProgressDialog pDialog;

    // URL_API to get contacts JSON
    ArrayList<HashMap<String, String>> fieldsList;

    public ContactFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        tietName.setText("");
        tietEmail.setText("");
        tietPhone.setText("");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_contact, container, false);

        setUp(view);

        showData();

        return view;
    }

    private boolean verifyFields() {
        boolean isName, isEmail, isPhone;

        if (tietName.getText().toString().isEmpty()) {
            isName = false;
            tilName.setError(getString(R.string.str_emptyfield));
            tilName.setErrorEnabled(true);
        } else {
            isName = true;
            tilName.setErrorEnabled(false);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tietEmail.getText().toString()).matches()) {
            isEmail = false;
            tilEmail.setError(getString(R.string.str_invalidemail));
            tilEmail.setErrorEnabled(true);
        } else {
            isEmail = true;
            tilEmail.setErrorEnabled(false);
        }

        if (!Helper.isPhone(tietPhone.getText().toString())) {
            isPhone = false;
            tilPhone.setError(getString(R.string.str_invalidphone));
            tilPhone.setErrorEnabled(true);
        } else {
            isPhone = true;
            tilPhone.setErrorEnabled(false);
        }

        return isName && isEmail && isPhone;
    }

    @Override
    public void showProgress() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setTitle("Aguarde...");
        pDialog.setMessage("Carregando os dados...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    public void hideProgress() {
        if (pDialog.isShowing()) pDialog.dismiss();
    }

    @Override
    public void hideKeyboard() {
        Helper.hideSoftInput(getActivity());
    }

    @Override
    public void showData() {
        presenter.showDataFormFields(this, new ContactCallback<HashMap<String, Cell[]>>() {
            @Override
            public void onSuccess(HashMap<String, Cell[]> data) {
                for (Map.Entry<String, Cell[]> entry : data.entrySet()) {
                    final Cell[] value = entry.getValue();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //  setta os dados na view
                            tvwIntroduce.setId(value[0].getId());
                            tvwIntroduce.setText(value[0].getMessage());
                            tvwIntroduce.setLayoutParams(Helper.margins(0, value[0].getTopSpacing(), 0, 0));

                            tilName.setId(value[1].getId());
                            tilName.setHint(value[1].getMessage());
                            tietName.setInputType(Helper.doubleToInt(Double.parseDouble((String) value[1].getTypeField())));
                            tilName.setLayoutParams(Helper.margins(0, value[1].getTopSpacing(), 0, 0));

                            cbxRegister.setId(value[4].getId());
                            cbxRegister.setText(value[4].getMessage());
                            cbxRegister.setLayoutParams(Helper.margins(0, value[4].getTopSpacing(), 0, 0));

                            tilEmail.setId(value[2].getId());
                            tilEmail.setHint(value[2].getMessage());
                            tietEmail.setInputType(value[2].getTypeField() != null ? InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS : TypeField.EMAIL.getValue());
                            tilEmail.setLayoutParams(Helper.margins(0, value[2].getTopSpacing(), 0, 0));

                            tilPhone.setId(value[3].getId());
                            tilPhone.setHint(value[3].getMessage());
                            tietPhone.setInputType(InputType.TYPE_CLASS_PHONE);
                            tilPhone.setLayoutParams(Helper.margins(0, value[3].getTopSpacing(), 0, 0));

                            btnSend.setId(value[5].getId());
                            btnSend.setText(value[5].getMessage());
                            btnSend.setLayoutParams(Helper.margins(0, value[5].getTopSpacing(), 0, 0));
                            btnSend.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    hideKeyboard();
                                    if (verifyFields())
                                        Helper.loadFragment(new SuccessFragment(), true, new Bundle(), getActivity());
                                }
                            });

                        }
                    });
                }
            }

            @Override
            public void onFailure(int errorCode, String reason) {

            }
        });
    }

    @Override
    public void setUp(View view) {
        presenter = new ContactPresenter();
        presenter.onAttach(this);

        casts(view);
        fieldsList = new ArrayList<>();

        tietPhone.addTextChangedListener(new MaskPhone().insert("(##)#####-####", tietPhone));
    }

    @Override
    public void casts(View view) {
        pbr = view.findViewById(R.id.pbr_contact);
        tvwIntroduce = view.findViewById(R.id.tvw_contact_introduce);
        tilName = view.findViewById(R.id.til_contact_name);
        tietName = view.findViewById(R.id.tiet_contact_name);
        tilEmail = view.findViewById(R.id.til_contact_email);
        tietEmail = view.findViewById(R.id.tiet_contact_email);
        tilPhone = view.findViewById(R.id.til_contact_phone);
        tietPhone = view.findViewById(R.id.tiet_contact_phone);
        btnSend = view.findViewById(R.id.btn_contact_send);
        btnSend.setOnTouchListener(this);
        cbxRegister = view.findViewById(R.id.cbx_contact_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_success_newmessage:
                Helper.toast(getActivity(), "OI");
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v == btnSend) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.setAlpha(0.7f);
            } else {
                v.setAlpha(1f);
            }
        }
        return false;
    }

}
