package com.binar.bejticketing.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(Long id){
        super("The data using id '"+ id + "' does not exists");
    }

    public DataNotFoundException(Long id1, Long id2){
        super("The data using id '"+ id1 + " and using id " + id2 + "' does not exists");
    }

    public DataNotFoundException() {

    }
    public DataNotFoundException(String code){
        super("The data using code '"+ code + "' does not exists");
    }
}
