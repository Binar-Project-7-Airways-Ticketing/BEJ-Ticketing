package com.binar.bejticketing.service.order;

import com.binar.bejticketing.entity.History;
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
    @Override
    public History addHistory(History history) {
        return historyRepository.saveAndFlush(history);
    }

    @Override
    public History updateHistory(Long idBooking, boolean state) {
        Optional<History> historyChecking = historyRepository.findById(idBooking);
        if (historyChecking.isEmpty()){
            throw new EntityNotFoundException();
        }
        historyChecking.get().getBooking().getBookingDetails().setStatePricing(state);
        return historyRepository.saveAndFlush(historyChecking.get());
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
