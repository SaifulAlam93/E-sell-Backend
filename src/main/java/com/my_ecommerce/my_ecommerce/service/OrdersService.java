package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.*;
import com.my_ecommerce.my_ecommerce.model.OrderDetailsDTO;
import com.my_ecommerce.my_ecommerce.model.OrdersDTO;
import com.my_ecommerce.my_ecommerce.repos.OrderDetailsRepository;
import com.my_ecommerce.my_ecommerce.repos.OrdersRepository;
import com.my_ecommerce.my_ecommerce.repos.UserRepository;
import com.my_ecommerce.my_ecommerce.repos.UserRepository01;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrdersService {

    @Autowired
    private  OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private UserRepository userRepository;

//    public OrdersService(final OrdersRepository ordersRepository,
//            final UserRepository01 userRepository01) {
//        this.ordersRepository = ordersRepository;
//        this.userRepository01 = userRepository01;
//    }

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

        Long orderId = ordersRepository.save(orders).getId();

        if (ordersDTO.getOrderDetails()!=null){
            for (OrderDetailsDTO ord:ordersDTO.getOrderDetails()
            ) {
                ord.setOrders(orderId);
                orderDetailsService.create(ord);
            }
        }
        return orderId;
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
        ordersDTO.setTax(orders.getTax());
        ordersDTO.setFirstName(orders.getFirstName());
        ordersDTO.setLastName(orders.getLastName());
        ordersDTO.setShipping(orders.getShipping());
        ordersDTO.setOrderAddress1(orders.getOrderAddress1());
        ordersDTO.setOrderAddress2(orders.getOrderAddress2());
        ordersDTO.setOrderCity(orders.getOrderCity());
        ordersDTO.setOrderState(orders.getOrderState());
        ordersDTO.setZip(orders.getZip());
        ordersDTO.setHouseNo(orders.getHouseNo());
        ordersDTO.setPhone(orders.getPhone());
        ordersDTO.setEmail(orders.getEmail());
        ordersDTO.setTotalProductAmount(orders.getTotalProductAmount());
        ordersDTO.setUser(orders.getUser() == null ? null : orders.getUser().getId());
        return ordersDTO;
    }

    private Orders mapToEntity(final OrdersDTO ordersDTO, final Orders orders) {
        orders.setTotalPrice(ordersDTO.getTotalPrice());
        orders.setTax(ordersDTO.getTax());
        orders.setFirstName(ordersDTO.getFirstName());
        orders.setLastName(ordersDTO.getLastName());
        orders.setShipping(ordersDTO.getShipping());
        orders.setOrderAddress1(ordersDTO.getOrderAddress1());
        orders.setOrderAddress2(ordersDTO.getOrderAddress2());
        orders.setOrderCity(ordersDTO.getOrderCity());
        orders.setOrderState(ordersDTO.getOrderState());
        orders.setZip(ordersDTO.getZip());
        orders.setHouseNo(ordersDTO.getHouseNo());
        orders.setPhone(ordersDTO.getPhone());
        orders.setEmail(ordersDTO.getEmail());
        orders.setTotalProductAmount(ordersDTO.getTotalProductAmount());
        final User user = ordersDTO.getUser() == null ? null : userRepository.findById(ordersDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user01 not found"));
        orders.setUser(user);
        return orders;
    }

}
