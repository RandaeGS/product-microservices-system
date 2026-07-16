package com.randaegs.entities;

import com.randaegs.dto.ProductSoldMessage;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "invoices")
public class Invoice extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(name = "product_id")
    public String productId;

    @Column(name = "product_name")
    public String productName;

    @Column(name = "product_price")
    public BigDecimal productPrice;

    @Column(name = "product_amount")
    public Integer productAmount;

    @CreationTimestamp
    @Column(name = "creation_date")
    public Instant creationDate;

    public Invoice(ProductSoldMessage dto) {
        this.productId = dto.id();
        this.productName = dto.name();
        this.productPrice = dto.price();
        this.productAmount = dto.amount();
    }
}
