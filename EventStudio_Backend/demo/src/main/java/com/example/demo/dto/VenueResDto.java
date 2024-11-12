package com.example.demo.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.demo.entity.Venue}
 */
@Value
public class VenueResDto implements Serializable {
    LocalDateTime createDate;
    LocalDateTime updateDate;
    Long createdBy;
    String name;
    SpaceDto space;

    /**
     * DTO for {@link com.example.demo.entity.Space}
     */
    @Value
    public static class SpaceDto implements Serializable {
        LocalDateTime createDate;
        Long createdBy;
        String spaceName;
    }
}