package com.example.demo.serivce.impl;

import com.example.demo.dto.SpeakerDtoReq;
import com.example.demo.dto.SpeakerDtoRes;
import com.example.demo.entity.Event;
import com.example.demo.entity.Space;
import com.example.demo.entity.Speaker;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.repository.SpeakerRepository;
import com.example.demo.serivce.SpeakerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    private final SpeakerRepository speakerRepository;
    private final EventRepository eventRepository;
    private final SpaceRepository spaceRepository;

    @Autowired
    public SpeakerServiceImpl(SpeakerRepository speakerRepository, EventRepository eventRepository, SpaceRepository spaceRepository) {
        this.speakerRepository = speakerRepository;
        this.eventRepository = eventRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public SpeakerDtoRes addSpeaker(SpeakerDtoReq speakerDtoReq) {
        Event event = eventRepository.findById(speakerDtoReq.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Space space = spaceRepository.findById(speakerDtoReq.getSpaceId())
                .orElseThrow(() -> new EntityNotFoundException("Space not found"));

        Speaker speaker = new Speaker();
        speaker.setEvent(event);
        speaker.setSpace(space);
        speaker.setFirstName(speakerDtoReq.getFirstName());
        speaker.setLastName(speakerDtoReq.getLastName());
        speaker.setEmailAddress(speakerDtoReq.getEmailAddress());
        speaker.setPrimaryAffiliation(speakerDtoReq.getPrimaryAffiliation());
        speaker.setTitle(speakerDtoReq.getTitle());
        speaker.setHeadshot(speakerDtoReq.getHeadshot());
        speaker.setLinkedInURL(speakerDtoReq.getLinkedInURL());
        speaker.setTwitterURL(speakerDtoReq.getTwitterURL());
        speaker.setBio(speakerDtoReq.getBio());
        speaker.setAdminFullName(speakerDtoReq.getAdminFullName());
        speaker.setAdminEmailAddress(speakerDtoReq.getAdminEmailAddress());

        Speaker savedSpeaker = speakerRepository.save(speaker);

        return mapToDto(savedSpeaker);
    }

    @Override
    public SpeakerDtoRes updateSpeaker(Long id, SpeakerDtoReq speakerDtoReq) {
        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speaker not found"));

        Event event = eventRepository.findById(speakerDtoReq.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));
        Space space = spaceRepository.findById(speakerDtoReq.getSpaceId())
                .orElseThrow(() -> new EntityNotFoundException("Space not found"));

        speaker.setEvent(event);
        speaker.setSpace(space);
        speaker.setFirstName(speakerDtoReq.getFirstName());
        speaker.setLastName(speakerDtoReq.getLastName());
        speaker.setEmailAddress(speakerDtoReq.getEmailAddress());
        speaker.setPrimaryAffiliation(speakerDtoReq.getPrimaryAffiliation());
        speaker.setTitle(speakerDtoReq.getTitle());
        speaker.setHeadshot(speakerDtoReq.getHeadshot());
        speaker.setLinkedInURL(speakerDtoReq.getLinkedInURL());
        speaker.setTwitterURL(speakerDtoReq.getTwitterURL());
        speaker.setBio(speakerDtoReq.getBio());
        speaker.setAdminFullName(speakerDtoReq.getAdminFullName());
        speaker.setAdminEmailAddress(speakerDtoReq.getAdminEmailAddress());

        Speaker updatedSpeaker = speakerRepository.save(speaker);

        return mapToDto(updatedSpeaker);
    }

    @Override
    public void deleteSpeaker(Long id) {
        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speaker not found"));
        speakerRepository.delete(speaker);
    }

    @Override
    public List<String> getAllSpeakers(Long spaceId) {
        return speakerRepository.findAllBySpaceId(spaceId).stream()
                .map(speaker -> speaker.getFirstName() + " " + speaker.getLastName())
                .collect(Collectors.toList());
    }

    @Override
    public List<SpeakerDtoRes> getAllSpeakersByEvent(Long eventId) {
        return speakerRepository.findAllByEventId(eventId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SpeakerDtoRes mapToDto(Speaker speaker) {
        SpeakerDtoRes.EventDto eventDto = new SpeakerDtoRes.EventDto(
                speaker.getEvent().getId(),
                speaker.getEvent().getName(),
                speaker.getEvent().getShortname()
        );

        SpeakerDtoRes.SpaceDto spaceDto = new SpeakerDtoRes.SpaceDto(
                speaker.getSpace().getId(),
                speaker.getSpace().getSpaceName()
        );

        return new SpeakerDtoRes(
                speaker.getId(),
                speaker.getFirstName(),
                speaker.getLastName(),
                speaker.getEmailAddress(),
                speaker.getPrimaryAffiliation(),
                speaker.getTitle(),
                speaker.getHeadshot(),
                speaker.getLinkedInURL(),
                speaker.getTwitterURL(),
                speaker.getBio(),
                speaker.getAdminFullName(),
                speaker.getAdminEmailAddress(),
                eventDto,
                spaceDto
        );
    }
}
