package com.helix.ecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;
    private String name;
    private String desc;
    private String brand;
    private BigDecimal price;
    private String category;
    private LocalDate releaseDate;
    private boolean available;
    private int quantity;

}
