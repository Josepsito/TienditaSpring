package com.deconfort.tiendita.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer id;
    @Column(name = "name_product")
    private String name;
    @Column(name = "description_product")
    private String description;
    @Column(name = "stock_product")
    private Integer stock;
    @Column(name = "price_product")
    private double price;
    @Column(name = "category_product")
    private String category;
}
