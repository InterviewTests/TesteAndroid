package br.com.iomarsantos.testeandroid.ui.base.exception;

public class ViewNotAttachedException extends RuntimeException {
    public ViewNotAttachedException() {
        super("Chamar o Presenter.onAttach(BaseView) antes do Presenter");
    }
}
