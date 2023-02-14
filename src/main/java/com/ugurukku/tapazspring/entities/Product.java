package com.ugurukku.tapazspring.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "added_date")
    private LocalDate date;

    @Column(name = "user_email")
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String title, BigDecimal price, String description,String userEmail, Category category, City city) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.userEmail = userEmail;
        this.category = category;
        this.city = city;
        this.date = LocalDate.now();
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Category getCategory() {
        return category;
    }



    public City getCity() {
        return city;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
