package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class EventDtoReq {

    private UUID uuid;
    private String name;
    private String shortname;
    private String landingUrl;
    private String startdate;
    private String enddate;
    private Long venueId;
    private Long userId;
    private boolean isActive = true;
    private Long spaceId;
    private String sponsorshipDeckUrl;
    private String theme;



}
