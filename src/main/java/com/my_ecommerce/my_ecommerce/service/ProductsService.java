package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.*;
import com.my_ecommerce.my_ecommerce.model.ProductsDTO;
import com.my_ecommerce.my_ecommerce.repos.FileUploadRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductCategoryRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductOptionRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductsRepository;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ProductsService {
    private final String FOLDER_PATH="E://img/static/image/";

    private final ProductsRepository productsRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private final ProductOptionRepository productOptionRepository;
    private final FileUploadRepository fileUploadRepository;

    public ProductsService(final ProductsRepository productsRepository,
                           final ProductCategoryRepository productCategoryRepository,
                           final  ProductOptionRepository productOptionRepository,
                           final FileUploadRepository fileUploadRepository) {
        this.productsRepository = productsRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productOptionRepository = productOptionRepository;
        this.fileUploadRepository = fileUploadRepository;
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
        productsDTO.setFileUploads(products.getFileUploads() == null ? null : products.getFileUploads().stream()
                .map(fileUpload -> fileUpload.getId())
                .toList());
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
//        final List<FileUpload> fileUploads = fileUploadRepository.findAllById(
//                productsDTO.getFileUploads() == null ? Collections.emptyList() : productsDTO.getFileUploads());
//        if (fileUploads.size() != (productsDTO.getFileUploads() == null ? 0 : productsDTO.getFileUploads().size())) {
//            throw new NotFoundException("one of fileUploads not found");
//        }
//        products.setFileUploads(fileUploads.stream().collect(Collectors.toSet()));
        return products;
    }



    public Long createWithFile(final ProductsDTO productsDTO, MultipartFile[] file) {
        final Products products = new Products();

        mapToEntity(productsDTO, products);


        Set<FileUpload> fileUploads = new HashSet<>();
        try {
            int index = 0;
            for (MultipartFile mf: file)
            {

                String filePath = FOLDER_PATH+mf.getOriginalFilename();

                FileUpload fileUpload=fileUploadRepository.save(
                        FileUpload.builder()
                                .name(mf.getOriginalFilename())
                                .type(mf.getContentType())
                                .imageNo(index++)
                                .imageType("test")
                                .url(filePath).build());

                mf.transferTo(new File(filePath));
                if (fileUpload != null) {
                    fileUploads.add(fileUpload);
                }
            }
            products.setFileUploads(fileUploads);
        }catch (Exception e){}




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

}
