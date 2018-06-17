package lzacheu.com.br.santanderinvestimento.data.remote.contact;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lzacheu.com.br.santanderinvestimento.model.contact.InputField;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class ContactResponse {

    @SerializedName("cells")
    private List<InputField> inputFields;

    public List<InputField> getInputFields() {
        return inputFields;
    }

    public void setInputFields(List<InputField> inputFields) {
        this.inputFields = inputFields;
    }
}
