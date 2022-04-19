package gs.gs.controller;

import gs.gs.model.Order;
import gs.gs.repositories.OrderRepo;
import gs.gs.repositories.ProductVariantRepo;
import gs.gs.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping(value = "/orders")
public class OrderController<StockHandlerRespo> {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ProductVariantRepo productVariantRepo;

/*    @Autowired
    StockHandlerRespo stockHandlerRespo;*/

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam(value = "user_id", required = false) Long userId) {
        if (userId != null) {
            return new ResponseEntity<>(orderRepo.findOrdersByUserId(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(orderRepo.findAll(), HttpStatus.OK);
    }
/*
    @PostMapping(value = "/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws Exception {

        User user = userRepo.findUserById(order.getUser().getId());
        List<ProductVariant> productVariants = new ArrayList<>();
        for (ProductVariant productVariant : order.getProductVariant()) {
            ProductVariant itemFromStock = productVariantRepo.findProductVariantById(productVariant.getId());
            productVariants.add(itemFromStock);
        }
        StockHandler stockHandler = new StockHandler();
        Order newOrder = stockHandler.makeOrder(user, productVariants);
        orderRepo.save(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }*/

    @PutMapping(value = "/{id}/dispatched")
    public ResponseEntity<Order> updateOrderDispatchedStatus(@RequestBody Order order, @PathVariable Long id) {
        Order orderToUpdate = orderRepo.findById(id).get();
        orderToUpdate.setDispatchedStatus(order.getDispatchedStatus());
        Order updatedOrder = orderRepo.save(orderToUpdate);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/delivered")

    public ResponseEntity<Order> updateOrderDeliveredStatus(@RequestBody Order order, @PathVariable Long id) {
        Order orderToUpdate = orderRepo.findById(id).get();
        orderToUpdate.setDispatchedStatus(order.getDispatchedStatus());
        Order updatedOrder = orderRepo.save(orderToUpdate);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}
