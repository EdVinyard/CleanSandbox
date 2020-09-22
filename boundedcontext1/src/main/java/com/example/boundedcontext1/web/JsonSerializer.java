package com.example.boundedcontext1.web;

import com.example.boundedcontext1.web.dto.GreetingDto;
import java.io.InputStream;

public interface JsonSerializer {
    String toJson(Object o);

    GreetingDto fromJson(InputStream requestBody);
}
