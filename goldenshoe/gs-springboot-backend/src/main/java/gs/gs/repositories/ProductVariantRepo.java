package gs.gs.repositories;

import gs.gs.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepo  extends JpaRepository<ProductVariant, Long> {

    List<ProductVariant> findByProductId(Long productId);
    List<ProductVariant> findByProductIdAndColour(Long productId, String color);
    List<ProductVariant> findByProductIdAndColourAndSize(Long productId, String color, int size);

    List<ProductVariant> findBySold (Boolean sold);
    List<ProductVariant> findByProductIdAndSold(Long shoeTypeId, Boolean sold);

    List<ProductVariant> findByProductIdAndSoldAndColourAndSize(Long productId, Boolean soldStatus, String colour, Integer size);
}
