package com.example.demo.controller;

import com.example.demo.common.RestResponse;
import com.example.demo.dto.OrganizationDtoReq;
import com.example.demo.dto.OrganizationDtoRes;
import com.example.demo.entity.Organization;
import com.example.demo.serivce.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/add")
    public ResponseEntity<RestResponse<OrganizationDtoRes>> addOrganization(@RequestBody OrganizationDtoReq orgData) {
        OrganizationDtoRes organizationDtoRes = organizationService.addOrganization(orgData, orgData.getSpace().getId());

        return new ResponseEntity<>(RestResponse.of(organizationDtoRes), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<OrganizationDtoRes>> updateOrganization(@PathVariable Long id, @RequestBody OrganizationDtoReq orgData) {
        OrganizationDtoRes organizationDtoRes = organizationService.updateOrganization(orgData, id);
        return new ResponseEntity<>(RestResponse.of(organizationDtoRes), HttpStatus.OK);
    }

    @GetMapping("/active/{spaceId}")
    public ResponseEntity<RestResponse<List<OrganizationDtoRes>>> getActiveOrgs(@PathVariable Long spaceId) {
        List<OrganizationDtoRes> activeOrgs = organizationService.getActiveOrgs(spaceId);
        return new ResponseEntity<>(RestResponse.of(activeOrgs),HttpStatus.OK);
    }

    @GetMapping("/names/{spaceId}")
    public ResponseEntity<RestResponse<List<String>>> getAllOrgNames(@PathVariable Long spaceId) {
        List<String> orgNames = organizationService.getAllOrgNames(spaceId);
        return new ResponseEntity<>(RestResponse.of(orgNames),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.ok("Organization with ID " + id + " deleted successfully.");
    }
}
