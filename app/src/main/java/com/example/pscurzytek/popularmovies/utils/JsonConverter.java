package com.example.pscurzytek.popularmovies.utils;

import com.example.pscurzytek.popularmovies.models.ObjectWithId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter {
    public static <T> T convertTo(JSONObject jsonObject, Class<T> type) {
        if (jsonObject == null) {
            return null;
        }

        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonObject.toString(), type);
    }

    public static <T> List<T> convertListTo(JSONObject jsonObject, Class<T> type) {
        if (jsonObject == null) {
            return null;
        }

        JSONArray array = jsonObject.optJSONArray("results");
        List<T> objects = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            T obj = convertTo(array.optJSONObject(i), type);
            if (!(obj instanceof ObjectWithId))
                continue;

            String objectId = ((ObjectWithId)obj).getIdAsString();
            if (!objectId.trim().isEmpty()) {
                objects.add(obj);
            }
        }
        return objects;
    }
}
