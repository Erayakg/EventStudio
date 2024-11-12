package com.example.demo.serivce;

import com.example.demo.dto.OrganizationDtoReq;
import com.example.demo.dto.OrganizationDtoRes;
import com.example.demo.entity.Organization;

import java.util.List;

public interface OrganizationService {

    OrganizationDtoRes addOrganization(OrganizationDtoReq organization, Long spaceId);

    OrganizationDtoRes updateOrganization(OrganizationDtoReq organization, Long id);

    OrganizationDtoRes getOrganization(Long id);

    OrganizationDtoRes deleteOrganization(Long id);

    List<String> getAllOrgNames(Long id);

    List<OrganizationDtoRes> getActiveOrgs(Long spaceId);

}
