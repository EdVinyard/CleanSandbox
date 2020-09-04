package com.example.boundedcontext1.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonJsonSerializer implements JsonSerializer {
    private Gson gson;

    public GsonJsonSerializer() {
        this.gson = new GsonBuilder()
                //.registerTypeAdapter(Id.class, new IdTypeAdapter())
                .serializeNulls()
                .setPrettyPrinting()
                .create();        
    }

    @Override
    public String toJson(Object o) {
        return gson.toJson(o);
    }
}
