package br.com.ricardo.testeandroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class ContactFragment extends Fragment {

    private TextView textContactTitle;
    private TextInputLayout inputContactName;
    private TextInputLayout inputContactEmail;
    private TextInputLayout inputContactPhone;
    private CheckBox checkContactRegisterEmail;
    private Button buttonContactSend;

    private FragmentManager fragmentManagerContact;
    private FragmentTransaction fragmentTransactionContact;

    private Typeface font;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View contactView = inflater.inflate(R.layout.fragment_contact, container, false);

        textContactTitle = (TextView) contactView.findViewById(R.id.contact_text_title);
        inputContactName = (TextInputLayout) contactView.findViewById(R.id.contact_input_name);
        inputContactEmail = (TextInputLayout) contactView.findViewById(R.id.contact_input_email);
        inputContactPhone = (TextInputLayout) contactView.findViewById(R.id.contact_input_phone);
        checkContactRegisterEmail = (CheckBox) contactView.findViewById(R.id.contact_check_register_email);
        buttonContactSend = (Button) contactView.findViewById(R.id.contact_button_send);

        font = Typeface.createFromAsset(getActivity().getAssets(), "font/DINPro-Medium.otf");
        textContactTitle.setTypeface(font);
        inputContactName.setTypeface(font);
        inputContactEmail.setTypeface(font);
        inputContactPhone.setTypeface(font);
        checkContactRegisterEmail.setTypeface(font);
        buttonContactSend.setTypeface(font);

        buttonContactSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManagerContact = getActivity().getSupportFragmentManager();
                fragmentTransactionContact = fragmentManagerContact.beginTransaction();
                fragmentTransactionContact.replace(R.id.main_frame_container, new MessageFragment());
                fragmentTransactionContact.commit();

            }
        });

        return contactView;

    }
}