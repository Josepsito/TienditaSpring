package com.deconfort.tiendita.service;

import com.deconfort.tiendita.entity.Product;

import java.util.List;

public interface ProductService{

    Product verProducto(int id);
    List<Product> verTodosLosProductos();

    List<Product> verProductosPorIniciales(String iniciales);

    List<Product> verProductosPorCategoria(String categoria);

    List<Product> añadirListaDeProductos(List<Product> products);

    Product crearProducto(Product product);

    Product retirarProducto(int id, int cant);

    boolean eliminarProducto(int id);

    boolean eliminarTodosLosProductos();
}
