package com.santander.wesleyalves.santandercode.cadastrousuario.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Patterns;

import com.santander.wesleyalves.santandercode._utils.FieldValidator;

public final class Usuario {

    @NonNull
    private final String NomeCompleto;

    @Nullable
    private final String Email;

    @NonNull
    private final String Telefone;

    @Nullable
    private final boolean CadastrarEmail;

    public Usuario(@NonNull String nomeCompleto, String email, @NonNull String telefone, boolean cadastrarEmail) {
        NomeCompleto = nomeCompleto;
        Email = email;
        Telefone = telefone;
        CadastrarEmail = cadastrarEmail;
    }

    public boolean usuarioValido() {
        if (NomeCompleto.trim().length() == 0)
            return false;

        if (Telefone.trim().length() == 0 || (!Patterns.PHONE.matcher(Telefone).matches()))
            return false;

        if (CadastrarEmail && (Email.trim().length() == 0 || (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())))
            return false;

        return true;
    }

    @NonNull
    public String getNomeCompleto() {
        return NomeCompleto;
    }

    @Nullable
    public String getEmail() {
        return Email;
    }

    @NonNull
    public String getTelefone() {
        return Telefone;
    }

    @Nullable
    public boolean isCadastrarEmail() {
        return CadastrarEmail;
    }

}
