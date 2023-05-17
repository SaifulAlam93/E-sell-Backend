package com.my_ecommerce.my_ecommerce.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductOptionDTO {

    private Long id;

    @Size(max = 255)
    private String color;

    @Size(max = 255)
    private String size;

    private Integer quantity;

    private Long products;

}
