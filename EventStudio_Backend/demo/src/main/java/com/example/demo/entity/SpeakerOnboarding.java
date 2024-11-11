package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "speaker_onboardings")
public class SpeakerOnboarding extends BaseEntity{

    private UUID eventUUID;
    private UUID spaceUUID;
    private String fullName;
    private String email;
    private String bio;
    private String eventName;
    private String linkedInURL;
    private String twitterURL;
    private String headshotURL;
    private String title;
    private String organization;


}