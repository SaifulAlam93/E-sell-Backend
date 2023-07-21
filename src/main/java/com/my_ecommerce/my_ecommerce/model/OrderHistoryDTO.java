package com.my_ecommerce.my_ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
@Getter
@Setter
public class OrderHistoryDTO {

    private Long order_id;
    private String productName;
    private String userName;
    private String categoryName;
    private Double quantity;
    private Double price;
    private String lastUpdated;


}
