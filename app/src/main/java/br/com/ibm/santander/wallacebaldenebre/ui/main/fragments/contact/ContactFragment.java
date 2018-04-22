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

import br.com.ibm.santander.wallacebaldenebre.R;
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
    private static String URL_API = "https://floating-mountain-50292.herokuapp.com/cells.json";
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
            tilName.setError("Esta campo está vázio");
            tilName.setErrorEnabled(true);
        } else {
            isName = true;
            tilName.setErrorEnabled(false);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tietEmail.getText().toString()).matches()) {
            isEmail = false;
            tilEmail.setError("Email inválido");
            tilEmail.setErrorEnabled(true);
        } else {
            isEmail = true;
            tilEmail.setErrorEnabled(false);
        }

        if (!Helper.isPhone(tietPhone.getText().toString())) {
            isPhone = false;
            tilPhone.setError("Número de telefone/celular inválido");
            tilPhone.setErrorEnabled(true);
        } else {
            isPhone = true;
            tilPhone.setErrorEnabled(false);
        }

        return isName && isEmail && isPhone;
    }

    @Override
    public void showProgress() {
        pbr.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbr.setVisibility(View.GONE);
    }

    @Override
    public void hideKeyboard() {
        Helper.hideSoftInput(getActivity());
    }

    @Override
    public void showData() {
        new GetFormFields().execute();
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

    public class GetFormFields extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setTitle("Aguarde...");
            pDialog.setMessage("Estamos carregando o formulário de contato.");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String jsonString = Helper.callAPI(URL_API);

            if (jsonString != null) {
                try {
                    JSONObject jo = new JSONObject(jsonString);

                    //  JSON Array do Cells
                    JSONArray cells = jo.getJSONArray("cells");

                    //  pegar todos os contatos
                    for (int i = 0; i < cells.length(); i++) {
                        JSONObject cell = cells.getJSONObject(i);

                        final int id = cell.getInt("id");
                        int type = cell.getInt("type");
                        final String message = cell.getString("message");
                        final Object typeField = cell.getString("typefield");
                        boolean hidden = cell.getBoolean("hidden");
                        final int topSpacing = cell.getInt("topSpacing");
                        Object show = cell.getString("show");
                        boolean required = cell.getBoolean("required");

                        HashMap<String, String> tempCell = new HashMap<>();
                        tempCell.put("id", String.valueOf(id));
                        tempCell.put("type", String.valueOf(type));
                        tempCell.put("message", message);
                        tempCell.put("typeField", String.valueOf(typeField));
                        tempCell.put("hidden", String.valueOf(hidden));
                        tempCell.put("topSpacing", String.valueOf(topSpacing));
                        tempCell.put("show", (String) show);
                        tempCell.put("required", String.valueOf(required));

                        fieldsList.add(tempCell);

                        //  setta os dados na view
                        switch (id) {
                            case 1:
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvwIntroduce.setId(id);
                                        tvwIntroduce.setText(message);
                                        tvwIntroduce.setLayoutParams(Helper.margins(0, topSpacing, 0, 0));
                                    }
                                });
                                break;
                            case 2:
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tilName.setId(id);
                                        tilName.setHint(message);
                                        tietName.setInputType(Helper.doubleToInt(Double.parseDouble((String) typeField)));
                                        tilName.setLayoutParams(Helper.margins(0, topSpacing, 0, 0));
                                    }
                                });
                                break;
                            case 3:
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        cbxRegister.setId(id);
                                        cbxRegister.setText(message);
                                        cbxRegister.setLayoutParams(Helper.margins(0, topSpacing, 0, 0));
                                    }
                                });
                                break;
                            case 4:
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tilEmail.setId(id);
                                        tilEmail.setHint(message);
                                        tietEmail.setInputType(typeField != null ? InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS : TypeField.EMAIL.getValue());
                                        tilEmail.setLayoutParams(Helper.margins(0, topSpacing, 0, 0));
                                    }
                                });
                                break;
                            case 6:
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tilPhone.setId(id);
                                        tilPhone.setHint(message);
                                        tietPhone.setInputType(InputType.TYPE_CLASS_PHONE);
                                        tilPhone.setLayoutParams(Helper.margins(0, topSpacing, 0, 0));
                                    }
                                });
                                break;
                            case 7:
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnSend.setId(id);
                                        btnSend.setText(message);
                                        btnSend.setLayoutParams(Helper.margins(0, topSpacing, 0, 0));
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
                                break;
                        }
                    }

                } catch (final JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Helper.snackbar(getView(), getString(R.string.str_msgerror_contactsupport));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
        }
    }
}
