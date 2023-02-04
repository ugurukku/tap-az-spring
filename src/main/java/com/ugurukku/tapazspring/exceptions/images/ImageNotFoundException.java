package com.ugurukku.tapazspring.exceptions.images;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String message) {
        super(message);
    }
}
