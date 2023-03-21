package edu.itstep.restapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private int id;

    private String name;

    private String ingredients;

    private String photo;

    @Column(name = "price")
    private BigDecimal smallPrice;

    private BigDecimal mediumPrice;

    private BigDecimal largePrice;

    public Pizza(int id, String name, String ingredients, String photo, BigDecimal smallPrice) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.photo = photo;
        this.smallPrice = smallPrice;
        mediumPrice = smallPrice.multiply(new BigDecimal("1.5"));
        largePrice = smallPrice.multiply(new BigDecimal("2"));
        System.out.println("pizza full ctor");
    }
}
