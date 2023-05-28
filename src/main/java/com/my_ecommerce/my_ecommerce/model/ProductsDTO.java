package com.my_ecommerce.my_ecommerce.model;

import com.my_ecommerce.my_ecommerce.domain.FileUpload;
import com.my_ecommerce.my_ecommerce.domain.ProductOption;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Getter
@Setter
public class ProductsDTO {

    private Long id;

    @Size(max = 255)
    private String productName;

    private String longDesc;

    @Size(max = 255)
    private String shortDesc;

    private BigDecimal unitPrice;

    private String unitType;

    private BigDecimal unitWeight;

    private BigDecimal totalQuantity;

    private BigDecimal currentStock;

    @Size(max = 255)
    private String image;

    private Long productCategory;

    private Set<ProductOption> productOptions;

    private List<Long> fileUploads;

    private Set<FileUploadDTO> fileUploadList;

}
