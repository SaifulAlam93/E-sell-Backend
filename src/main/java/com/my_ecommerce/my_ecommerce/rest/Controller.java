package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.message.ResponseMessage;
import com.my_ecommerce.my_ecommerce.model.FileInfo;
import com.my_ecommerce.my_ecommerce.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class Controller {


    @Autowired
    private StorageService service;

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
        String message = "";
        try {
            String uploadImage = service.uploadImageToFileSystem(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = service.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/fileSystem")
    public ResponseEntity<?> getAllImage() throws IOException {
        List<FileInfo> fileInfoList = service.getAllImage();
        return ResponseEntity.status(HttpStatus.OK).body(fileInfoList);
    }
}
