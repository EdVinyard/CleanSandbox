package com.example.rating.api;

import java.time.Instant;
import java.util.UUID;

public class RatingCreationDto {
    public UUID subjectId;
    public UUID raterId;
    public float rating;
    public Instant created;
}
