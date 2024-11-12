package com.example.demo.controller;

import com.example.demo.common.RestResponse;
import com.example.demo.dto.VenueDtoReq;
import com.example.demo.dto.VenueResDto;
import com.example.demo.serivce.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {

    private final VenueService venueService;

    @Autowired
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestResponse<VenueResDto>> addVenue(@RequestBody VenueDtoReq venue) {
        VenueResDto venueResDto = venueService.addVenue(venue);
        return new ResponseEntity<>(RestResponse.of(venueResDto), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<RestResponse<VenueResDto>> updateVenue(@RequestBody VenueDtoReq venue, @PathVariable Long id) {
        VenueResDto venueResDto = venueService.updateVenue(venue, id);
        return new ResponseEntity<>(RestResponse.of(venueResDto), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestResponse<VenueResDto>> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenueById(id);
        return new ResponseEntity<>(RestResponse.empty(), HttpStatus.OK);
    }
    @GetMapping("/getvenues/{id}")
    public ResponseEntity<RestResponse<List<String>>> getVenues(@PathVariable Long id) {
        List<String> allVenueNames = venueService.getAllVenueNames(id);
        return new ResponseEntity<>(RestResponse.of(allVenueNames), HttpStatus.OK);
    }
}