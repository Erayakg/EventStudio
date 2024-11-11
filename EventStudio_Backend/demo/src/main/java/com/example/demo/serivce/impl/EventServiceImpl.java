package com.example.demo.serivce.impl;

import com.example.demo.dto.EventDtoReq;
import com.example.demo.dto.EventDtoRes;
import com.example.demo.entity.Event;
import com.example.demo.entity.Space;
import com.example.demo.entity.User;
import com.example.demo.entity.Venue;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VenueRepository;
import com.example.demo.serivce.EventsService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventsService {

    private final EventRepository eventRepository;
    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, SpaceRepository spaceRepository, UserRepository userRepository, VenueRepository venueRepository) {

        this.eventRepository = eventRepository;
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;
        this.venueRepository = venueRepository;

    }

    @Override
    public EventDtoRes addEvent(EventDtoReq eventDtoReq) {

        Event event = new Event();

        Optional<Space> byId = spaceRepository.findById(eventDtoReq.getSpaceId());

        Optional<User> byUser = userRepository.findById(eventDtoReq.getUserId());
        Optional<Venue> byVenue = venueRepository.findById(eventDtoReq.getVenueId());

        if (byId.isPresent()) {
            Space space = byId.get();
            event.setSpace(space);
        } else {
            throw new EntityNotFoundException("Space not found");
        }
        event.setName(eventDtoReq.getName());
        event.setEnddate(eventDtoReq.getEnddate());
        event.setStartdate(eventDtoReq.getStartdate());
        event.setShortname(eventDtoReq.getShortname());
        event.setLandingUrl(eventDtoReq.getLandingUrl());
        event.setSponsorshipDeckUrl(eventDtoReq.getSponsorshipDeckUrl());
        event.setTheme(eventDtoReq.getTheme());

        if (byUser.isPresent()) {
            User user = byUser.get();
            event.setUser(user);
        } else {
            throw new EntityNotFoundException("User not found");
        }
        if (byVenue.isPresent()) {
            Venue venue = byVenue.get();
            event.setVenue(venue);
        } else {
            throw new EntityNotFoundException("Venue not found");
        }

        Event save = eventRepository.save(event);

        return toDto(save);
    }

    @Override
    public EventDtoRes getBySpaceId(Long spaceId) {

        if (spaceRepository.findById(spaceId).isPresent()) {
            return toDto(eventRepository.findBySpaceId(spaceId).get());
        }
        throw new EntityNotFoundException("Space not found");
    }

    @Override
    public void deleteByEventId(Long eventId) {
        if (eventRepository.findById(eventId).isPresent()) {
            eventRepository.deleteById(eventId);
        }
        throw new EntityNotFoundException("Event not found");
    }

    @Override
    public List<EventDtoRes> getBySpaceIdWhenIsActiveTrue(Long spaceId) {
        List<EventDtoRes> eventDtoRes = new ArrayList<>();
        if (spaceRepository.findById(spaceId).isPresent()) {
            List<Event> bySpaceIdAndIsActiveTrue = eventRepository.findBySpaceIdAndIsActiveTrue(spaceId);
            for (Event event : bySpaceIdAndIsActiveTrue) {
                eventDtoRes.add(toDto(event));

            }
        }

        return eventDtoRes;
    }

    @Override
    public EventDtoRes updateEvent(Long eventId, EventDtoReq eventDtoReq) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            // Update fields
            event.setName(eventDtoReq.getName());
            event.setEnddate(eventDtoReq.getEnddate());
            event.setStartdate(eventDtoReq.getStartdate());
            event.setShortname(eventDtoReq.getShortname());
            event.setLandingUrl(eventDtoReq.getLandingUrl());
            event.setSponsorshipDeckUrl(eventDtoReq.getSponsorshipDeckUrl());
            event.setTheme(eventDtoReq.getTheme());

            // Update related entities
            Optional<Space> spaceOpt = spaceRepository.findById(eventDtoReq.getSpaceId());
            Optional<User> userOpt = userRepository.findById(eventDtoReq.getUserId());
            Optional<Venue> venueOpt = venueRepository.findById(eventDtoReq.getVenueId());

            if (spaceOpt.isPresent()) {
                event.setSpace(spaceOpt.get());
            } else {
                throw new EntityNotFoundException("Space not found");
            }

            if (userOpt.isPresent()) {
                event.setUser(userOpt.get());
            } else {
                throw new EntityNotFoundException("User not found");
            }

            if (venueOpt.isPresent()) {
                event.setVenue(venueOpt.get());
            } else {
                throw new EntityNotFoundException("Venue not found");
            }

            Event updatedEvent = eventRepository.save(event);
            return toDto(updatedEvent);

        } else {
            throw new EntityNotFoundException("Event not found");
        }
    }


    private EventDtoRes toDto(Event event) {

        EventDtoRes.VenueDto venueDto = new EventDtoRes.VenueDto(null, null, event.getVenue().getName());

        EventDtoRes.UserDto userDto = new EventDtoRes.UserDto(event.getUser().getId(), event.getUser().getCreateDate(), event.getUser().getName(), event.getUser().getEmail());

        EventDtoRes.SpaceDto spaceDto = new EventDtoRes.SpaceDto(event.getSpace().getCreatedBy(), event.getSpace().getUuid(), event.getSpace().getSpaceName());

        EventDtoRes eventDtoRes = new EventDtoRes(null, event.getName(), event.getShortname(), event.getLandingUrl(), event.getStartdate(), event.getEnddate(), venueDto, userDto, event.isActive(), spaceDto, event.getSponsorshipDeckUrl(), event.getTheme());


        return eventDtoRes;

    }

}
