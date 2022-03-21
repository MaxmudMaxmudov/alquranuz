package com.company.controller;

import com.company.service.AttachService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
@Api(tags = "Attach")
public class AttachController {

    @Autowired
    private AttachService attachService;
    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestParam("file")MultipartFile file){
        String fileName=attachService.saveFile(file);
        return ResponseEntity.ok().body(file);
    }
}
