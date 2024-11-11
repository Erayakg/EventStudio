package com.example.demo.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "space_users")
public class SpaceUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    private boolean isAdmin = false;


}
