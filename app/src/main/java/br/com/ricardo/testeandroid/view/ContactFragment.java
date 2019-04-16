package br.com.ricardo.testeandroid.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.testeandroid.R;
import br.com.ricardo.testeandroid.model.ContactInteractor;
import br.com.ricardo.testeandroid.model.ContactInteractorImpl;
import br.com.ricardo.testeandroid.model.Cell;
import br.com.ricardo.testeandroid.presenter.ContactPresenter;
import br.com.ricardo.testeandroid.presenter.ContactPresenterImpl;
import br.com.ricardo.testeandroid.view.Utils.MaskEditUtil;


public class ContactFragment extends Fragment implements ContactView{

    private LinearLayout linearContactContainer;
    private TextView textContactTitle;
    private TextInputLayout inputContactName;
    private TextInputLayout inputContactEmail;
    private TextInputLayout inputContactPhone;
    private EditText editContactPhone;
    private CheckBox checkContactRegisterEmail;
    private Button buttonContactSend;
    private ProgressBar progressBarContact;


    private FragmentManager fragmentManagerContact;
    private FragmentTransaction fragmentTransactionContact;

    private Typeface font;
    private List<Cell> cellList;

    //Model
    private ContactInteractor contactInteractor;

    //Presenter
    private ContactPresenter contactPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View contactView = inflater.inflate(R.layout.fragment_contact, container, false);

        contactInteractor = new ContactInteractorImpl();
        contactPresenter = new ContactPresenterImpl(contactInteractor);
        cellList = new ArrayList<>();

        linearContactContainer = (LinearLayout) contactView.findViewById(R.id.contact_linear_container);
        progressBarContact = (ProgressBar) contactView.findViewById(R.id.contact_progress_bar);
        textContactTitle = (TextView) contactView.findViewById(R.id.contact_text_title);
        inputContactName = (TextInputLayout) contactView.findViewById(R.id.contact_input_name);
        inputContactEmail = (TextInputLayout) contactView.findViewById(R.id.contact_input_email);
        inputContactPhone = (TextInputLayout) contactView.findViewById(R.id.contact_input_phone);
        editContactPhone = (EditText) contactView.findViewById(R.id.contact_edit_phone);
        checkContactRegisterEmail = (CheckBox) contactView.findViewById(R.id.contact_check_register_email);
        buttonContactSend = (Button) contactView.findViewById(R.id.contact_button_send);


        editContactPhone.addTextChangedListener(MaskEditUtil.mask(editContactPhone, MaskEditUtil.FORMAT_FONE_9));


        linearContactContainer.setVisibility(View.GONE);
        progressBarContact.setVisibility(View.VISIBLE);

        inputContactEmail.setEnabled(false);

        checkContactRegisterEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    inputContactEmail.setEnabled(true);
                } else {
                    inputContactEmail.setEnabled(false);
                }
            }
        });

        buttonContactSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = String.valueOf(inputContactName.getEditText().getText());
                String email = String.valueOf(inputContactEmail.getEditText().getText());
                String phone = String.valueOf(inputContactPhone.getEditText().getText());


                if(validaCampos(name, email, phone)){

                    fragmentManagerContact = getActivity().getSupportFragmentManager();
                    fragmentTransactionContact = fragmentManagerContact.beginTransaction();
                    fragmentTransactionContact.replace(R.id.main_frame_container, new MessageFragment());
                    fragmentTransactionContact.commit();

                }
            }
        });

        contactPresenter.requestContactsField();

        font = Typeface.createFromAsset(getActivity().getAssets(), "font/DINPro-Medium.otf");
        textContactTitle.setTypeface(font);
        inputContactName.setTypeface(font);
        inputContactEmail.setTypeface(font);
        inputContactPhone.setTypeface(font);
        checkContactRegisterEmail.setTypeface(font);
        buttonContactSend.setTypeface(font);


        return contactView;

    }

    //Método para validar os campos
    public boolean validaCampos(String name, String email, String phone){

        boolean check = false;


        if(checkContactRegisterEmail.isChecked()){
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)){
                Toast.makeText(getActivity(), "Campo vazio", Toast.LENGTH_SHORT).show();
            } else {
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    check = true;
                } else {
                    Toast.makeText(getActivity(), "Email inválido", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)){
                check = true;
            } else {
                Toast.makeText(getActivity(), "Campo vazio", Toast.LENGTH_SHORT).show();
            }
        }

        return check;
    }

    @Override
    public void addTextField(Cell item) {

        cellList.add(item);

        linearContactContainer.setVisibility(View.VISIBLE);
        progressBarContact.setVisibility(View.GONE);

        for(int i = 0; i < cellList.size(); i++){

            switch (cellList.get(i).getId()){

                case 1:
                    textContactTitle.setMinHeight(cellList.get(i).getTopSpacing());
                    textContactTitle.setText(cellList.get(i).getMessage());
                    textContactTitle.setMinHeight(cellList.get(i).getTopSpacing());
                    break;

                case 2:
                    inputContactName.setMinimumHeight(cellList.get(i).getTopSpacing());
                    inputContactName.setHint(cellList.get(i).getMessage());
                    inputContactName.setMinimumHeight(cellList.get(i).getTopSpacing());
                    break;

                case 3:
                    checkContactRegisterEmail.setMinHeight(cellList.get(i).getTopSpacing());
                    checkContactRegisterEmail.setText(cellList.get(i).getMessage());
                    checkContactRegisterEmail.setMinHeight(cellList.get(i).getTopSpacing());
                    break;

                case 4:
                    inputContactEmail.setMinimumHeight(cellList.get(i).getTopSpacing());
                    inputContactEmail.setHint(cellList.get(i).getMessage());
                    inputContactEmail.setMinimumHeight(cellList.get(i).getTopSpacing());
                    break;

                case 6:
                    inputContactPhone.setMinimumHeight(cellList.get(i).getTopSpacing());
                    inputContactPhone.setHint(cellList.get(i).getMessage());
                    inputContactPhone.setMinimumHeight(cellList.get(i).getTopSpacing());
                    break;

                case 7:
                    buttonContactSend.setMinimumHeight(cellList.get(i).getTopSpacing());
                    buttonContactSend.setText(cellList.get(i).getMessage());
                    buttonContactSend.setMinimumHeight(cellList.get(i).getTopSpacing());
                    break;
            }

        }
    }


    @Override
    public void onStart() {
        super.onStart();

        contactPresenter.attachView(this);
        contactPresenter.requestContactsField();

    }

    @Override
    public void onStop() {

        contactPresenter.detachView();
        super.onStop();
    }
}