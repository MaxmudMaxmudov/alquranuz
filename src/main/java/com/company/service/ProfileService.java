package com.company.service;

import com.company.dto.profile.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO dto) {

        dto.setCreatedDate(LocalDateTime.now());

        ProfileEntity entity = new ProfileEntity();
        String password= DigestUtils.md5Hex(dto.getPassword());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(password);
        entity.setLogin(dto.getLogin());
        entity.setRole(dto.getRole());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(dto.getCreatedDate());

        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Profile not found"));
    }

    public ProfileDTO getById(Integer id) {
        ProfileEntity entity = profileRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Profile Not Found with this ID"));

        return ToDTO(entity);
    }

    public List<ProfileDTO> getAll() {
        return profileRepository.findAll().stream()
                .map(this::ToDTO).collect(Collectors.toList());
    }

    public ProfileDTO update(ProfileDTO dto) {
        ProfileDTO old = getById(dto.getId());

        if (dto.getName() != null && dto.getName() != old.getName()) {
            profileRepository.updateNameById(dto.getId(), dto.getName());
        }

        if (dto.getSurname() != null && dto.getSurname() != old.getSurname()) {
            profileRepository.updateSurnameById(dto.getId(), dto.getSurname());
        }

        if (dto.getEmail() != null && dto.getEmail() != old.getEmail()) {
            profileRepository.updateEmailById(dto.getId(), dto.getEmail());
        }

        if (dto.getLogin() != null && dto.getLogin() != old.getLogin()) {
            profileRepository.updateLoginById(dto.getId(), dto.getLogin());
        }

        if (dto.getPassword() != null && dto.getPassword() != old.getPassword()) {
            String password = DigestUtils.md5Hex(dto.getPassword());
            profileRepository.updatePasswordById(dto.getId(), password);
        }

        if (dto.getRole() != null && dto.getRole() != old.getRole()) {
            profileRepository.updateRoleById(dto.getId(), dto.getRole());
        }

        return getById(dto.getId());

    }

    public ProfileDTO deleteById(Integer id) {
        ProfileDTO dto = getById(id);
        profileRepository.deleteById(id);
        return dto;
    }

    public ProfileDTO ToDTO(ProfileEntity entity) {

        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
