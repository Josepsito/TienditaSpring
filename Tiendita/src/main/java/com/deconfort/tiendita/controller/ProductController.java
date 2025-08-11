package com.deconfort.tiendita.controller;

import com.deconfort.tiendita.entity.Product;
import com.deconfort.tiendita.service.ProductService;
import com.deconfort.tiendita.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/v1/tiendita/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("id/{id}")
    public ResponseEntity<Product> obtenerProductoPorId(@PathVariable int id) {
        Product product = productService.verProducto(id);
        return (product != null)
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("nombre/{nombre}")
    public ResponseEntity<Product> obtenerProductoPorNombre(@PathVariable String nombre) {
        Product product = productService.buscarProductoPorNombre(nombre);
        return (product != null)
                ? ResponseEntity.ok(product)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("buscar/{iniciales}")
    public ResponseEntity<List<Product>> verProductosPorIniciales(@PathVariable String iniciales) throws IOException {
        List<Product> products = productService.buscarProductosPorIniciales(iniciales);
        return products.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(products);
    }

    @GetMapping("category/{categoria}")
    public ResponseEntity<List<Product>> verProductosPorCategoria(@PathVariable String categoria) {
        List<Product> products = productService.verProductosPorCategoria(categoria);
        return products.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(products);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> listarTodosLosProductos() {
        List<Product> products = productService.verTodosLosProductos();
        return products.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(products);
    }

    @PostMapping("")
    public ResponseEntity<List<Product>> añadirListaDeProductos(@RequestBody List<Product> products) throws IOException {
        if (products == null || products.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Product> savedProducts = productService.añadirListaDeProductos(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProducts);
    }

    @DeleteMapping("")
    public ResponseEntity<String> eliminarTodosLosProductos() {
        productService.eliminarTodosLosProductos();
        return ResponseEntity.ok("Todos los productos han sido eliminados.");
    }
}
