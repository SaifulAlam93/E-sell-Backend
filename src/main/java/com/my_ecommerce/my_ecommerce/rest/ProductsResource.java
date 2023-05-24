package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.message.ResponseMessage;
import com.my_ecommerce.my_ecommerce.model.FileInfo;
import com.my_ecommerce.my_ecommerce.model.ProductCategoryDTO;
import com.my_ecommerce.my_ecommerce.model.ProductsDTO;
import com.my_ecommerce.my_ecommerce.service.ProductsService;
import com.my_ecommerce.my_ecommerce.service.StorageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api/productss", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsResource {
    @Autowired
    private ProductsService productsService;

    @Autowired
    StorageService service;


    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getAllProductss() {
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProducts(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(productsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProducts(@RequestBody @Valid final ProductsDTO productsDTO) {
        final Long createdId = productsService.create(productsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProducts(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ProductsDTO productsDTO) {
        productsService.update(id, productsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProducts(@PathVariable(name = "id") final Long id) {
        productsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/productsWithImage")
    public ResponseEntity<Long> uploadFileAndData(@RequestParam("file") MultipartFile[] file, @ModelAttribute ProductsDTO productsDTO) {
        // Access the multipart file
        // ...

        // Access the request body data
        // ...
        final Long createdId = productsService.createWithFile(productsDTO, file);


        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
//        return ResponseEntity.ok("File uploaded successfully. "+ name+" "+ fileName);
    }

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
