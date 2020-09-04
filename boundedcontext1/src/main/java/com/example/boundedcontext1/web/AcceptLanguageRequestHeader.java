package com.example.boundedcontext1.web;

import com.sun.net.httpserver.Headers;
import java.util.Locale;

/**
 * the content of the HTTP Accept-Language request header
 */
public class AcceptLanguageRequestHeader {
    private final Locale preferredLanguage;

    public AcceptLanguageRequestHeader(final Headers headers) {
        preferredLanguage = Locale.forLanguageTag(headers
                .get("Accept-Language")
                .get(0)
                .split(",")[0]
                .split(";")[0]);
    }

    public Locale preferredLanguage() {
        return preferredLanguage;
    }
}
