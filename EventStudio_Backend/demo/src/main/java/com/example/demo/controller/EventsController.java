package com.example.demo.controller;

import com.example.demo.common.RestResponse;
import com.example.demo.dto.EventDtoReq;
import com.example.demo.dto.EventDtoRes;
import com.example.demo.serivce.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestResponse<EventDtoRes>> addEvent(@RequestBody EventDtoReq eventDtoReq) {
        EventDtoRes eventDtoRes = eventsService.addEvent(eventDtoReq);
        return  new ResponseEntity<>(RestResponse.of(eventDtoRes), HttpStatus.OK);
    }
    @GetMapping("/get/{spaceId}")
    public ResponseEntity<RestResponse<EventDtoRes>> getEvent(@PathVariable Long spaceId) {
        EventDtoRes bySpaceId = eventsService.getBySpaceId(spaceId);
        return  new ResponseEntity<>(RestResponse.of(bySpaceId), HttpStatus.OK);
    }
    @GetMapping("/events/active/{spaceId}")
    public ResponseEntity<RestResponse<List<EventDtoRes>>> getActiveEvent(@PathVariable Long spaceId) {
        List<EventDtoRes> bySpaceId = eventsService.getBySpaceIdWhenIsActiveTrue(spaceId);
        return  new ResponseEntity<>(RestResponse.of(bySpaceId), HttpStatus.OK);
    }
    @DeleteMapping("/events/delete")
    public ResponseEntity<RestResponse<EventDtoRes>> deleteEvent(Long eventsId) {
        eventsService.deleteByEventId(eventsId);
        return new ResponseEntity<>(RestResponse.empty(), HttpStatus.OK);
    }
    @PutMapping("/events/update/{id}")
    public ResponseEntity<RestResponse<EventDtoRes>> updateEvent(@PathVariable Long id, @RequestBody EventDtoReq eventDtoReq) {
        eventsService.updateEvent(id, eventDtoReq);
        return new ResponseEntity<>(RestResponse.empty(), HttpStatus.OK);
    }
}
