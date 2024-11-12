package com.example.demo.controller;

import com.example.demo.common.RestResponse;
import com.example.demo.dto.SponsorshipDtoReq;
import com.example.demo.dto.SponsorshipDtoRes;
import com.example.demo.serivce.SponsorshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sponsorships")
public class SponsorshipController {

    private final SponsorshipService sponsorshipService;

    @Autowired
    public SponsorshipController(SponsorshipService sponsorshipService) {
        this.sponsorshipService = sponsorshipService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestResponse<SponsorshipDtoRes>> addSponsorship(@RequestBody SponsorshipDtoReq sponsorshipDtoReq) {
        SponsorshipDtoRes response = sponsorshipService.addSponsorship(sponsorshipDtoReq);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<SponsorshipDtoRes>> updateSponsorship(@PathVariable Long id, @RequestBody SponsorshipDtoReq sponsorshipDtoReq) {
        SponsorshipDtoRes response = sponsorshipService.updateSponsorship(id, sponsorshipDtoReq);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RestResponse<SponsorshipDtoRes>> getSponsorship(@PathVariable Long id) {
        SponsorshipDtoRes response = sponsorshipService.getSponsorship(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<RestResponse<Void>> deleteSponsorship(@PathVariable Long id) {
        sponsorshipService.deleteSponsorship(id);
        return new ResponseEntity<>(RestResponse.empty(),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/{spaceId}")
    public ResponseEntity<RestResponse<List<SponsorshipDtoRes>>> getAllSponsorships(@PathVariable Long spaceId) {
        List<SponsorshipDtoRes> response = sponsorshipService.getAllSponsorships(spaceId);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<RestResponse<List<SponsorshipDtoRes>>> getAllSponsorshipsEventDetail(@PathVariable Long eventId) {
        List<SponsorshipDtoRes> response = sponsorshipService.getAllSponsorshipsEventDetail(eventId);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }
}