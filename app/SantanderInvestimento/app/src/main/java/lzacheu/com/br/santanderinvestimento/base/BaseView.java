package lzacheu.com.br.santanderinvestimento.base;

/**
 * Created by luiszacheu on 6/16/18.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
