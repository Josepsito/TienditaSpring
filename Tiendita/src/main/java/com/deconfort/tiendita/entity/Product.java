package com.deconfort.tiendita.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
    @Column(name = "name_product", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
    @Column(name = "description_product", nullable = false, length = 500)
    private String description;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name = "stock_product", nullable = false)
    private Integer stock;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Column(name = "price_product", nullable = false)
    private double price;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no debe superar los 50 caracteres")
    @Column(name = "category_product", nullable = false, length = 50)
    private String category;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    @Size(max = 255, message = "La URL de la imagen no debe superar los 255 caracteres")
    @Column(name = "image_url_product", nullable = false, length = 255)
    private String imageUrl;

    @DecimalMin(value = "0.0", message = "El descuento no puede ser negativo")
    @DecimalMax(value = "1.0", message = "El descuento no puede ser mayor al 100%")
    @Column(name = "discount_product")
    private Double discount;

    @NotNull(message = "El estado activo es obligatorio")
    @Column(name = "active_product", nullable = false)
    private Boolean active;

    @PastOrPresent(message = "La fecha de creación no puede ser futura")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PastOrPresent(message = "La fecha de actualización no puede ser futura")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no debe superar los 50 caracteres")
    @Column(name = "brand_product", nullable = false, length = 50)
    private String brand;
}
