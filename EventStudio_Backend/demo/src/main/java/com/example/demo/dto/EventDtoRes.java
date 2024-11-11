package com.example.demo.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.example.demo.entity.Event}
 */
@Value
public class EventDtoRes implements Serializable {
    LocalDateTime createDate;
    String name;
    String shortname;
    String landingUrl;
    String startdate;
    String enddate;
    VenueDto venue;
    UserDto user;
    boolean isActive;
    SpaceDto space;
    String sponsorshipDeckUrl;
    String theme;

    /**
     * DTO for {@link com.example.demo.entity.Venue}
     */
    @Value
    public static class VenueDto implements Serializable {
        LocalDateTime createDate;
        LocalDateTime updateDate;
        String name;
    }

    /**
     * DTO for {@link com.example.demo.entity.User}
     */
    @Value
    public static class UserDto implements Serializable {
        Long id;
        LocalDateTime createDate;
        String name;
        String email;
    }

    /**
     * DTO for {@link com.example.demo.entity.Space}
     */
    @Value
    public static class SpaceDto implements Serializable {
        Long createdBy;
        UUID uuid;
        String spaceName;
    }
}