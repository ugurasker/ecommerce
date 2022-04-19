package gs.gs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity

public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer size;
    private String colour;
    private Boolean sold;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore()
    private Product product;

    @ManyToOne
    @JsonIgnore()

    private Order order;

    @ManyToOne
    @JsonIgnore()
    private StockHandler stockHandler;

    public ProductVariant(Integer size,Integer quantity, String colour, Product product, StockHandler stockHandler) {
        this.size = size;
        this.quantity = quantity;
        this.colour = colour;
        this.product = product;
        this.stockHandler = stockHandler;
        this.sold = false;
        this.order = null;

    }
}
