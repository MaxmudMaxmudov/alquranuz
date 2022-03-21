package com.company.controller;

import com.company.dto.MessageDTO;
import com.company.dto.profile.ProfileJwtDTO;
import com.company.enums.ProfileRole;
import com.company.service.EmailService;
import com.company.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/message")
@Api(tags = "Email Message controller")
public class MessageController {

    @Autowired
    private EmailService emailService;

    @GetMapping("{id}")
    @ApiOperation(value = "@ApiOperation(value = \" GET BY ID\")")
    public MessageDTO getById(@PathVariable Integer id, HttpServletRequest request) {

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);

        return emailService.getById(id);
    }

    @GetMapping("/last")
    @ApiOperation(value = "@ApiOperation(value = \" Ohirgi  ID\")")
    public MessageDTO getByPid(HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);
        return emailService.getLast();
    }

    @GetMapping("/today/{id}")
    @ApiOperation(value = "bugungi kun")
    public ResponseEntity<?> today(@PathVariable Integer pid, HttpServletRequest request) {

        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);

        return ResponseEntity.ok(emailService.getTodays(pid));
    }

    @GetMapping("unused")
    @ApiOperation(value = "Bu value", notes = "Bu esa notes", nickname = "bu nickname")
    public ResponseEntity getUnused(@RequestParam("page") int page,
                                    @RequestParam("size") int size,
                                    HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);
        return ResponseEntity.ok(emailService.getUnused(page, size));
    }

    @GetMapping
    @ApiOperation(value = "Get Page of All Likes")
    public ResponseEntity getAll(@RequestParam("page") int page,
                                 @RequestParam("size") int size,
                                 HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.ADMIN_ROLE);
        return ResponseEntity.ok(emailService.getAll(page, size));
    }


}
