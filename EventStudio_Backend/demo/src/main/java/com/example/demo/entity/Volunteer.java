package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "volunteers")
public class Volunteer extends BaseEntity{



    private UUID eventUUID;
    private UUID spaceUUID;
    private String fullName;
    private String email;
    private String mobilePhone;

    @ElementCollection
    private List<String> communicationTools = new ArrayList<>();

    private String telegramID;
    private String linkedInURL;

    @ElementCollection
    private List<String> areasOfSupport = new ArrayList<>();

    private boolean businessAttire = false;


}
