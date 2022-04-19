package gs.gs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String description;
    private String category;
    private String imageUrl;
    private Double price;

    @OneToMany()
    @JsonIgnore()
    private List<ProductVariant> productVariant;

    public Product(String brand, String description, String category, String imageUrl, Double price) {
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
        this.productVariant = new ArrayList<>();
    }
}
