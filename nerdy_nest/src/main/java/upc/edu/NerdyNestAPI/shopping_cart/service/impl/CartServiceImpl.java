package upc.edu.NerdyNestAPI.shopping_cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upc.edu.NerdyNestAPI.inventario.model.Product;
import upc.edu.NerdyNestAPI.inventario.repository.ProductRepository;
import upc.edu.NerdyNestAPI.shopping_cart.model.Cart;
import upc.edu.NerdyNestAPI.shopping_cart.model.CartItem;
import upc.edu.NerdyNestAPI.shopping_cart.repository.CartRepository;
import upc.edu.NerdyNestAPI.shopping_cart.service.CartService;
import upc.edu.NerdyNestAPI.user.repository.ClientRepository;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart createCart(String clientId) {
        notExistClientById(clientId);
        existCartByClientId(clientId);
        Cart cart = Cart.builder()
                .clientId(clientId)
                .items(null)
                .build();
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(String clientId) {
        notExistClientById(clientId);
        notExistCartByClientId(clientId);
        return cartRepository.findByClientId(clientId);
    }
    @Transactional
    @Override
    public Cart addItemToCart(String clientId, String productId, Integer quantity) {
        notExistClientById(clientId);
        notExistProductById(productId);
        notExistCartByClientId(clientId);
        validateQuantity(quantity);
        quantityNotAvailable(productId, quantity);

        Cart cart = cartRepository.findByClientId(clientId);

        if(cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        existProductInCartByProductId(cart, productId);
        cart.getItems().add(CartItem.builder()
                .productId(productId)
                .quantity(quantity)
                .build());

        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public Cart removeItemFromCart(String clientId, String productId) {
        notExistClientById(clientId);
        notExistProductById(productId);
        notExistCartByClientId(clientId);
        Cart cart = cartRepository.findByClientId(clientId);
        notExistProductInCartByClientId(cart, productId);
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public Cart updateItemQuantity(String clientId, String productId, Integer quantity) {
        notExistClientById(clientId);
        notExistProductById(productId);
        notExistCartByClientId(clientId);
        validateQuantity(quantity);
        Cart cart = cartRepository.findByClientId(clientId);
        notExistProductInCartByClientId(cart, productId);
        quantityNotAvailable(productId, quantity);
        cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .forEach(item -> item.setQuantity(quantity));
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public Cart clearCart(String clientId) {
        notExistClientById(clientId);
        notExistCartByClientId(clientId);
        Cart cart = cartRepository.findByClientId(clientId);
        cart.setItems(null);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(String clientId) {
        notExistClientById(clientId);
        notExistCartByClientId(clientId);
        cartRepository.deleteByClientId(clientId);
    }

    private void notExistClientById(String clientId) {
        if(!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException(String.format("Client with id %s not found", clientId));
    }
    private void notExistProductById(String productId) {
        if(!productRepository.existsById(productId))
            throw new ResourceNotFoundException(String.format("Product with id %s not found", productId));
    }
    private void validateQuantity(Integer quantity) {
        if(quantity <= 0)
            throw new ResourceNotFoundException("Quantity must be greater than 0");
    }
    private void notExistCartByClientId(String clientId) {
        if(!cartRepository.existsByClientId(clientId))
            throw new ResourceNotFoundException(String.format("Cart with client id %s not found", clientId));
    }
    private void quantityNotAvailable(String productId, Integer quantity) {
        Product product = productRepository.findById(productId).get();
        if(product.getStock() < quantity)
            throw new ResourceNotFoundException(String.format("Product with id %s does not have enough stock", productId));
    }
    private void notExistProductInCartByClientId(Cart cart, String productId) {
        if(cart.getItems().stream().noneMatch(item -> item.getProductId().equals(productId)))
            throw new ResourceNotFoundException(String.format("Product with id %s not found in cart", productId));
    }
    private void existCartByClientId(String clientId) {
        if(cartRepository.existsByClientId(clientId))
            throw new ResourceNotFoundException(String.format("Cart with client id %s already exists", clientId));
    }
    private void existProductInCartByProductId(Cart cart, String productId) {
        if(cart.getItems().stream().anyMatch(item -> item.getProductId().equals(productId)))
            throw new ResourceNotFoundException(String.format("Product with id %s already exists in cart", productId));
    }
}
