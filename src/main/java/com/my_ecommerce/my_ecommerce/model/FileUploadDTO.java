package com.my_ecommerce.my_ecommerce.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FileUploadDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String type;

    @Size(max = 255)
    private String url;

    @Size(max = 255)
    private Integer imageNo;

    @Size(max = 255)
    private String imageType;

    private Long products;

}
