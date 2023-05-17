package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.model.ProductOptionDTO;
import com.my_ecommerce.my_ecommerce.service.ProductOptionService;
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
@RequestMapping(value = "/api/productOptions", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductOptionResource {

    private final ProductOptionService productOptionService;

    public ProductOptionResource(final ProductOptionService productOptionService) {
        this.productOptionService = productOptionService;
    }

    @GetMapping
    public ResponseEntity<List<ProductOptionDTO>> getAllProductOptions() {
        return ResponseEntity.ok(productOptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOptionDTO> getProductOption(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(productOptionService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProductOption(
            @RequestBody @Valid final ProductOptionDTO productOptionDTO) {
        final Long createdId = productOptionService.create(productOptionDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductOption(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ProductOptionDTO productOptionDTO) {
        productOptionService.update(id, productOptionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProductOption(@PathVariable(name = "id") final Long id) {
        productOptionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
