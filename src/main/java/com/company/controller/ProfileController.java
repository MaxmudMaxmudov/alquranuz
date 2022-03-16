package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.dto.ProfileJwtDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@Api(tags = "Profile controller", value = "Bu controllerni admin ishlata oladi")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "@ApiResponse(code = 400, message = \"...\")")})
    public ResponseEntity<?> create(@Valid @RequestBody ProfileDTO dto, HttpServletRequest request) {
        JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);
        ProfileDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createUser")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "@ApiResponse(code = 400, message = \"...\")")})
    public ResponseEntity<?> createUser(@Valid @RequestBody ProfileDTO dto) {
        ProfileDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody ProfileDTO dto,
                                 HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request);
        return ResponseEntity.ok(profileService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);
        return ResponseEntity.ok(profileService.deleteById(id));
    }

    @GetMapping("/all")
    public ResponseEntity getAll(HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id, HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);
        return ResponseEntity.ok(profileService.getById(id));
    }
}
