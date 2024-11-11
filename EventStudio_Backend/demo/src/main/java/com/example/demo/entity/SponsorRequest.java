package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "sponsor_requests")
public class SponsorRequest extends BaseEntity {


    private UUID eventUUID;
    private UUID spaceUUID;
    private String name;
    private String email;
    private String involvement;
    private String linkedIn;

}

