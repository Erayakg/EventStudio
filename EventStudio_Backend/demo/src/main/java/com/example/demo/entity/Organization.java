package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "organizations")
public class Organization extends BaseEntity{


    private String name;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

}