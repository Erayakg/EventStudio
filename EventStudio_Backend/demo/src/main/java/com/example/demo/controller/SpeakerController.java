package com.example.demo.controller;

import com.example.demo.common.RestResponse;
import com.example.demo.dto.SpeakerDtoReq;
import com.example.demo.dto.SpeakerDtoRes;
import com.example.demo.serivce.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/speakers")
public class SpeakerController {

    private final SpeakerService speakerService;

    @Autowired
    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestResponse<SpeakerDtoRes>> addSpeaker(@RequestBody SpeakerDtoReq speakerDtoReq) {
        SpeakerDtoRes response = speakerService.addSpeaker(speakerDtoReq);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @GetMapping("/names/{spaceId}")
    public ResponseEntity<RestResponse<List<String>>> getAllSpeakers(@PathVariable Long spaceId) {
        List<String> response = speakerService.getAllSpeakers(spaceId);
        return ResponseEntity.ok(RestResponse.of(response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<SpeakerDtoRes>> updateSpeaker(@PathVariable Long id, @RequestBody SpeakerDtoReq speakerDtoReq) {
        SpeakerDtoRes response = speakerService.updateSpeaker(id, speakerDtoReq);
        return ResponseEntity.ok(RestResponse.of(response));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteSpeaker(@PathVariable Long id) {
        speakerService.deleteSpeaker(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<RestResponse<List<SpeakerDtoRes>>> getAllSpeakersEventDetail(@PathVariable Long eventId) {
        List<SpeakerDtoRes> response = speakerService.getAllSpeakersByEvent(eventId);
        return ResponseEntity.ok(RestResponse.of(response));
    }
}
