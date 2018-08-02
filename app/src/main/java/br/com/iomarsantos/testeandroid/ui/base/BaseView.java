package br.com.iomarsantos.testeandroid.ui.base;

import android.support.annotation.StringRes;

/**
 * Interface base que ser estendida por outra interface mais especifica que sera implementada por uma Activity ou Fragment
 * todas as classes que vao atuar como View no padrão MVP, devem implementar uma interface especifica.
 */
public interface BaseView {
    void setup();
    void setTitleActivity(@StringRes int resId);
    void setTitleActivity(String titulo);
}
