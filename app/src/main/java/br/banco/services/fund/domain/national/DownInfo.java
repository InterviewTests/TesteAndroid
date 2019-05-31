package br.banco.services.fund.domain.national;

import java.util.HashMap;
import java.util.Map;

public class DownInfo {

    //JsonProperty("name")
    private String name;
    //JsonProperty("data")
    private Object data;
    //JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    //JsonProperty("name")
    public String getName() {
        return name;
    }

    //JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    //JsonProperty("data")
    public Object getData() {
        return data;
    }

    //JsonProperty("data")
    public void setData(Object data) {
        this.data = data;
    }

    //JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    //JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }



}