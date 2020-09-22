package com.example.boundedcontext1.web;

import com.example.boundedcontext1.web.dto.GreetingDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GsonJsonSerializer implements JsonSerializer {
    private Gson gson;

    public GsonJsonSerializer() {
        this.gson = new GsonBuilder()
                // .registerTypeAdapter(Id.class, new IdTypeAdapter())
                .serializeNulls().setPrettyPrinting().create();
    }

    @Override
    public String toJson(Object o) {
        return gson.toJson(o);
    }

    @Override
    public GreetingDto fromJson(InputStream input) {
        return gson.fromJson(
                new BufferedReader(new InputStreamReader(input)),
                GreetingDto.class);
    }
}
