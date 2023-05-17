package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
