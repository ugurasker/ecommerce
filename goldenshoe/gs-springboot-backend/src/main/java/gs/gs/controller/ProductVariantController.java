package gs.gs.controller;

import gs.gs.model.ProductVariant;
import gs.gs.repositories.ProductVariantRepo;
import gs.gs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/productVariant")
public class ProductVariantController {

    @Autowired
    ProductVariantRepo productVariantRepo;
    @Autowired
    ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductVariant>> getAll(
            @RequestParam(name = "product_id", required = false) Long productId
            , @RequestParam(name = "sold", required = false) Boolean sold
            , @RequestParam(name = "colour", required = false) String colour
            , @RequestParam(name = "size", required = false) Integer size
    ) {
        if (productId != null && sold != null && colour != null && size != null) {
            return new ResponseEntity<>(productVariantRepo.findByProductIdAndSoldAndColourAndSize(productId, sold, colour, size), HttpStatus.OK);
        }
        else if (productId != null && sold !=null) {
            return new ResponseEntity<>(productVariantRepo.findByProductIdAndSold(productId, sold), HttpStatus.OK);
        }
        else if (productId != null) {
            return new ResponseEntity<>(productVariantRepo.findByProductId(productId), HttpStatus.OK);
        }
        return new ResponseEntity<>(productVariantRepo.findAll(), HttpStatus.OK);

    }

    @GetMapping(value = "/colors")
    public Set<String> getColorsOfProduct(
            @RequestParam(name = "product_id", required = false) Long productId) {
            return productService.findColors(productId);
    }
    @GetMapping(value = "/sizes")
    public Set<Integer> getSizeOfProductByColor(
            @RequestParam(name = "product_id", required = false) Long productId,
            @RequestParam(name = "color", required = false) String color) {
            return productService.findSizeByColor(productId,color);
    }
    @GetMapping(value = "/quantity")
    public int getQuantityOfProductByColorAndSize(
            @RequestParam(name = "product_id", required = false) Long productId,
            @RequestParam(name = "size", required = false) int size,
            @RequestParam(name = "color", required = false) String color) {
            return productService.calculateQuantityByColorAndSize(productId,color,size);
    }

    @GetMapping(value = "/productVariant/{id}")

    public ResponseEntity<Optional<ProductVariant>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(productVariantRepo.findById(id), HttpStatus.OK);
    }






}
