package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.Booking;
import com.binar.bejticketing.entity.History;
import com.binar.bejticketing.repository.BookingRepository;
import com.binar.bejticketing.repository.HistoryRepository;
import com.binar.bejticketing.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public History addHistory(History history) {
        return historyRepository.saveAndFlush(history);
    }

    @Override
    public History updateHistory(Long idHistory, Long idBooking, boolean state) {
        Optional<Booking> bookingChecking = bookingRepository.findById(idBooking);
        Optional<History> history = historyRepository.findById(idHistory);
        if (bookingChecking.isEmpty()){
            throw new EntityNotFoundException();
        }
        history.get().getBooking().getBookingDetails().setStatePricing(state);
        return historyRepository.saveAndFlush(history.get());
    }

    @Override
    public List<History> getAllHistory() {
        return historyRepository.findAll();
    }

    @Override
    public History getHistoryById(Long id) {
        return historyRepository.findById(id).get();
    }
}
