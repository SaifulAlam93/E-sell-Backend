package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.Orders;
import com.my_ecommerce.my_ecommerce.domain.User01;
import com.my_ecommerce.my_ecommerce.model.OrdersDTO;
import com.my_ecommerce.my_ecommerce.repos.OrdersRepository;
import com.my_ecommerce.my_ecommerce.repos.UserRepository01;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final UserRepository01 userRepository01;

    public OrdersService(final OrdersRepository ordersRepository,
            final UserRepository01 userRepository01) {
        this.ordersRepository = ordersRepository;
        this.userRepository01 = userRepository01;
    }

    public List<OrdersDTO> findAll() {
        final List<Orders> orderss = ordersRepository.findAll(Sort.by("id"));
        return orderss.stream()
                .map((orders) -> mapToDTO(orders, new OrdersDTO()))
                .toList();
    }

    public OrdersDTO get(final Long id) {
        return ordersRepository.findById(id)
                .map(orders -> mapToDTO(orders, new OrdersDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrdersDTO ordersDTO) {
        final Orders orders = new Orders();
        mapToEntity(ordersDTO, orders);
        return ordersRepository.save(orders).getId();
    }

    public void update(final Long id, final OrdersDTO ordersDTO) {
        final Orders orders = ordersRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ordersDTO, orders);
        ordersRepository.save(orders);
    }

    public void delete(final Long id) {
        ordersRepository.deleteById(id);
    }

    private OrdersDTO mapToDTO(final Orders orders, final OrdersDTO ordersDTO) {
        ordersDTO.setId(orders.getId());
        ordersDTO.setTotalPrice(orders.getTotalPrice());
        ordersDTO.setOrderAddress1(orders.getOrderAddress1());
        ordersDTO.setOrderAddress2(orders.getOrderAddress2());
        ordersDTO.setOrderCity(orders.getOrderCity());
        ordersDTO.setOrderState(orders.getOrderState());
        ordersDTO.setZip(orders.getZip());
        ordersDTO.setHouseNo(orders.getHouseNo());
        ordersDTO.setPhone(orders.getPhone());
        ordersDTO.setEmail(orders.getEmail());
        ordersDTO.setTotalProductAmount(orders.getTotalProductAmount());
        ordersDTO.setUser(orders.getUser01() == null ? null : orders.getUser01().getUserName());
        return ordersDTO;
    }

    private Orders mapToEntity(final OrdersDTO ordersDTO, final Orders orders) {
        orders.setTotalPrice(ordersDTO.getTotalPrice());
        orders.setOrderAddress1(ordersDTO.getOrderAddress1());
        orders.setOrderAddress2(ordersDTO.getOrderAddress2());
        orders.setOrderCity(ordersDTO.getOrderCity());
        orders.setOrderState(ordersDTO.getOrderState());
        orders.setZip(ordersDTO.getZip());
        orders.setHouseNo(ordersDTO.getHouseNo());
        orders.setPhone(ordersDTO.getPhone());
        orders.setEmail(ordersDTO.getEmail());
        orders.setTotalProductAmount(ordersDTO.getTotalProductAmount());
        final User01 user01 = ordersDTO.getUser() == null ? null : userRepository01.findById(ordersDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user01 not found"));
        orders.setUser01(user01);
        return orders;
    }

}
