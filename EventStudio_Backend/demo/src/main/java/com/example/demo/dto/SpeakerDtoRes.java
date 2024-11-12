package com.example.demo.dto;

import lombok.Value;

@Value
public class SpeakerDtoRes {
    Long id;
    String firstName;
    String lastName;
    String emailAddress;
    String primaryAffiliation;
    String title;
    String headshot;
    String linkedInURL;
    String twitterURL;
    String bio;
    String adminFullName;
    String adminEmailAddress;
    EventDto event;
    SpaceDto space;

    @Value
    public static class EventDto {
        Long id;
        String name;
        String shortname;
    }

    @Value
    public static class SpaceDto {
        Long id;
        String spaceName;
    }
}
