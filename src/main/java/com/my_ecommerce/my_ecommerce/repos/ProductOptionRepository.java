package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    @Query(value = " select * from product_option where products_id = :products_id ", nativeQuery = true)
    Set<ProductOption> getAllByProductId(@Param(value = "products_id") Long id);
}
