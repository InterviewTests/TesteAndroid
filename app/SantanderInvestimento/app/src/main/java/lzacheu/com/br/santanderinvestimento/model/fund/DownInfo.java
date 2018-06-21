package lzacheu.com.br.santanderinvestimento.model.fund;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class DownInfo {
    private String name;
    private String data;

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public DownInfo() {
    }

    public DownInfo(String name, String data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DownInfo{" +
                "name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
