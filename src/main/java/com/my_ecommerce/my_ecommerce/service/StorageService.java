package com.my_ecommerce.my_ecommerce.service;


import com.my_ecommerce.my_ecommerce.domain.FileData;
import com.my_ecommerce.my_ecommerce.domain.ImageData;
import com.my_ecommerce.my_ecommerce.model.FileInfo;
import com.my_ecommerce.my_ecommerce.repos.FileDataRepository;
import com.my_ecommerce.my_ecommerce.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH="E://img/static/image/";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {

        String filePath = FOLDER_PATH+file.getOriginalFilename();
        FileData fileData=fileDataRepository.save(
                FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());


//        FileData fileData  = new FileData();
//        fileData.setName(file.getOriginalFilename());
//        fileData.setType(file.getContentType());
//        fileData.setFilePath(filePath);
//        fileDataRepository.save(fileData);


        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        FileData fileData = fileDataRepository.findAllSortedByNameUsingNative(fileName);
        String filePath=fileData.getFilePath();
//        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public List<FileInfo> getAllImage() throws IOException {
        List<FileInfo> fileInfos = new ArrayList<>();
        List<FileData> fileDataList = fileDataRepository.findAll();
        fileDataList.forEach(fileData -> {
            try {
                String filePath=fileData.getFilePath();
                byte[] images = Files.readAllBytes(new File(filePath).toPath());
                FileInfo fileInfo = new FileInfo(fileData.getName(), fileData.getFilePath(), images);
                fileInfos.add(fileInfo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return fileInfos;
    }



}
