package com.example.boundedcontext1.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * a collection of prose greetings, in the same or similar languages to a
 * preferred language (e.g., a language known to a user)
 */
public class Greetings implements Iterable<Greeting> { // a value type
    private final Locale preferredLanguage;
    private final List<Greeting> greetings;

    /**
     * all the greetings relevant to a reader/speaker of the preferredLanguage
     */
    public Greetings(
            final Locale preferredLanguage,
            final Collection<Greeting> greetings) {

        if (null == preferredLanguage) {
            throw new IllegalArgumentException(
                "preferredLanguage must not be null");
        }

        if (null == greetings) {
            throw new IllegalArgumentException(
                "greetings must not be null");
        }
        
        this.preferredLanguage = preferredLanguage;
        this.greetings = Collections.unmodifiableList(
            new ArrayList<Greeting>(greetings));
    }

    /**
     * a preferred read/spoken language, to which all greetings are (probably) 
     * relevant
     */
    public Locale preferredLanguage() {
        return this.preferredLanguage;
    }

    @Override
    public Iterator<Greeting> iterator() {
        return greetings.iterator();
    }

    @Override 
    public String toString() {
        final var sb = new StringBuilder()
                .append('[')
                .append("prefer ")
                .append(preferredLanguage)
                .append("; ");
        
        var isFirstGreeting = true;
        for (var g : greetings) {
            if (!isFirstGreeting) {
                sb.append(", ");
            }
            
            sb.append(g);
            isFirstGreeting = false;
        }

        return sb.append(']').toString();
    }

    /** lazily computed by the hashCode() method */
    private int hashCode;

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = preferredLanguage.hashCode();
            for (var g : greetings) {
                result = 31 * result + g.hashCode();
            }

            hashCode = result;
        }

        return result;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }

        if (!(otherObj instanceof Greetings)) {
            return false;
        }

        final var other = (Greetings)otherObj;
        if (!preferredLanguage.equals(other.preferredLanguage)) {
            return false;
        }

        if (this.greetings.size() != other.greetings.size()) {
            return false;
        }

        for (int i = 0; i < greetings.size(); i++) {
            if (!this.greetings.get(i).equals(other.greetings.get(i))) {
                return false;
            }
        }

        return true;
    }
}
