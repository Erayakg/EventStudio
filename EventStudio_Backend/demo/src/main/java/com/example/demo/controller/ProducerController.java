package com.example.demo.controller;

import com.example.demo.common.RestResponse;
import com.example.demo.dto.ProducerDtoReq;
import com.example.demo.dto.ProducerDtoRes;
import com.example.demo.serivce.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestResponse<ProducerDtoRes>> addProducer(@RequestBody ProducerDtoReq producerDtoReq) {
        ProducerDtoRes response = producerService.addProducer(producerDtoReq);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @GetMapping("/names/{spaceId}")
    public ResponseEntity<RestResponse<List<String>>> getAllProducerNames(@PathVariable Long spaceId) {
        List<String> response = producerService.getAllProducerNames(spaceId);
        return ResponseEntity.ok(RestResponse.of(response));
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteProducer(@PathVariable Long id) {
        producerService.deleteProducer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<ProducerDtoRes>> updateProducer(@PathVariable Long id, @RequestBody ProducerDtoReq producerDtoReq) {
        ProducerDtoRes response = producerService.updateProducer(id, producerDtoReq);
        return ResponseEntity.ok(RestResponse.of(response));
    }
}
