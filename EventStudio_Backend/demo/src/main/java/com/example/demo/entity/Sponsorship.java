package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
@Data
@Entity
@Table(name = "sponsorships")
public class Sponsorship extends BaseEntity{


    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private String contactPerson;
    private String contactEmailAddress;
    private String invoicePerson;
    private String invoiceEmailAddress;
    private boolean deckSent = false;
    private boolean contractSent = false;
    private boolean invoiceSent = false;
    private boolean paymentReceived = false;
    private BigDecimal commitmentAmount;

    @ManyToOne
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

}
