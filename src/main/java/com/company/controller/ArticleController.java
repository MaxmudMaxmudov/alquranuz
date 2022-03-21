package com.company.controller;

import com.company.dto.article.ArticleDTO;
import com.company.dto.profile.ProfileJwtDTO;
import com.company.enums.ProfileRole;
import com.company.service.ArticleService;
import com.company.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/article")
@Api(tags = "Article controller")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
//    public ResponseEntity<?> create(@Valid @RequestBody)

    @PostMapping("/create")
    @ApiOperation(value = "Create new Article", notes = "Create a New Article for Mederator")
    public ResponseEntity<?> create(@Valid @RequestBody ArticleDTO dto, HttpServletRequest request) {

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.MODERATOR_ROLE);

        ArticleDTO response = articleService.create(jwtDTO.getId(), dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Get all article",notes = "Auth required")
    public ResponseEntity<?> getAll(HttpServletRequest request){
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        return ResponseEntity.ok(articleService.getAll());
    }

    @PutMapping("/update")
    @ApiOperation(value = "@Publishing Article")
    public ResponseEntity<?> update (@RequestBody ArticleDTO dto,  HttpServletRequest request){
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request,ProfileRole.PUBLISHER_ROLE);

        return ResponseEntity.ok(articleService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "@Deleting article")
    public ResponseEntity<?> delete(@PathVariable Integer id,HttpServletRequest request){
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request,ProfileRole.MODERATOR_ROLE);

        return ResponseEntity.ok(articleService.delete(id));
    }

    @PutMapping("/publish/{id}")
    @ApiOperation(value = "Publishing Article", notes = "for PUBLISHER_ROLE")
    public ResponseEntity publish(@PathVariable Integer id,
                                  HttpServletRequest request) {

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.PUBLISHER_ROLE);

        return ResponseEntity.ok(articleService.publish(id, jwtDTO.getId()));

    }
}
