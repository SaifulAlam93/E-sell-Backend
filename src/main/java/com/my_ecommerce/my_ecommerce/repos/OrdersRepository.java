package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
