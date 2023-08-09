package com.vti.shoppee.modal.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Product")
public class Product extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`",length = 50)
    private String name;

    @Column(name = "image",length = 255)
    private String  image;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipping_unit")
    private ShippingUnit shippingUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;

}
