package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "speakers")
public class Speaker extends BaseEntity {



    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String primaryAffiliation;
    private String title;
    private String headshot;
    private String linkedInURL;
    private String twitterURL;
    private String bio;
    private String adminFullName;
    private String adminEmailAddress;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;


}

