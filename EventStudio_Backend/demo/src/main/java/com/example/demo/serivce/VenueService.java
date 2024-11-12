package com.example.demo.serivce;

import com.example.demo.dto.VenueResDto;
import com.example.demo.dto.VenueDtoReq;

import java.util.List;

public interface VenueService {

    VenueResDto addVenue(VenueDtoReq venueDtoReq);
    VenueResDto updateVenue(VenueDtoReq venueDtoReq,Long id);
    List<VenueResDto> getAllVenues();
    VenueResDto getVenueById(Long id);
    void deleteVenueById(Long id);
    List<String> getAllVenueNames(Long id);

}
