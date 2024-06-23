package upc.edu.NerdyNestAPI.order.model;

import lombok.Builder;
import lombok.Data;
import upc.edu.NerdyNestAPI.inventario.model.Product;

@Data
@Builder
public class OrderItem {
    private Product productId;
    private Integer quantity;
}
