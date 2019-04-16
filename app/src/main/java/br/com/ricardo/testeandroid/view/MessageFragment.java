package br.com.ricardo.testeandroid.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.ricardo.testeandroid.R;


public class MessageFragment extends Fragment {

    private TextView textMessageTitle;
    private TextView textMessageSubtitle;
    private TextView textMessageLink;

    private Typeface font;

    private FragmentManager fragmentManagerMessage;
    private FragmentTransaction fragmentTransactionMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View messageView = inflater.inflate(R.layout.fragment_message, container, false);

        textMessageTitle = (TextView) messageView.findViewById(R.id.message_title);
        textMessageSubtitle = (TextView) messageView.findViewById(R.id.message_subtitle);
        textMessageLink = (TextView) messageView.findViewById(R.id.message_link);

        font = Typeface.createFromAsset(getActivity().getAssets(), "font/DINPro-Medium.otf");
        textMessageTitle.setTypeface(font);
        textMessageSubtitle.setTypeface(font);
        textMessageLink.setTypeface(font);

        textMessageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentManagerMessage = getActivity().getSupportFragmentManager();
                fragmentTransactionMessage = fragmentManagerMessage.beginTransaction();
                fragmentTransactionMessage.replace(R.id.main_frame_container, new ContactFragment());
                fragmentTransactionMessage.commit();

            }
        });


        return messageView;

    }
}
