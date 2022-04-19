package gs.gs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
    @Getter
    @Setter
    @NoArgsConstructor
    @Entity

    public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToMany()
        private List<ProductVariant> productVariant;

        @ManyToOne
        private User user;

        private LocalDate orderDate;
        private LocalDate etaDelivery;
        private Double totalCost;
        private Boolean dispatchedStatus;
        private Boolean deliveredStatus;

        public Order(List<ProductVariant> productVariant, User user, LocalDate orderDate, LocalDate etaDelivery, Double totalCost) {
            this.productVariant = productVariant;
            this.user = user;
            this.orderDate = orderDate;
            this.etaDelivery = etaDelivery;
            this.totalCost = totalCost;
            this.dispatchedStatus = false;
            this.deliveredStatus = false;

        }

    }
