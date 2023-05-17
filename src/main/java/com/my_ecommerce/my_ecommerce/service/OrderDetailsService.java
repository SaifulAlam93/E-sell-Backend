package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.OrderDetails;
import com.my_ecommerce.my_ecommerce.domain.Orders;
import com.my_ecommerce.my_ecommerce.domain.Products;
import com.my_ecommerce.my_ecommerce.model.OrderDetailsDTO;
import com.my_ecommerce.my_ecommerce.repos.OrderDetailsRepository;
import com.my_ecommerce.my_ecommerce.repos.OrdersRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductsRepository;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;

    public OrderDetailsService(final OrderDetailsRepository orderDetailsRepository,
            final OrdersRepository ordersRepository, final ProductsRepository productsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.ordersRepository = ordersRepository;
        this.productsRepository = productsRepository;
    }

    public List<OrderDetailsDTO> findAll() {
        final List<OrderDetails> orderDetailss = orderDetailsRepository.findAll(Sort.by("id"));
        return orderDetailss.stream()
                .map((orderDetails) -> mapToDTO(orderDetails, new OrderDetailsDTO()))
                .toList();
    }

    public OrderDetailsDTO get(final Long id) {
        return orderDetailsRepository.findById(id)
                .map(orderDetails -> mapToDTO(orderDetails, new OrderDetailsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrderDetailsDTO orderDetailsDTO) {
        final OrderDetails orderDetails = new OrderDetails();
        mapToEntity(orderDetailsDTO, orderDetails);
        return orderDetailsRepository.save(orderDetails).getId();
    }

    public void update(final Long id, final OrderDetailsDTO orderDetailsDTO) {
        final OrderDetails orderDetails = orderDetailsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderDetailsDTO, orderDetails);
        orderDetailsRepository.save(orderDetails);
    }

    public void delete(final Long id) {
        orderDetailsRepository.deleteById(id);
    }

    private OrderDetailsDTO mapToDTO(final OrderDetails orderDetails,
            final OrderDetailsDTO orderDetailsDTO) {
        orderDetailsDTO.setId(orderDetails.getId());
        orderDetailsDTO.setQuantity(orderDetails.getQuantity());
        orderDetailsDTO.setPrice(orderDetails.getPrice());
        orderDetailsDTO.setOrders(orderDetails.getOrders() == null ? null : orderDetails.getOrders().getId());
        orderDetailsDTO.setProducts(orderDetails.getProducts() == null ? null : orderDetails.getProducts().getId());
        return orderDetailsDTO;
    }

    private OrderDetails mapToEntity(final OrderDetailsDTO orderDetailsDTO,
            final OrderDetails orderDetails) {
        orderDetails.setQuantity(orderDetailsDTO.getQuantity());
        orderDetails.setPrice(orderDetailsDTO.getPrice());
        final Orders orders = orderDetailsDTO.getOrders() == null ? null : ordersRepository.findById(orderDetailsDTO.getOrders())
                .orElseThrow(() -> new NotFoundException("orders not found"));
        orderDetails.setOrders(orders);
        final Products products = orderDetailsDTO.getProducts() == null ? null : productsRepository.findById(orderDetailsDTO.getProducts())
                .orElseThrow(() -> new NotFoundException("products not found"));
        orderDetails.setProducts(products);
        return orderDetails;
    }

}
