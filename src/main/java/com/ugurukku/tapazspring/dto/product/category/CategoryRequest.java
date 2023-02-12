package com.ugurukku.tapazspring.dto.product.category;

public record CategoryRequest(Long id) {
    public CategoryRequest() {
    this(0L);
    }
}
