package com.my_ecommerce.my_ecommerce.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductCategoryDTO {

    private Long id;

    @Size(max = 255)
    private String categoryName;

    @Size(max = 255)
    private String categoryDescription;

}
