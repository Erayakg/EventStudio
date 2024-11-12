package com.example.demo.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class SponsorshipDtoReq {
    Long organizationId;
    Long eventId;
    String contactPerson;
    String contactEmailAddress;
    String invoicePerson;
    String invoiceEmailAddress;
    boolean deckSent;
    boolean contractSent;
    boolean invoiceSent;
    boolean paymentReceived;
    BigDecimal commitmentAmount;
    Long spaceId;
}