package com.example.rating.api;

import java.util.UUID;

public interface RatingSystem {
    /**
     * Returns the average/mean rating of all ratings for the specified subject.
     */
    public RatingResponseDto averageRating(UUID ratingSubject);

    /**
     * Add a single rating to all the ratings for a specified subject.
     */
    public RatingResponseDto rate(RatingCreationDto rating);
}
