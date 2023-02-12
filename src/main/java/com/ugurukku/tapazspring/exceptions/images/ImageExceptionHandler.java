package com.ugurukku.tapazspring.exceptions.images;

import com.ugurukku.tapazspring.exceptions.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ImageExceptionHandler {

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<?> imageNotFoundExceptionHandler(ImageNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
