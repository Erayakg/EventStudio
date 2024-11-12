package com.example.demo.repository;

import com.example.demo.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllBySpaceId(Long spaceId);

}
