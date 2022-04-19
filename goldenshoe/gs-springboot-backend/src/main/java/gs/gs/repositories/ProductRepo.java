package gs.gs.repositories;

import gs.gs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findProductsByCategory(String category);

    List<Product> findProductByBrand(String brand);



}
