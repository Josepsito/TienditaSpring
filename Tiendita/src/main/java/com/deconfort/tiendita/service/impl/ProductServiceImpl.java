package com.deconfort.tiendita.service.impl;

import com.deconfort.tiendita.entity.Product;
import com.deconfort.tiendita.repository.ProductRepository;
import com.deconfort.tiendita.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product verProducto(int id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Product> verTodosLosProductos() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> verProductosPorIniciales(String iniciales) {
        return productRepository.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(iniciales.toLowerCase()))
                .sorted(Comparator.comparing( product->
                        product.getName().toLowerCase().startsWith(iniciales.toLowerCase()) ? 0 : 1))
                .collect(Collectors.toList());
    }


    @Override
    public List<Product> verProductosPorCategoria(String categoria) {
        return productRepository.findByCategory(categoria);
    }


    @Override
    @Transactional
    public List<Product> añadirListaDeProductos(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    @Transactional
    public Product crearProducto(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product retirarProducto(int id, int cant) {

        if (cant <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        return productRepository.findById(id)
                .map(product -> {
                    if (cant > product.getStock()) {
                        throw new RuntimeException("No hay suficiente stock");
                    }
                    product.setStock(product.getStock() - cant);
                    return productRepository.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    @Transactional
    public boolean eliminarProducto(int id) {
        try{
            productRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional
    public boolean eliminarTodosLosProductos() {
        try{
            productRepository.deleteAll();
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
