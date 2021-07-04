package com.example.hospital.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(Long id) {
        super("Entity with id: " + id + " couldn't be found");
    }
}
