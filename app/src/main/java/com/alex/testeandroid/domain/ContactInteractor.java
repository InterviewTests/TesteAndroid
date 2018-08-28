package com.alex.testeandroid.domain;

import com.alex.testeandroid.data.entities.Cell;
import com.alex.testeandroid.data.remote.ServiceGenerator;
import com.alex.testeandroid.data.remote.services.ContactService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by alex on 27/08/18.
 */
public class ContactInteractor extends BaseInteractor {

    //region METHODS
    //region PUBLIC METHODS
    public Single<List<Cell>> getContactForm() {
        ContactService service = ServiceGenerator.createService(ContactService.class, "https://floating-mountain-50292.herokuapp.com/");
        return service.getContactForm()
                .map(new Function<JsonObject, List<Cell>>() {
                    @Override
                    public List<Cell> apply(JsonObject jsonObject) throws Exception {
                        Gson gson = new GsonBuilder()
                                .registerTypeAdapter(com.alex.testeandroid.data.entities.Type.class, new JsonDeserializer<com.alex.testeandroid.data.entities.Type>() {
                                    @Override
                                    public com.alex.testeandroid.data.entities.Type deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                        return new com.alex.testeandroid.data.entities.Type(json.getAsInt());
                                    }
                                })
                                .registerTypeAdapter(com.alex.testeandroid.data.entities.TypeField.class, new JsonDeserializer<com.alex.testeandroid.data.entities.TypeField>() {
                                    @Override
                                    public com.alex.testeandroid.data.entities.TypeField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                        if (json.isJsonNull()) return null;
                                        if (json.getAsJsonPrimitive().isString()) {
                                            return new com.alex.testeandroid.data.entities.TypeField(json.getAsString());
                                        } else {
                                            return new com.alex.testeandroid.data.entities.TypeField(json.getAsInt());
                                        }
                                    }
                                })
                                .create();

                        List<Cell> cells = gson.fromJson(jsonObject.get("cells"), new TypeToken<ArrayList<Cell>>() {
                        }.getType());
                        return cells;
                    }
                });
    }
    //endregion
    //endregion
}
