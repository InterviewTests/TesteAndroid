package lzacheu.com.br.santanderinvestimento.contact;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lzacheu.com.br.santanderinvestimento.R;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class ContactFragment extends Fragment implements ContactContract.View {

    public static ContactFragment newInstance() {

        Bundle args = new Bundle();

        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contact_fragment, container, false);
        return root;
    }

    @Override
    public void setPresenter(ContactContract.Presenter presenter) {

    }

    @Override
    public void showLoading(boolean isVisible) {

    }

    @Override
    public void showForm() {

    }

    @Override
    public void hideForm() {

    }

    @Override
    public void sendForm() {

    }

    @Override
    public void showSendMessageView() {

    }
}
