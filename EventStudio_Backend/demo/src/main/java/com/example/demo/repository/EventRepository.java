package com.example.demo.repository;

import com.example.demo.dto.EventDtoRes;
import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional< Event> findBySpaceId(Long spaceId);

    List<Event> findBySpaceIdAndIsActiveTrue(Long spaceId);

}
