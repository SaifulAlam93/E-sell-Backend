package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.message.ResponseMessage;
import com.my_ecommerce.my_ecommerce.model.FileInfo;
import com.my_ecommerce.my_ecommerce.model.ProductCategoryDTO;
import com.my_ecommerce.my_ecommerce.model.ProductsDTO;
import com.my_ecommerce.my_ecommerce.service.FileUploadService;
import com.my_ecommerce.my_ecommerce.service.ProductsService;
import com.my_ecommerce.my_ecommerce.service.StorageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://192.168.133.30:4200", allowCredentials = "true")
public class ProductsResource {
    @Autowired
    private ProductsService productsService;

    @Autowired
    StorageService service;
    @Autowired
    FileUploadService fileUploadService;


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
    public ResponseEntity<Long> createProduct(@RequestParam("file") MultipartFile[] file, @ModelAttribute ProductsDTO productsDTO) {
        final Long createdId = productsService.createWithFile(productsDTO, file);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }


    @PostMapping("/productImage/{id}")
    public ResponseEntity<Set<Long>> uploadProductImage(@PathVariable Long id,  @RequestParam("file") MultipartFile[] file) {
        final Set<Long> createdId = fileUploadService.fileUpload(id, file);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }


    @GetMapping("/productsWithImage")
    public ResponseEntity<List<ProductsDTO>> getAllProductsWithImage() {
        return ResponseEntity.ok(productsService.findAllWithImage());
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
