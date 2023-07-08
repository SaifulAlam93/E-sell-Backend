package com.my_ecommerce.my_ecommerce.rest;

import com.my_ecommerce.my_ecommerce.model.OrderDetailsDTO;
import com.my_ecommerce.my_ecommerce.service.OrderDetailsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/orderDetailss", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://192.168.2.220:4200", allowCredentials = "true")
public class OrderDetailsResource {

    private final OrderDetailsService orderDetailsService;

    public OrderDetailsResource(final OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailsDTO>> getAllOrderDetailss() {
        return ResponseEntity.ok(orderDetailsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(orderDetailsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrderDetails(
            @RequestBody @Valid final OrderDetailsDTO orderDetailsDTO) {
        final Long createdId = orderDetailsService.create(orderDetailsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderDetails(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final OrderDetailsDTO orderDetailsDTO) {
        orderDetailsService.update(id, orderDetailsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable(name = "id") final Long id) {
        orderDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
