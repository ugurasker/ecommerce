package gs.gs.controller;

import gs.gs.model.Product;
import gs.gs.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepo productRepo;

    @GetMapping(value = "/all")
    public List<Product> getAllProducts(
            @RequestParam(name = "category", required = false) String category
            , @RequestParam(name = "brand", required = false) String brand) {
        if (brand != null && category == null) {
            return productRepo.findProductByBrand(brand);
        }
        else if (category != null) {
            return productRepo.findProductsByCategory(category);
        }
        return productRepo.findAll();
    }
}
