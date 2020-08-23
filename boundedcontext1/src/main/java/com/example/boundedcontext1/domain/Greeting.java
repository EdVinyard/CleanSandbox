package com.example.boundedcontext1.domain;

import static org.apache.logging.log4j.util.Strings.isBlank;

import java.util.Locale;

/**
 * a prose greeting in a specific language; e.g., "Hello!" (American English)
 */
public class Greeting { // a value type
    private final Locale language;
    private final String text;

    /**
     * specify a prose greeting and its language
     * 
     * @param language non-null Locale describing the language of `text`
     * @param text non-whitespace string containing prose in `language`
     */
    public Greeting(
            final Locale language,
            final String text) {

        if (null == language) {
            throw new IllegalArgumentException("language must not be null");
        }

        if (isBlank(text)) {
            throw new IllegalArgumentException(
                "text must not be null, empty, or whitespace");
        }

        this.language = language;
        this.text = text;
    }

    public Locale language() {
        return this.language;
    }

    public String text() {
        return this.text;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.text, language);
    }

    @Override
    public int hashCode() {
        int result = language.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }

        if (!(otherObj instanceof Greeting)) {
            return false;
        }

        final Greeting other = (Greeting)otherObj;
        return language.equals(other.language)
            && text.equals(other.text);
    }
}
