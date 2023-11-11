package com.upc.ejerc.Ejercicio.exception;

import jakarta.annotation.Resource;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super();
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
