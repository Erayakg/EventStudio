package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;
@Entity
@Table(name = "speaking_requests")
public class SpeakingRequest extends BaseEntity {



    private UUID eventUUID;
    private UUID spaceUUID;
    private String fullName;
    private String email;
    private String organization;
    private String eventName;
    private String title;
    private boolean panelists;
    private String abstractText;
    private String linkedInURL;
    private String sponsorshipInterest;

}
