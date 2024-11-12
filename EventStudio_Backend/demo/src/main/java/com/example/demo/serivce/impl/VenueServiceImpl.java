package com.example.demo.serivce.impl;

import com.example.demo.dto.VenueResDto;
import com.example.demo.entity.Space;
import com.example.demo.entity.Venue;
import com.example.demo.dto.VenueDtoReq;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.repository.VenueRepository;
import com.example.demo.serivce.VenueService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final SpaceRepository spaceRepository;


    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository, SpaceRepository spaceRepository) {
        this.venueRepository = venueRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public VenueResDto addVenue(VenueDtoReq venueDtoReq) {

        Optional<Space> space = spaceRepository.findById(venueDtoReq.getSpace().getSpaceId());

        Venue venue = new Venue();

        if (space.isPresent()) {
            venue.setSpace(space.get());
        } else {
            throw new EntityNotFoundException("Space not found");
        }
        venue.setName(venueDtoReq.getName());

        Venue save = venueRepository.save(venue);

        VenueResDto.SpaceDto spaceDto = new VenueResDto.SpaceDto(save.getSpace().getCreateDate(), save.getSpace().getCreatedBy(), save.getSpace().getSpaceName());

        VenueResDto venueResDto = new VenueResDto(save.getCreateDate(), save.getUpdateDate(), save.getCreatedBy(), save.getName(), spaceDto);

        return venueResDto;
    }

    @Override
    public VenueResDto updateVenue(VenueDtoReq venueDtoReq,Long id) {

        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venue not found"));

        venue.setName(venueDtoReq.getName());
        if (venueDtoReq.getSpace() != null) {
            Space space = spaceRepository.findById(venueDtoReq.getSpace().getSpaceId())
                    .orElseThrow(() -> new EntityNotFoundException("Space not found"));
            venue.setSpace(space);
        }

        Venue updatedVenue = venueRepository.save(venue);
        VenueResDto.SpaceDto spaceDto = new VenueResDto.SpaceDto(updatedVenue.getSpace().getCreateDate(), updatedVenue.getSpace().getCreatedBy(), updatedVenue.getSpace().getSpaceName());

        return new VenueResDto(updatedVenue.getCreateDate(), updatedVenue.getUpdateDate(), updatedVenue.getCreatedBy(), updatedVenue.getName(), spaceDto);
    }


    @Override
    public List<VenueResDto> getAllVenues() {
        List<Venue> venues = venueRepository.findAll();

        return venues.stream().map(venue -> {
            VenueResDto.SpaceDto spaceDto = new VenueResDto.SpaceDto(venue.getSpace().getCreateDate(), venue.getSpace().getCreatedBy(), venue.getSpace().getSpaceName());
            return new VenueResDto(venue.getCreateDate(), venue.getUpdateDate(), venue.getCreatedBy(), venue.getName(), spaceDto);
        }).collect(Collectors.toList());
    }

    @Override
    public VenueResDto getVenueById(Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venue not found"));

        VenueResDto.SpaceDto spaceDto = new VenueResDto.SpaceDto(venue.getSpace().getCreateDate(), venue.getSpace().getCreatedBy(), venue.getSpace().getSpaceName());

        return new VenueResDto(venue.getCreateDate(), venue.getUpdateDate(), venue.getCreatedBy(), venue.getName(), spaceDto);
    }

    @Override
    public void deleteVenueById(Long id) {

        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venue not found"));

        venueRepository.deleteById(id);
    }
    @Override
    public List<String> getAllVenueNames(Long spaceId) {
        if (spaceId == null) {
            throw new IllegalArgumentException("spaceId not found.");
        }

        // Find all venues by spaceId and map them to their names
        List<Venue> venues = venueRepository.findAllBySpaceId(spaceId);

        // Extract the names from the venue objects
        return venues.stream()
                .map(Venue::getName)
                .collect(Collectors.toList());
    }
}
