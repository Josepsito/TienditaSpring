package com.deconfort.tiendita.service;

import com.deconfort.tiendita.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService{

    Product verProducto(int id);
    List<Product> verTodosLosProductos();


    Product buscarProductoPorNombre(String nombre);

    List<Product> buscarProductosPorIniciales(String iniciales) throws IOException;

    List<Product> verProductosPorCategoria(String categoria);

    List<Product> añadirListaDeProductos(List<Product> products) throws IOException;

    Product crearProducto(Product product) throws IOException;

    Product retirarProducto(int id, int cant) throws IOException;

    boolean eliminarProducto(int id) throws IOException;

    boolean eliminarTodosLosProductos();
}
