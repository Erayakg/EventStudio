package com.example.demo.repository;

import com.example.demo.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    List<Producer> findAllBySpaceId(Long spaceId);

}
