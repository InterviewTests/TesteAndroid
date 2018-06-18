package lzacheu.com.br.santanderinvestimento.model.fund;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class Info {
    private String name;
    private String data;

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
