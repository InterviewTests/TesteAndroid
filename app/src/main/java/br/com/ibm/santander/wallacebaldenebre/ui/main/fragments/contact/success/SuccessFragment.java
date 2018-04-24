package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.contact.success;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.ibm.santander.wallacebaldenebre.R;

public class SuccessFragment extends Fragment {
    Button btnSuccess;

    public SuccessFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_success, container, false);

        btnSuccess = view.findViewById(R.id.btn_success_newmessage);
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }
}
