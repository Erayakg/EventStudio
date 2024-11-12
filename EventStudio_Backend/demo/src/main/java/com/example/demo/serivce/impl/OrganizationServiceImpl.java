package com.example.demo.serivce.impl;

import com.example.demo.dto.OrganizationDtoReq;
import com.example.demo.dto.OrganizationDtoRes;
import com.example.demo.entity.Organization;
import com.example.demo.entity.Space;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.repository.SpaceRepository;
import com.example.demo.serivce.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final SpaceRepository spaceRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, SpaceRepository spaceRepository) {
        this.organizationRepository = organizationRepository;
        this.spaceRepository = spaceRepository;
    }


    @Override
    public OrganizationDtoRes addOrganization(OrganizationDtoReq organizationDtoReq, Long spaceId) {
        // Space nesnesini veritabanından bul
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new EntityNotFoundException("Space not found"));

        // Yeni Organization nesnesini oluştur ve alanları ayarla
        Organization organization = new Organization();
        organization.setName(organizationDtoReq.getName());
        organization.setSpace(space);

        // Organization nesnesini kaydet
        Organization savedOrganization = organizationRepository.save(organization);

        // Cevap DTO nesnesini oluştur ve geri döndür
        return mapToDto(savedOrganization);
    }

    @Override
    public OrganizationDtoRes updateOrganization(OrganizationDtoReq organizationDtoReq, Long id) {
        // Organization nesnesini veritabanından bul
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));

        // Alanları güncelle
        organization.setName(organizationDtoReq.getName());

        // Güncellenmiş Organization nesnesini kaydet
        Organization updatedOrganization = organizationRepository.save(organization);

        // Cevap DTO nesnesini oluştur ve geri döndür
        return mapToDto(updatedOrganization);
    }

    @Override
    public OrganizationDtoRes getOrganization(Long id) {
        // Organization nesnesini bul ve DTO'ya dönüştürerek geri döndür
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        return mapToDto(organization);
    }

    @Override
    public OrganizationDtoRes deleteOrganization(Long id) {
        // Organization nesnesini bulun
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));

        // Organization nesnesini sil
        organizationRepository.delete(organization);

        // Silinen Organization için bir DTO döndür (silinen bilgileri göstermek için)
        return mapToDto(organization);
    }

    @Override
    public List<String> getAllOrgNames(Long spaceId) {
        // Tüm Organization nesnelerini bulun ve sadece adlarını alın
        return organizationRepository.findAllBySpaceId(spaceId).stream()
                .map(Organization::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationDtoRes> getActiveOrgs(Long spaceId) {
        // Tüm aktif Organization nesnelerini bulun ve DTO'ya dönüştürün
        return organizationRepository.findAllBySpaceId(spaceId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Helper method to convert Organization entity to OrganizationDtoRes
    private OrganizationDtoRes mapToDto(Organization organization) {
        OrganizationDtoRes.SpaceDto spaceDto = new OrganizationDtoRes.SpaceDto(
                organization.getSpace().getCreateDate(),
                organization.getSpace().getCreatedBy(),
                organization.getSpace().getSpaceName()
        );

        return new OrganizationDtoRes(
                organization.getId(),
                organization.getCreateDate(),
                organization.getUpdateDate(),
                organization.getCreatedBy(),
                organization.getUpdatedBy(),
                organization.getName(),
                spaceDto
        );
    }
}
