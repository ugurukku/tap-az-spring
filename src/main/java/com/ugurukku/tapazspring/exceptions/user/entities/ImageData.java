package com.ugurukku.tapazspring.exceptions.user.entities;

import javax.persistence.*;

@Entity
@Table(name = "imageData")
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

    public ImageData() {
    }

    public ImageData(Long productId, String type, byte[] imageData) {
        this.productId = productId;
        this.type = type;
        this.imageData = imageData;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getType() {
        return type;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
