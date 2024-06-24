package org.aplicacaobancariaapi.course.servicies.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Object id) {
        super("Resource not found: " + id);
    }
}
