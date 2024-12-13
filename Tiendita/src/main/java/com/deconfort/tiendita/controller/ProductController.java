package com.deconfort.tiendita.controller;

import com.deconfort.tiendita.entity.Product;
import com.deconfort.tiendita.service.ProductService;
import com.deconfort.tiendita.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tiendita")
public class ProductController{

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> listarProductoPorId(@PathVariable int id){
        Product product = productService.verProducto(id);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/products/name/{iniciales}")
    public ResponseEntity<?> verProductosPorIniciales(@PathVariable String iniciales){
        List<Product> products = productService.verProductosPorIniciales(iniciales);
        if (products.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos con estas iniciales");
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/category/{categoria}")
    public ResponseEntity<List<Product>> verProductosPorCategoria(@PathVariable String categoria){
        List<Product> products = productService.verProductosPorCategoria(categoria);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listarTodosLosProductos(){

        List<Product> products = productService.verTodosLosProductos();

        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<List<Product>> añadirListaDeProductos(@RequestBody List<Product> products){

        if (products == null || products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Product> savedProducts = productService.añadirListaDeProductos(products);

        return new ResponseEntity<>(savedProducts, HttpStatus.OK);
    }


    @DeleteMapping("/products")
    public ResponseEntity<String> eliminarTodosLosProductos() {
        productService.eliminarTodosLosProductos();
        return ResponseEntity.ok("Todos los productos han sido eliminados.");
    }

}