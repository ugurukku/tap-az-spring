package com.ugurukku.tapazspring.dto.product;

public record CategoryRequest(Long id) {
    public CategoryRequest() {
    this(0L);
    }
}
