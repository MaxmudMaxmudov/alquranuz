package com.company.service;

import com.company.dto.profile.AttachDTO;
import com.company.entity.AttachEntity;
import com.company.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachService {

    @Autowired
    private AttachRepository attachRepository;
    @Value("${attach.upload.folder}")
    private String uploadFolder;
    @Value("${attach.open.url}")
    private String attachOpenUrl;
    public String saveFile(MultipartFile file) {

        String filePath =   getYmDString();
        String fileName = UUID.randomUUID().toString();
        String extension = getExtension(file.getOriginalFilename());



        File folder = new File(uploadFolder + filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFolder + "/"+ fileName + "."+extension); // uploads/zari.jpg
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();

            entity.setFilePath(filePath);
            entity.setExtension(extension);
            entity.setOriginName(file.getOriginalFilename());
            entity.setSize(file.getSize());
            entity.setKey(fileName);

            AttachDTO attachDTO = new AttachDTO();
            attachDTO.setId(entity.getId());
            attachDTO.setKey(entity.getKey());
            attachDTO.setFilePath(entity.getFilePath());
            attachDTO.setCreatedDate(entity.getCreatedDate());
            attachDTO.setSize(entity.getSize());
            attachDTO.setOriginName(entity.getOriginName());

            return fileName ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] loadAttach(String key) throws IOException{

        Optional<AttachEntity> optional = attachRepository.findByKey(key);
        if (!optional.isPresent()){

        }

        byte[] imageInByte;

        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File(uploadFolder + "/" ));
        }catch (Exception e){
            return new byte[0];
        }

//        String extension = getExtension();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(originalImage,extension,baos);

        baos.flush();
        imageInByte= baos.toByteArray();
        baos.close();
        return imageInByte;
    }
    public static String getYmDString(){
        int year= Calendar.getInstance().get(Calendar.YEAR);
        int month= Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day= Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day + "/";
    }

    public String getExtension(String fileName){
        int lastIndex = fileName.lastIndexOf(fileName);
        return null;
    }

    public void delete(String key){
        Optional<AttachEntity> optional = attachRepository.findByKey(key);
        if (!optional.isPresent()){
            return;
        }
        String filePath = optional.get().getFilePath() + "/" + key + "." + optional.get().getExtension();

        File file = new File(uploadFolder + "/" + filePath);
        if (file.exists()){
            file.delete();
        }
        attachRepository.delete(optional.get());
    }
}
