package upc.edu.NerdyNestAPI.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.order.dto.OrderResponse;
import upc.edu.NerdyNestAPI.order.dto.mapper.OrderMapper;
import upc.edu.NerdyNestAPI.order.service.OrderService;

import java.util.List;

@Tag(name = "Gestión de Ordenes", description = "Controlador para operaciones relacionadas con ordenes")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Crea una nueva orden", description = "Crea una nueva orden para un cliente con su ID")
    @PostMapping(value = "/clients/{client_id}/orders")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable String client_id){
        var orderCreated = orderService.createOrder(client_id);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderCreated);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene las ordenes", description = "Obtiene las ordenes de un cliente con su ID")
    @GetMapping(value = "/clients/{client_id}")
    public ResponseEntity<List<OrderResponse>> getOrder(@PathVariable String client_id){
        var orders = orderService.getOrdersByClientId(client_id);
        var ordersResponse = OrderMapper.INSTANCE.ordersToOrderResponses(orders);
        return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una orden", description = "Elimina una orden de un cliente con su ID")
    @DeleteMapping(value = "/orders/{order_id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String order_id){
         orderService.deleteOrder(order_id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Operation(summary = "Añade un producto a una orden", description = "Actualiza una orden, añade un producto a la orden de un cliente con su ID")
    @PutMapping(value = "/orders/{order_id}/products/{product_id}/quantity/{quantity}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable String order_id, @PathVariable String product_id, @PathVariable Integer quantity){
        var orderUpdated = orderService.addItemToOrder(order_id, product_id, quantity);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderUpdated);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto de una orden", description = "Elimina un producto de una orden de un cliente con su ID")
    @PatchMapping(value = "/orders/{order_id}/products/{product_id}")
    public ResponseEntity<OrderResponse> removeItemFromOrder(@PathVariable String order_id, @PathVariable String product_id){
        var order = orderService.removeItemFromOrder(order_id, product_id);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(order);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza la cantidad de un producto en una orden", description = "Actualiza la cantidad de un producto en una orden de un cliente con su ID")
    @PatchMapping(value = "/orders/{order_id}/products/{product_id}/quantity/{quantity}")
    public ResponseEntity<OrderResponse> updateItemQuantity(@PathVariable String order_id, @PathVariable String product_id, @PathVariable Integer quantity){
        var order = orderService.updateItemQuantity(order_id, product_id, quantity);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(order);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @Operation(summary = "Limpia la orden", description = "Limpia los productos de una orden de un cliente con su ID")
    @PatchMapping(value = "/orders/{order_id}")
    public ResponseEntity<OrderResponse> clearOrder(@PathVariable String order_id){
        var order = orderService.clearOrder(order_id);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(order);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }


}
