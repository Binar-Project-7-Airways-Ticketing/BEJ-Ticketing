package com.binar.bejticketing.exception;

import com.binar.bejticketing.utils.AgeCategoryName;

import javax.validation.constraints.NotBlank;

public class DataAlreadyExistException extends RuntimeException{
    public DataAlreadyExistException(@NotBlank(message = "Name Category shouldn't Blank") AgeCategoryName username) {
        super("Data with username "+ username + " already exist");
    }
    public DataAlreadyExistException(){

    }
}
