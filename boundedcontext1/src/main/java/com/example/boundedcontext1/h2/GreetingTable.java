package com.example.boundedcontext1.h2;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * an automatically generated JPA repository; the surrounding class is
 * an adapter/orchestrator that conforms the JPA-generated code to the
 * expectations of com.example.boundedcontext1.domain
 */
public interface GreetingTable extends CrudRepository<GreetingRow, UUID> {
    List<GreetingRow> findByLanguageStartsWith(String languagePrefix);
}
