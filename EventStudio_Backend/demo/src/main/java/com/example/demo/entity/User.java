package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;

}
