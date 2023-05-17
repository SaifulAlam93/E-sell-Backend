package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.ProductCategory;
import com.my_ecommerce.my_ecommerce.domain.ProductOption;
import com.my_ecommerce.my_ecommerce.domain.Products;
import com.my_ecommerce.my_ecommerce.model.ProductsDTO;
import com.my_ecommerce.my_ecommerce.repos.ProductCategoryRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductOptionRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductsRepository;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private final ProductOptionRepository productOptionRepository;

    public ProductsService(final ProductsRepository productsRepository,
                           final ProductCategoryRepository productCategoryRepository,
                           final  ProductOptionRepository productOptionRepository) {
        this.productsRepository = productsRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productOptionRepository = productOptionRepository;
    }

    public List<ProductsDTO> findAll() {
        final List<Products> productss = productsRepository.findAll(Sort.by("id"));


        return productss.stream()
                .map((products) -> {

                    ProductsDTO productsDTO = new ProductsDTO();
                    Set<ProductOption> productOptions = new HashSet<>();
                    mapToDTO(products, productsDTO);
                    productOptions = productOptionRepository.getAllByProductId(products.getId());
                    productOptions.forEach(productOption -> productOption.setProducts(null));
                    productsDTO.setProductOptions(productOptions);

                    return productsDTO;
                })
                .toList();
    }

    public ProductsDTO get(final Long id) {
        return productsRepository.findById(id)
                .map(products -> mapToDTO(products, new ProductsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductsDTO productsDTO) {
        final Products products = new Products();
        mapToEntity(productsDTO, products);
        Long pid = productsRepository.save(products).getId();
        products.setId(pid);
        if (productsDTO.getProductOptions()!=null){
            for (ProductOption p:productsDTO.getProductOptions()
            ) {
                p.setProducts(products);
                productOptionRepository.save(p);
            }
        }
        return pid;
    }

    public void update(final Long id, final ProductsDTO productsDTO) {
        final Products products = productsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productsDTO, products);
        productsRepository.save(products);
    }

    public void delete(final Long id) {
        productsRepository.deleteById(id);
    }

    private ProductsDTO mapToDTO(final Products products, final ProductsDTO productsDTO) {
        productsDTO.setId(products.getId());
        productsDTO.setProductName(products.getProductName());
        productsDTO.setLongDesc(products.getLongDesc());
        productsDTO.setShortDesc(products.getShortDesc());
        productsDTO.setUnitPrice(products.getUnitPrice());
        productsDTO.setUnitWeight(products.getUnitWeight());
        productsDTO.setTotalQuantity(products.getTotalQuantity());
        productsDTO.setCurrentStock(products.getCurrentStock());
        productsDTO.setImage(products.getImage());
        productsDTO.setProductCategory(products.getProductCategory() == null ? null : products.getProductCategory().getId());
        return productsDTO;
    }

    private Products mapToEntity(final ProductsDTO productsDTO, final Products products) {
        products.setProductName(productsDTO.getProductName());
        products.setLongDesc(productsDTO.getLongDesc());
        products.setShortDesc(productsDTO.getShortDesc());
        products.setUnitPrice(productsDTO.getUnitPrice());
        products.setUnitWeight(productsDTO.getUnitWeight());
        products.setTotalQuantity(productsDTO.getTotalQuantity());
        products.setCurrentStock(productsDTO.getCurrentStock());
        products.setImage(productsDTO.getImage());
        final ProductCategory productCategory = productsDTO.getProductCategory() == null ? null : productCategoryRepository.findById(productsDTO.getProductCategory())
                .orElseThrow(() -> new NotFoundException("productCategory not found"));
        products.setProductCategory(productCategory);

        return products;
    }

}
