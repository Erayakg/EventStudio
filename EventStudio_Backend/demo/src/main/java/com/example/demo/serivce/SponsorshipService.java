package com.example.demo.serivce;

import com.example.demo.dto.SponsorshipDtoReq;
import com.example.demo.dto.SponsorshipDtoRes;

import java.util.List;

public interface SponsorshipService {
    SponsorshipDtoRes addSponsorship(SponsorshipDtoReq sponsorshipDtoReq);
    SponsorshipDtoRes updateSponsorship(Long id, SponsorshipDtoReq sponsorshipDtoReq);
    SponsorshipDtoRes getSponsorship(Long id);
    void deleteSponsorship(Long id);
    List<SponsorshipDtoRes> getAllSponsorships(Long spaceId);
    List<SponsorshipDtoRes> getAllSponsorshipsEventDetail(Long eventId);

}
