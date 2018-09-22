package com.santander.wesleyalves.santandercode;

import com.santander.wesleyalves.santandercode.cadastrousuario.domain.model.Usuario;

public class CadastroUsuarioPresenter implements CadastroUsuarioContract.Presenter {

    public boolean salvarUsuario(String nomeCompleto, String email, String telefone, boolean cadastrarEmail) {
        Usuario usuario = new Usuario(nomeCompleto, email, telefone, cadastrarEmail);
        return true;
    }
}
