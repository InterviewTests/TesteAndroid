package br.com.accenture.santander.wallacebaldenebre.ui.main.fragments.contact.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.accenture.santander.wallacebaldenebre.R;
import br.com.accenture.santander.wallacebaldenebre.ui.main.fragments.contact.ContactFragment;
import br.com.accenture.santander.wallacebaldenebre.utils.Helper;

public class BaseContactFragment extends Fragment {

    public BaseContactFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_contact, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Helper.loadFragment(new ContactFragment(), false, new Bundle(), getActivity());
    }
}
