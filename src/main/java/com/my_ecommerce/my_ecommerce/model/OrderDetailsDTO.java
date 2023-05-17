package com.my_ecommerce.my_ecommerce.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDetailsDTO {

    private Long id;
    private Double quantity;
    private Double price;
    private Long orders;
    private Long products;

}
