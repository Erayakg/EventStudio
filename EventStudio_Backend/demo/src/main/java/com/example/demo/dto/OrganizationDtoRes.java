package com.example.demo.dto;

import com.example.demo.entity.Organization;
import com.example.demo.entity.Space;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Organization}
 */
@Value
public class OrganizationDtoRes implements Serializable {
    Long id;
    LocalDateTime createDate;
    LocalDateTime updateDate;
    Long createdBy;
    Long updatedBy;
    String name;
    SpaceDto space;

    /**
     * DTO for {@link Space}
     */
    @Value
    public static class SpaceDto implements Serializable {
        LocalDateTime createDate;
        Long createdBy;
        String spaceName;
    }
}