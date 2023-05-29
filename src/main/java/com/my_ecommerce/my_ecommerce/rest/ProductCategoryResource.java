package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.model.ProductCategoryDTO;
import com.my_ecommerce.my_ecommerce.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/api/productCategorys", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://192.168.191.222:4200", allowCredentials = "true")
public class ProductCategoryResource {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryResource(final ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategorys() {
        return ResponseEntity.ok(productCategoryService.findAll());
    }


    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFileAndData(@RequestParam("file") MultipartFile file, @ModelAttribute ProductCategoryDTO productCategoryDTO) {
        // Access the multipart file
        // ...

        // Access the request body data
        String name = productCategoryDTO.getCategoryName();
        String  age = productCategoryDTO.getCategoryDescription();
        String  fileName = file.getOriginalFilename();
        // ...

        return ResponseEntity.ok("File uploaded successfully. "+ name+"  "+age+"  "+ fileName);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductCategory(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(productCategoryService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProductCategory(
            @RequestBody @Valid final ProductCategoryDTO productCategoryDTO) {
        final Long createdId = productCategoryService.create(productCategoryDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductCategory(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ProductCategoryDTO productCategoryDTO) {
        productCategoryService.update(id, productCategoryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable(name = "id") final Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
