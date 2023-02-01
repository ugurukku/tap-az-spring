package com.ugurukku.tapazspring.entities;

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
}
