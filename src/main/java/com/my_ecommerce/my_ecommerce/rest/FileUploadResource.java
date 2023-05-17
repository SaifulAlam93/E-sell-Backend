package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.model.FileUploadDTO;
import com.my_ecommerce.my_ecommerce.service.FileUploadService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/fileUploads", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileUploadResource {

    private final FileUploadService fileUploadService;

    public FileUploadResource(final FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public ResponseEntity<List<FileUploadDTO>> getAllFileUploads() {
        return ResponseEntity.ok(fileUploadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileUploadDTO> getFileUpload(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(fileUploadService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createFileUpload(
            @RequestBody @Valid final FileUploadDTO fileUploadDTO) {
        final Long createdId = fileUploadService.create(fileUploadDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFileUpload(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final FileUploadDTO fileUploadDTO) {
        fileUploadService.update(id, fileUploadDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFileUpload(@PathVariable(name = "id") final Long id) {
        fileUploadService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
