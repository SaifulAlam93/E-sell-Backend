package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.ProductCategory;
import com.my_ecommerce.my_ecommerce.model.ProductCategoryDTO;
import com.my_ecommerce.my_ecommerce.repos.ProductCategoryRepository;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(final ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategoryDTO> findAll() {
        final List<ProductCategory> productCategorys = productCategoryRepository.findAll(Sort.by("id"));
        return productCategorys.stream()
                .map((productCategory) -> mapToDTO(productCategory, new ProductCategoryDTO()))
                .toList();
    }

    public ProductCategoryDTO get(final Long id) {
        return productCategoryRepository.findById(id)
                .map(productCategory -> mapToDTO(productCategory, new ProductCategoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductCategoryDTO productCategoryDTO) {
        final ProductCategory productCategory = new ProductCategory();
        mapToEntity(productCategoryDTO, productCategory);
        return productCategoryRepository.save(productCategory).getId();
    }

    public void update(final Long id, final ProductCategoryDTO productCategoryDTO) {
        final ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productCategoryDTO, productCategory);
        productCategoryRepository.save(productCategory);
    }

    public void delete(final Long id) {
        productCategoryRepository.deleteById(id);
    }

    private ProductCategoryDTO mapToDTO(final ProductCategory productCategory,
            final ProductCategoryDTO productCategoryDTO) {
        productCategoryDTO.setId(productCategory.getId());
        productCategoryDTO.setCategoryName(productCategory.getCategoryName());
        productCategoryDTO.setCategoryDescription(productCategory.getCategoryDescription());
        return productCategoryDTO;
    }

    private ProductCategory mapToEntity(final ProductCategoryDTO productCategoryDTO,
            final ProductCategory productCategory) {
        productCategory.setCategoryName(productCategoryDTO.getCategoryName());
        productCategory.setCategoryDescription(productCategoryDTO.getCategoryDescription());
        return productCategory;
    }

}
