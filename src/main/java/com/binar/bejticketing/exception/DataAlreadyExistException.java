package com.binar.bejticketing.exception;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(String username) {
        super("Data with username "+ username + " already exist");
    }
    public DataAlreadyExistException(){

    }
}
