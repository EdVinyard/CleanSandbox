package com.example.boundedcontext1.h2;

import static java.util.stream.Collectors.toList;

import com.example.boundedcontext1.domain.Greeting;
import com.example.boundedcontext1.domain.GreetingRepository;
import com.example.boundedcontext1.domain.Greetings;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.repository.CrudRepository;

public class H2GreetingRepository implements GreetingRepository {
    private TableGateway table;

    public H2GreetingRepository(TableGateway table) {
        this.table = table;
    }

    @Override
    public Greetings getByBaseLanguage(Locale language) {
        var baseLanguage = language.getLanguage();

        return new Greetings(
                language,
                table.findByLanguageStartsWith(baseLanguage)
                        .stream()
                        .map(H2GreetingRepository::toDomain)
                        .collect(toList()));
    }

    @Override
    public void save(Greeting greeting) {
        table.save(fromDomain(greeting));
    }

    private Row fromDomain(Greeting greeting) {
        Row r = new Row();
        r.language = greeting.language().toLanguageTag();
        r.text = greeting.text();
        return r;
    }

    private static Greeting toDomain(Row r) {
        return new Greeting(
                Locale.forLanguageTag(r.language),
                r.text);
    }

    /**
     * a Data Transfer Object (DTO) that shuttles data to and from the
     * database.
     */
    @Entity
    @Table(name = "greeting")
    public static class Row { // TODO: not public
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;

        public String language;

        public String text;
    }

    /**
     * an automatically generated JPA repository; the surrounding class is
     * an adapter/orchestrator that conforms the JPA-generated code to the
     * expectations of com.example.boundedcontext1.domain
     */
    public interface TableGateway extends CrudRepository<Row, UUID> { // TODO: not public!
        List<Row> findByLanguageStartsWith(String languagePrefix);
    }
}
