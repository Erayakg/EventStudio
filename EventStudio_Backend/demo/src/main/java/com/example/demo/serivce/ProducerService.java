package com.example.demo.serivce;

import com.example.demo.dto.ProducerDtoReq;
import com.example.demo.dto.ProducerDtoRes;

import java.util.List;

public interface ProducerService {
    ProducerDtoRes addProducer(ProducerDtoReq producerDtoReq);

    ProducerDtoRes updateProducer(Long id, ProducerDtoReq producerDtoReq);

    void deleteProducer(Long id);

    List<String> getAllProducerNames(Long spaceId);
}
