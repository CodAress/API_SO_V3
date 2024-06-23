package upc.edu.NerdyNestAPI.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import upc.edu.NerdyNestAPI.user.model.Client;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    @Field(name = "client_id")
    private Client client;
    @Field(name = "order_date")
    private LocalDateTime orderDate;
    @Field(name = "order_update")
    private LocalDateTime orderUpdate;
    @Field(name = "cart_id")
    private String cartId;
    @Field(name = "items")
    private List<OrderItem> items;
}
