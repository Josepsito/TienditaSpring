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
    public ResponseEntity<Sale> verVenta(@PathVariable int id) {
        Sale sale = saleService.verVenta(id);
        return (sale != null)
                ? ResponseEntity.ok(sale)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/sale")
    public ResponseEntity<List<Sale>> verTodasLasVentas() {
        List<Sale> sales = saleService.verTodasLasVentas();
        return sales.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(sales);
    }

    @PostMapping("/sale/{idProduct}/realizar/{cant}")
    public ResponseEntity<Sale> realizarVenta(@PathVariable int idProduct, @PathVariable int cant) {
        Sale sale = saleService.realizarVenta(idProduct, cant);
        return (sale != null)
                ? ResponseEntity.status(HttpStatus.CREATED).body(sale)
                : ResponseEntity.badRequest().build();
    }
}