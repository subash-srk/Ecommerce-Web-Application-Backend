package com.EcommerceWeb.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException(String field, String resourceName, String fieldName) {
        this.field = field;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s %s", resourceName, field, fieldId));
        this.field = field;
        this.resourceName = resourceName;
        this.fieldId = fieldId;
    }

    public ResourceNotFoundException() {
    }
}
