package com.corredor.empleo.exception;

public class ResourceNotFoundException extends RuntimeException{

public ResourceNotFoundException(String mensaje){
super(mensaje);
}

}