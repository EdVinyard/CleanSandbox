package com.example.boundedcontext1.h2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * a Data Transfer Object (DTO) that shuttles data to and from the
 * database.
 */
@Entity
@Table(name = "greeting")
public class GreetingRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String language;

    public String text;
}
