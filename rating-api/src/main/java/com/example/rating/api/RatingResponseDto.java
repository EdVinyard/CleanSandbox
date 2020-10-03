package com.example.rating.api;

import java.time.Instant;
import java.util.UUID;

public class RatingResponseDto {
    public UUID subjectId;
    public float aggregateRating;
    public float count;
    public Instant updated;
}
