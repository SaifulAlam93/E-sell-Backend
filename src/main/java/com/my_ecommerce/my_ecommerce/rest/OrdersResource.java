package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.model.OrderHistoryDTO;
import com.my_ecommerce.my_ecommerce.model.OrdersDTO;
import com.my_ecommerce.my_ecommerce.service.OrdersService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/orderss", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
public class OrdersResource {

    @Autowired
    private OrdersService ordersService;


    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrderss() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrders(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(ordersService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrders(@RequestBody @Valid final OrdersDTO ordersDTO) {
        final Long createdId = ordersService.create(ordersDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrders(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final OrdersDTO ordersDTO) {
        ordersService.update(id, ordersDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrders(@PathVariable(name = "id") final Long id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/orderList/{id}")
    public ResponseEntity<List<OrderHistoryDTO>> getOrderListWithDetail(@PathVariable(name = "id") final String uId) {
        return ResponseEntity.ok(ordersService.findAllByUser(uId));
    }
}
