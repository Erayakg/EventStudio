package com.example.demo.dto;

import com.example.demo.entity.Space;
import com.example.demo.entity.Venue;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Venue}
 */
@Value
public class VenueDtoReq implements Serializable {
    String name;
    SpaceDto space;
    Long id;

    /**
     * DTO for {@link Space}
     */
    @Value
    public static class SpaceDto implements Serializable {
        String spaceName;
        Long spaceId;
    }
}