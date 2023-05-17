package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.ProductOption;
import com.my_ecommerce.my_ecommerce.domain.Products;
import com.my_ecommerce.my_ecommerce.model.ProductOptionDTO;
import com.my_ecommerce.my_ecommerce.repos.ProductOptionRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductsRepository;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    private final ProductsRepository productsRepository;

    public ProductOptionService(final ProductOptionRepository productOptionRepository,
            final ProductsRepository productsRepository) {
        this.productOptionRepository = productOptionRepository;
        this.productsRepository = productsRepository;
    }

    public List<ProductOptionDTO> findAll() {
        final List<ProductOption> productOptions = productOptionRepository.findAll(Sort.by("id"));
        return productOptions.stream()
                .map((productOption) -> mapToDTO(productOption, new ProductOptionDTO()))
                .toList();
    }

    public ProductOptionDTO get(final Long id) {
        return productOptionRepository.findById(id)
                .map(productOption -> mapToDTO(productOption, new ProductOptionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductOptionDTO productOptionDTO) {
        final ProductOption productOption = new ProductOption();
        mapToEntity(productOptionDTO, productOption);
        return productOptionRepository.save(productOption).getId();
    }

    public void update(final Long id, final ProductOptionDTO productOptionDTO) {
        final ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productOptionDTO, productOption);
        productOptionRepository.save(productOption);
    }

    public void delete(final Long id) {
        productOptionRepository.deleteById(id);
    }

    private ProductOptionDTO mapToDTO(final ProductOption productOption,
            final ProductOptionDTO productOptionDTO) {
        productOptionDTO.setId(productOption.getId());
        productOptionDTO.setColor(productOption.getColor());
        productOptionDTO.setSize(productOption.getSize());
        productOptionDTO.setQuantity(productOption.getQuantity());
        productOptionDTO.setProducts(productOption.getProducts() == null ? null : productOption.getProducts().getId());
        return productOptionDTO;
    }

    private ProductOption mapToEntity(final ProductOptionDTO productOptionDTO,
            final ProductOption productOption) {
        productOption.setColor(productOptionDTO.getColor());
        productOption.setSize(productOptionDTO.getSize());
        productOption.setQuantity(productOptionDTO.getQuantity());
        final Products products = productOptionDTO.getProducts() == null ? null : productsRepository.findById(productOptionDTO.getProducts())
                .orElseThrow(() -> new NotFoundException("products not found"));
        productOption.setProducts(products);
        return productOption;
    }

}
