//package com.avanade.santander.util;
//
//import android.arch.persistence.room.TypeConverter;
//
//import com.avanade.santander.fundos.domain.model.Info;
//import com.google.gson.Gson;
//
//import java.util.List;
//
//public class FundosTypeConverters {
//
//
//    Gson gson = new Gson();
//
//    @TypeConverter
//    public static List<SomeObject> stringToSomeObjectList(String data) {
//        if (data == null) {
//            return Collections.emptyList();
//        }
//
//        Type listType = new TypeToken<List<SomeObject>>() {}.getType();
//
//        return gson.fromJson(data, listType);
//    }
//
//    @TypeConverter
//    public static String someObjectListToString(List<SomeObject> someObjects) {
//        return gson.toJson(someObjects);
//    }
