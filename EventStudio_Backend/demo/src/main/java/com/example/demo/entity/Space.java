package com.example.demo.entity;

import lombok.Data;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "space")
public class Space extends BaseEntity {

    private UUID uuid;

    private String spaceName = "Default Space";

}