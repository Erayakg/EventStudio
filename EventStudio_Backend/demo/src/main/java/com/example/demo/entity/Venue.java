package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "venues")
public class Venue extends BaseEntity {


    private String name;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

}

