package gs.gs.controller;

import gs.gs.model.StockHandler;
import gs.gs.repositories.StockHandlerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins= "*")
@RequestMapping("/stockHandler")
public class StockHandlerController {
    @Autowired
    StockHandlerRepo stockHandlerRepo;

    @GetMapping(value = "/")

    public ResponseEntity<List<StockHandler>> getAllStockHandlers() {
        return new ResponseEntity<>(stockHandlerRepo.findAll(), HttpStatus.OK);
    }



}
