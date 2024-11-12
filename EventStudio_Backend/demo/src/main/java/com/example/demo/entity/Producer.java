package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "producers")
@Data
public class Producer extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

}

