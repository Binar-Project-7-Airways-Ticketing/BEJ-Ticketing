package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.entity.History;
import com.binar.bejticketing.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping
    public ResponseEntity<ResponseData<List<History>>> getAllHistory(){
        ResponseData<List<History>> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(historyService.getAllHistory());
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<History>> getHistoryById(@PathVariable("id") Long id){
        ResponseData<History> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setPayload(historyService.getHistoryById(id));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData<History>> getHistoryById(@Valid @RequestBody History history,
                                                                Errors errors){
        ResponseData<History> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(historyService.addHistory(history));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/edit/{id-booking}/{state}")
    public ResponseEntity<ResponseData<History>> editHistory(@PathVariable("id-booking")Long idBooking,
                                                             @PathVariable("state") boolean state){
        ResponseData<History> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(historyService.updateHistory(idBooking, state));
        return ResponseEntity.ok(responseData);
    }
}
