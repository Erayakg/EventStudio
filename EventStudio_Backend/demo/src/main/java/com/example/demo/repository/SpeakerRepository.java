package com.example.demo.repository;

import com.example.demo.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    List<Speaker> findAllBySpaceId(Long spaceId);
    List<Speaker> findAllByEventId(Long eventId);
}
