package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "events")
public class Event extends BaseEntity {


    private UUID uuid;
    private String name;
    private String shortname;
    private String landingUrl;
    private String startdate;
    private String enddate;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    private String sponsorshipDeckUrl;
    private String theme;


}

