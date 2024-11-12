package com.example.demo.serivce.impl;

import com.example.demo.dto.SponsorshipDtoReq;
import com.example.demo.dto.SponsorshipDtoRes;
import com.example.demo.entity.Event;
import com.example.demo.entity.Organization;
import com.example.demo.entity.Space;
import com.example.demo.entity.Sponsorship;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.repository.SponsorshipRepository;
import com.example.demo.serivce.SponsorshipService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SponsorshipServiceImpl implements SponsorshipService {

    private final SponsorshipRepository sponsorshipRepository;
    private final OrganizationRepository organizationRepository;
    private final EventRepository eventRepository;
    private final SpaceRepository spaceRepository;

    @Autowired
    public SponsorshipServiceImpl(SponsorshipRepository sponsorshipRepository,
                                  OrganizationRepository organizationRepository,
                                  EventRepository eventRepository,
                                  SpaceRepository spaceRepository) {
        this.sponsorshipRepository = sponsorshipRepository;
        this.organizationRepository = organizationRepository;
        this.eventRepository = eventRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public SponsorshipDtoRes addSponsorship(SponsorshipDtoReq sponsorshipDtoReq) {
        Organization organization = organizationRepository.findById(sponsorshipDtoReq.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        Event event = eventRepository.findById(sponsorshipDtoReq.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Space space = spaceRepository.findById(sponsorshipDtoReq.getSpaceId())
                .orElseThrow(() -> new EntityNotFoundException("Space not found"));

        Sponsorship sponsorship = new Sponsorship();
        sponsorship.setOrganization(organization);
        sponsorship.setEvent(event);
        sponsorship.setSpace(space);
        sponsorship.setContactPerson(sponsorshipDtoReq.getContactPerson());
        sponsorship.setContactEmailAddress(sponsorshipDtoReq.getContactEmailAddress());
        sponsorship.setInvoicePerson(sponsorshipDtoReq.getInvoicePerson());
        sponsorship.setInvoiceEmailAddress(sponsorshipDtoReq.getInvoiceEmailAddress());
        sponsorship.setDeckSent(sponsorshipDtoReq.isDeckSent());
        sponsorship.setContractSent(sponsorshipDtoReq.isContractSent());
        sponsorship.setInvoiceSent(sponsorshipDtoReq.isInvoiceSent());
        sponsorship.setPaymentReceived(sponsorshipDtoReq.isPaymentReceived());
        sponsorship.setCommitmentAmount(sponsorshipDtoReq.getCommitmentAmount());

        Sponsorship savedSponsorship = sponsorshipRepository.save(sponsorship);

        return mapToDto(savedSponsorship);
    }

    @Override
    public SponsorshipDtoRes updateSponsorship(Long id, SponsorshipDtoReq sponsorshipDtoReq) {
        Sponsorship sponsorship = sponsorshipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sponsorship not found"));

        sponsorship.setContactPerson(sponsorshipDtoReq.getContactPerson());
        sponsorship.setContactEmailAddress(sponsorshipDtoReq.getContactEmailAddress());
        sponsorship.setInvoicePerson(sponsorshipDtoReq.getInvoicePerson());
        sponsorship.setInvoiceEmailAddress(sponsorshipDtoReq.getInvoiceEmailAddress());
        sponsorship.setDeckSent(sponsorshipDtoReq.isDeckSent());
        sponsorship.setContractSent(sponsorshipDtoReq.isContractSent());
        sponsorship.setInvoiceSent(sponsorshipDtoReq.isInvoiceSent());
        sponsorship.setPaymentReceived(sponsorshipDtoReq.isPaymentReceived());
        sponsorship.setCommitmentAmount(sponsorshipDtoReq.getCommitmentAmount());

        Sponsorship updatedSponsorship = sponsorshipRepository.save(sponsorship);

        return mapToDto(updatedSponsorship);
    }

    @Override
    public SponsorshipDtoRes getSponsorship(Long id) {
        Sponsorship sponsorship = sponsorshipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sponsorship not found"));
        return mapToDto(sponsorship);
    }

    @Override
    public void deleteSponsorship(Long id) {
        Sponsorship sponsorship = sponsorshipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sponsorship not found"));
        sponsorshipRepository.delete(sponsorship);
    }

    @Override
    public List<SponsorshipDtoRes> getAllSponsorships(Long spaceId) {
        return sponsorshipRepository.findAllBySpaceId(spaceId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SponsorshipDtoRes> getAllSponsorshipsEventDetail(Long eventId) {
        return sponsorshipRepository.findAllByEventId(eventId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SponsorshipDtoRes mapToDto(Sponsorship sponsorship) {
        SponsorshipDtoRes.OrganizationDto organizationDto = new SponsorshipDtoRes.OrganizationDto(
                sponsorship.getOrganization().getId(),
                sponsorship.getOrganization().getName()
        );

        SponsorshipDtoRes.EventDto eventDto = new SponsorshipDtoRes.EventDto(
                sponsorship.getEvent().getId(),
                sponsorship.getEvent().getName(),
                sponsorship.getEvent().getStartdate(),
                sponsorship.getEvent().getEnddate()
        );

        SponsorshipDtoRes.SpaceDto spaceDto = new SponsorshipDtoRes.SpaceDto(
                sponsorship.getSpace().getId(),
                sponsorship.getSpace().getSpaceName()
        );

        return new SponsorshipDtoRes(
                sponsorship.getId(),
                organizationDto,
                eventDto,
                sponsorship.getContactPerson(),
                sponsorship.getContactEmailAddress(),
                sponsorship.getInvoicePerson(),
                sponsorship.getInvoiceEmailAddress(),
                sponsorship.isDeckSent(),
                sponsorship.isContractSent(),
                sponsorship.isInvoiceSent(),
                sponsorship.isPaymentReceived(),
                sponsorship.getCommitmentAmount(),
                spaceDto,
                sponsorship.getCreateDate(),
                sponsorship.getUpdateDate()
        );
    }
}

