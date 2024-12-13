package com.deconfort.tiendita.controller;

import com.deconfort.tiendita.entity.Sale;
import com.deconfort.tiendita.service.SaleService;
import com.deconfort.tiendita.service.impl.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tiendita")
public class SaleController {

    @Autowired
    private SaleService saleService;


    @GetMapping("/sale/{id}")
    public ResponseEntity<?> verVenta(@PathVariable int id) {

            Sale sale = saleService.verVenta(id);
            return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/sale")
    public ResponseEntity<List<Sale>> verTodasLasVentas() {
        List<Sale> sales = saleService.verTodasLasVentas();

        if (sales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping("/sale/{idProduct}/realizar/{cant}")
    public ResponseEntity<?> realizarVenta(@PathVariable int idProduct, @PathVariable int cant) {

            Sale sale = saleService.realizarVenta(idProduct, cant);
            return new ResponseEntity<>(sale, HttpStatus.OK);


}
}
