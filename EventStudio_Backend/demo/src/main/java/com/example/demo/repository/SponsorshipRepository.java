package com.example.demo.repository;

import com.example.demo.entity.Sponsorship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SponsorshipRepository extends JpaRepository<Sponsorship, Long> {
    List<Sponsorship> findAllBySpaceId(Long spaceId);
    List<Sponsorship> findAllByEventId(Long eventId);
}
