package com.binar.bejticketing.service.order;

import com.binar.bejticketing.repository.SeatRepository;
import com.binar.bejticketing.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatRepository seatRepository;
}
