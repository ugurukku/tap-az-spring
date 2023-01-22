package com.ugurukku.tapazspring.repositories;


import java.math.BigDecimal;

public class ProductFromConsumer {

    private Long id;

    private String title;

    private BigDecimal price;

    private String description;


    private String category;

    private String image;

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

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "ProductFromConsumer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
