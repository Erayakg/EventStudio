package com.example.demo.serivce;

import com.example.demo.dto.EventDtoReq;
import com.example.demo.dto.EventDtoRes;

import java.util.List;

public interface EventsService {

    EventDtoRes addEvent(EventDtoReq eventDtoReq);

    EventDtoRes getBySpaceId(Long spaceId);

    void deleteByEventId(Long eventId);

    List<EventDtoRes> getBySpaceIdWhenIsActiveTrue(Long spaceId);

    EventDtoRes updateEvent(Long eventId, EventDtoReq eventDtoReq);

}
