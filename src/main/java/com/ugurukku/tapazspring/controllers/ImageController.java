
package com.ugurukku.tapazspring.controllers;

import com.ugurukku.tapazspring.entities.ImageData;
import com.ugurukku.tapazspring.services.ImageDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageDataService service;

    public ImageController(ImageDataService service) {
        this.service = service;
    }

    @GetMapping("/info/{productId}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("productId") Long id){
        ImageData image = service.getInfoByProductId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?>  getImageByName(@PathVariable("productId") Long id){
        byte[] image = service.getImage(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }

}
