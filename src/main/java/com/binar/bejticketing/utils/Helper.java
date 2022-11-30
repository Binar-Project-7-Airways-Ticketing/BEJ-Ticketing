package com.binar.bejticketing.utils;

import com.binar.bejticketing.dto.ResponseData;

import java.util.List;

public class Helper {
    private static Helper single_instance = null;
    public static Helper getInstance(){
        if (single_instance == null){
            single_instance = new Helper();
        }
        return single_instance;
    }

    public <T> ResponseData<T> resultResponse(T payload, boolean status, List<String> messages){
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setPayload(payload);
        responseData.setMessages(messages);
        responseData.setStatus(status);
        return responseData;
    }
}
