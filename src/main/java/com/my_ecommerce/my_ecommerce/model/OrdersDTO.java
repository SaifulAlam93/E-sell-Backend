package com.my_ecommerce.my_ecommerce.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrdersDTO {

    private Long id;

    private Integer totalPrice;

    @Size(max = 255)
    private String orderAddress1;

    @Size(max = 255)
    private String orderAddress2;

    @Size(max = 255)
    private String orderCity;

    @Size(max = 255)
    private String orderState;

    @Size(max = 255)
    private String zip;

    @Size(max = 255)
    private String houseNo;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String email;

    private Integer totalProductAmount;

    @Size(max = 255)
    private String user;

}
