package com.example.demo.dto;

import com.example.demo.entity.Organization;
import com.example.demo.entity.Space;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Organization}
 */
@Value
public class OrganizationDtoReq implements Serializable {
    String name;
    SpaceDto space;

    /**
     * DTO for {@link Space}
     */
    @Value
    public static class SpaceDto implements Serializable {
        Long id;
        String spaceName;
    }
}