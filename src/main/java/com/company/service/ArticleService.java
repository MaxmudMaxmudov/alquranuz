package com.company.service;

import com.company.dto.article.ArticleDTO;
import com.company.entity.ArticleEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ArticleStatus;
import com.company.exceptions.BadRequestException;
import com.company.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArticleService {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDTO create(Integer userId, ArticleDTO dto) {
        ProfileEntity profile = profileService.get(userId);

        if (dto.getContent() == null && dto.getContent().isEmpty()) {
            throw new BadRequestException("Content is empty");
        }
        if (dto.getTitle() == null && dto.getTitle().isEmpty()) {
            throw new BadRequestException("Title is empty");
        }

        ArticleEntity entity = new ArticleEntity();

        entity.setProfile(profile);
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(LocalDateTime.now());

        articleRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<ArticleDTO> getAll() {
        return articleRepository.findAll().stream().map(this::ToDto).collect(Collectors.toList());
    }

    public ArticleDTO ToDto(ArticleEntity entity) {
        ArticleDTO dto = new ArticleDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setProfileId(entity.getProfile().getId());
        if (entity.getPublisher() != null)
            dto.setPublisherId(entity.getPublisher().getId());
        dto.setPublisherDate(entity.getPublisherDate());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());

        return dto;
    }

//    public String publish(Integer userId, Integer id) {
//
//        ArticleEntity entity = get(id);
//        ProfileEntity publisher = profileService.get(userId);
//        entity.setPublisher(publisher);
//        entity.setPublisherDate(LocalDateTime.now());
//
//        return "Published"
//    }

    public String update(ArticleDTO dto) {
        ArticleEntity entity = get(dto.getId());
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setStatus(ArticleStatus.BLOCKED);
        articleRepository.save(entity);

        return "Successfully";
    }

    public ArticleDTO delete(Integer id) {

        ArticleDTO dto = getById(id);
        articleRepository.deleteById(id);
        return dto;
    }

    public ArticleEntity get(Integer id) {
        return articleRepository.getById(id);
    }

    public ArticleDTO getById(Integer id) {
        return articleRepository.findById(id).map(this::ToDto)
                .orElseThrow(() -> new RuntimeException("Bunday article NOT EXIST"));
    }

    public String publish(Integer id, Integer userId) {
        ArticleEntity entity = get(id);
        ProfileEntity publisher = profileService.get(userId);
        entity.setPublisher(publisher);
        entity.setPublisherDate(LocalDateTime.now());
        return "Published";
    }


}
