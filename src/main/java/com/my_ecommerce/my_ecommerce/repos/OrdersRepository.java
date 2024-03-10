package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrdersRepository extends JpaRepository<Orders, Long> {



    @Query(value = " SELECT o.id,p.product_name, pc.category_name, od.quantity, od.price, od.last_updated, u.user_name  FROM order_details od\n" +
            " LEFT JOIN `orders` o ON od.orders_id = o.id\n" +
            " LEFT JOIN `products` p ON od.products_id = p.id\n" +
            " LEFT JOIN `product_category` pc ON p.product_category_id = pc.id " +
            " LEFT JOIN  User01 u ON o.user_id = u.user_name\n "+
            " WHERE o.user_name = :uId \n" +
            " ORDER BY od.last_updated DESC ", nativeQuery = true)

    List<Object[]> getAllByUserId(@Param("uId") String uId);



    @Query(value = " SELECT o.id,p.product_name, pc.category_name, od.quantity, od.price, od.last_updated, u.user_name FROM order_details od\n" +
            " LEFT JOIN `orders` o ON od.orders_id = o.id\n" +
            " LEFT JOIN `products` p ON od.products_id = p.id\n" +
            " LEFT JOIN `product_category` pc ON p.product_category_id = pc.id " +
            " LEFT JOIN  User01 u ON o.user_id = u.user_name\n "+
            " ORDER BY od.last_updated DESC ", nativeQuery = true)

    List<Object[]> getAll();
}
