package com.my_ecommerce.my_ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Products {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column(columnDefinition = "longtext")
    private String longDesc;

    @Column
    private String shortDesc;

    @Column
    private BigDecimal unitPrice;

    @Column
    private String unitType;

    @Column
    private BigDecimal unitWeight;

    @Column
    private BigDecimal totalQuantity;

    @Column
    private BigDecimal currentStock;

    @Column
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "products")
    private Set<ProductOption> productOptions;

    @ManyToMany
    @JoinTable(
            name = "product_file",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "file_upload_id")
    )
    private Set<FileUpload> fileUploads;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
