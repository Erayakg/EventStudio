package com.example.demo.serivce;

import com.example.demo.dto.SpeakerDtoReq;
import com.example.demo.dto.SpeakerDtoRes;

import java.util.List;

public interface SpeakerService {
    SpeakerDtoRes addSpeaker(SpeakerDtoReq speakerDtoReq);

    SpeakerDtoRes updateSpeaker(Long id, SpeakerDtoReq speakerDtoReq);

    void deleteSpeaker(Long id);

    List<String> getAllSpeakers(Long spaceId);

    List<SpeakerDtoRes> getAllSpeakersByEvent(Long eventId);
}
