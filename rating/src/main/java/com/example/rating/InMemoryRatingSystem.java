package com.example.rating;

import com.example.rating.api.RatingCreationDto;
import com.example.rating.api.RatingResponseDto;
import com.example.rating.api.RatingSystem;
import java.util.UUID;

public class InMemoryRatingSystem implements RatingSystem {

    public RatingResponseDto averageRating(UUID ratingSubject) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    public RatingResponseDto rate(RatingCreationDto rating) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
