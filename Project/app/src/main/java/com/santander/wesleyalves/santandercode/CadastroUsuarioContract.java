package com.santander.wesleyalves.santandercode;

public interface CadastroUsuarioContract {

    interface View {
        void exibirTelaSucesso();

        void definirListeners();

        void definirObjetosLayout();

        void definirFontes();
    }

    interface Presenter {
        boolean salvarUsuario(String nomeCompleto, String email, String telefone, boolean cadastrarEmail);
    }
}
