package br.com.santander.testeandroid.main;

/**
 * Definition contract between MainActivity and MainPresenter
 */
public interface MainContract {

    String[] getNamesArray();

    void setTitle(String title);

    void showShareButton(int visibility);

}
