package com.my_ecommerce.my_ecommerce.model;

import com.my_ecommerce.my_ecommerce.domain.OrderDetails;
import com.my_ecommerce.my_ecommerce.domain.ProductOption;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class OrdersDTO {

    private Long id;

    private Integer totalPrice;

    private Integer tax;

    private Integer shipping;

    @Size(max = 255)
    private String orderAddress1;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

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

    private Long user;


    private Set<OrderDetailsDTO> orderDetails;


}
