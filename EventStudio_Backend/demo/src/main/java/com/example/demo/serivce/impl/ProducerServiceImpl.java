package com.example.demo.serivce.impl;

import com.example.demo.dto.ProducerDtoReq;
import com.example.demo.dto.ProducerDtoRes;
import com.example.demo.entity.Producer;
import com.example.demo.entity.Space;
import com.example.demo.repository.ProducerRepository;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.serivce.ProducerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;
    private final SpaceRepository spaceRepository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository, SpaceRepository spaceRepository) {
        this.producerRepository = producerRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public ProducerDtoRes addProducer(ProducerDtoReq producerDtoReq) {
        Space space = spaceRepository.findById(producerDtoReq.getSpaceId())
                .orElseThrow(() -> new EntityNotFoundException("Space not found"));

        Producer producer = new Producer();
        producer.setName(producerDtoReq.getName());
        producer.setSpace(space);

        Producer savedProducer = producerRepository.save(producer);

        return mapToDto(savedProducer);
    }

    @Override
    public ProducerDtoRes updateProducer(Long id, ProducerDtoReq producerDtoReq) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producer not found"));

        producer.setName(producerDtoReq.getName());
        Space space = spaceRepository.findById(producerDtoReq.getSpaceId())
                .orElseThrow(() -> new EntityNotFoundException("Space not found"));
        producer.setSpace(space);

        Producer updatedProducer = producerRepository.save(producer);

        return mapToDto(updatedProducer);
    }

    @Override
    public void deleteProducer(Long id) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producer not found"));
        producerRepository.delete(producer);
    }

    @Override
    public List<String> getAllProducerNames(Long spaceId) {
        return producerRepository.findAllBySpaceId(spaceId).stream()
                .map(Producer::getName)
                .collect(Collectors.toList());
    }

    private ProducerDtoRes mapToDto(Producer producer) {
        ProducerDtoRes.SpaceDto spaceDto = new ProducerDtoRes.SpaceDto(
                producer.getSpace().getId(),
                producer.getSpace().getSpaceName()
        );

        return new ProducerDtoRes(
                producer.getId(),
                producer.getName(),
                spaceDto
        );
    }
}
