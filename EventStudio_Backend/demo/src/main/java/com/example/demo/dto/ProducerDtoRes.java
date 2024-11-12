package com.example.demo.dto;

import lombok.Value;

@Value
public class ProducerDtoRes {
    Long id;
    String name;
    SpaceDto space;

    @Value
    public static class SpaceDto {
        Long id;
        String spaceName;
    }
}
