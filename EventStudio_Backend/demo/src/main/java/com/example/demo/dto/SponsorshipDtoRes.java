package com.example.demo.dto;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class SponsorshipDtoRes {
    Long id;
    OrganizationDto organization;
    EventDto event;
    String contactPerson;
    String contactEmailAddress;
    String invoicePerson;
    String invoiceEmailAddress;
    boolean deckSent;
    boolean contractSent;
    boolean invoiceSent;
    boolean paymentReceived;
    BigDecimal commitmentAmount;
    SpaceDto space;
    LocalDateTime createDate;
    LocalDateTime updateDate;

    @Value
    public static class OrganizationDto {
        Long id;
        String name;
    }

    @Value
    public static class EventDto {
        Long id;
        String name;
        String startDate;
        String endDate;
    }

    @Value
    public static class SpaceDto {
        Long id;
        String spaceName;
    }
}

