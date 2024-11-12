package com.example.demo.dto;

import lombok.Value;

@Value
public class SpeakerDtoReq {
    Long eventId;
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
    Long spaceId;
}
