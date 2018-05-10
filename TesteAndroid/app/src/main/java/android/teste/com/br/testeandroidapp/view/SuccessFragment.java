package android.teste.com.br.testeandroidapp.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.teste.com.br.testeandroidapp.R;
import android.widget.Button;

public class SuccessFragment extends Fragment {

    private NovaMensagemCallback novaMensagemCallback;

    public SuccessFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SuccessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuccessFragment newInstance(NovaMensagemCallback novaMensagemCallback) {
        SuccessFragment successFragment = new SuccessFragment();
        successFragment.setNovaMensagemCallback(novaMensagemCallback);
        return successFragment;
    }

    private void setNovaMensagemCallback(NovaMensagemCallback novaMensagemCallback) {
        this.novaMensagemCallback = novaMensagemCallback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_success, container, false);

        Button btnNovaMensagem = (Button) view.findViewById(R.id.btn_nova_mensagem);
        btnNovaMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novaMensagemCallback.sendNewMessage();
            }
        });

        return view;
    }

    public interface NovaMensagemCallback {
        void sendNewMessage();
    }
}
