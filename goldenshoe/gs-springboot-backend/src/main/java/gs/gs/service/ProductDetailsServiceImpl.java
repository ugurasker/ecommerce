package gs.gs.service;

import gs.gs.model.ProductVariant;
import gs.gs.repositories.ProductVariantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductDetailsServiceImpl implements ProductService{

    @Autowired
    ProductVariantRepo productVariantRepo;

    @Override
    public Set<String> findColors(long ProductId) {
        Set<String> colors = new HashSet<>();
        List<ProductVariant> productsDetails = productVariantRepo.findByProductId(ProductId);
        productsDetails.forEach(p -> {
            colors.add(p.getColour());
        });
        return colors;
    }

    @Override
    public Set<Integer> findSizeByColor(long ProductId, String color) {
        Set<Integer> sizes = new HashSet<>();
        List<ProductVariant> productsDetails = productVariantRepo.findByProductIdAndColour(ProductId, color);
        productsDetails.forEach(p -> {
            sizes.add(p.getSize());
        });
        return sizes;
    }

    @Override
    public int calculateQuantityByColorAndSize(long ProductId, String color, int size) {
        int quantity = 0;
        List<ProductVariant> productsDetails = productVariantRepo.findByProductIdAndColourAndSize(ProductId, color, size);
        for (int i = 0; i < productsDetails.size() ; i++) {
                quantity += productsDetails.get(i).getQuantity();
        }
        return quantity;
    }
}
