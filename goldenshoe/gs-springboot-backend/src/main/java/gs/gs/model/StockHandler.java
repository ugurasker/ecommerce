package gs.gs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class StockHandler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany()
    private List<ProductVariant> productVariants;

    public StockHandler(String name) {
        this.name = name;
        this.productVariants = new ArrayList<>();
    }
    public void sellStock(ProductVariant productVariant) {
        productVariant.setSold(true);

    }

    public void addStock(ProductVariant productVariant) {
        this.productVariants.add(productVariant);
        productVariant.setStockHandler(this);
    }

    public Order makeOrder(User user, List<ProductVariant> items) throws Exception {

        ArrayList<ProductVariant> itemsInStock = new ArrayList<>();
        Order order = new Order(itemsInStock, user, LocalDate.now(), LocalDate.now().plusDays(3), 0.00);

        for (ProductVariant productVariant : items) {
            if (!productVariant.getSold()) {
                this.sellStock(productVariant);
                itemsInStock.add(productVariant);
                productVariant.setOrder(order);
                order.setTotalCost(order.getTotalCost() + productVariant.getProduct().getPrice());
            }
        }
        if (itemsInStock.size() > 0) {
            return order;
        } else throw new Exception("Order not created");
    }

}
