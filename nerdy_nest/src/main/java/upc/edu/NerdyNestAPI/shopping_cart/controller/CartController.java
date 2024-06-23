package upc.edu.NerdyNestAPI.shopping_cart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.shopping_cart.dto.CartRequest;
import upc.edu.NerdyNestAPI.shopping_cart.dto.CartResponse;
import upc.edu.NerdyNestAPI.shopping_cart.dto.mapper.CartMapper;
import upc.edu.NerdyNestAPI.shopping_cart.service.CartService;

@Tag(name = "Gestión de Carritos", description = "Controlador para operaciones relacionadas con carritos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class CartController {
    @Autowired
    private CartService cartService;
    @Operation(summary = "Crea un nuevo carrito de compras", description = "Crea un nuevo carrito de compras para un cliente con su ID")
    @PostMapping(value = "/clients/{client_id}/cart")
    public ResponseEntity<CartResponse> createCart(@PathVariable String client_id){
        var cartCreated = cartService.createCart(client_id);
        var cartResponse = CartMapper.INSTANCE.cartToCartResponse(cartCreated);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene el carrito de compras", description = "Obtiene el carrito de compras de un cliente con su ID")
    @GetMapping(value = "/clients/{client_id}/cart")
    public ResponseEntity<CartResponse> getCart(@PathVariable String client_id){
        var cart = cartService.getCart(client_id);
        var cartResponse = CartMapper.INSTANCE.cartToCartResponse(cart);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    @Operation(summary = "Añade un item al carrito de compras", description = "Añade un item al carrito de compras de un cliente con su ID")
    @PostMapping(value = "/clients/{client_id}/cart/items/{product_id}/quantity/{quantity}")
    public ResponseEntity<CartResponse> addItemToCart(@PathVariable String client_id, @PathVariable String product_id, @PathVariable Integer quantity){
        //Integer quantity = cartRequest.getQuantity();
        //System.out.println(cartRequest);
        var cart = cartService.addItemToCart(client_id, product_id, quantity);
        var cartResponse = CartMapper.INSTANCE.cartToCartResponse(cart);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un item del carrito de compras", description = "Elimina un item del carrito de compras de un cliente con su ID")
    @PatchMapping(value = "/clients/{client_id}/cart/items/{product_id}")
    public ResponseEntity<CartResponse> removeItemFromCart(@PathVariable String client_id, @PathVariable String product_id){
        var cart = cartService.removeItemFromCart(client_id, product_id);
        var cartResponse = CartMapper.INSTANCE.cartToCartResponse(cart);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza la cantidad de un item en el carrito de compras", description = "Actualiza la cantidad de un item en el carrito de compras de un cliente con su ID")
    @PutMapping(value = "/clients/{client_id}/cart/items/{product_id}/quantity/{quantity}")
    public ResponseEntity<CartResponse> updateItemQuantity(@PathVariable String client_id, @PathVariable String product_id, @RequestBody CartRequest cartRequest, @PathVariable Integer quantity){
        var cart = cartService.updateItemQuantity(client_id, product_id, quantity);
        var cartResponse = CartMapper.INSTANCE.cartToCartResponse(cart);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    @Operation(summary = "Limpia el carrito de compras", description = "Limpia el carrito de compras de un cliente con su ID")
    @PatchMapping(value = "/clients/{client_id}/cart")
    public ResponseEntity<CartResponse> clearCart(@PathVariable String client_id){
        var cart = cartService.clearCart(client_id);
        var cartResponse = CartMapper.INSTANCE.cartToCartResponse(cart);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina el carrito de compras", description = "Elimina el carrito de compras de un cliente con su ID")
    @DeleteMapping(value = "/clients/{client_id}/cart")
    public ResponseEntity<Void> deleteCart(@PathVariable String client_id){
        cartService.deleteCart(client_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
