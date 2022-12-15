package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.dto.TicketDto;
import com.binar.bejticketing.service.BookingService;
import com.binar.bejticketing.utils.GeneratePdf;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "/{idBooking}",produces = MediaType.APPLICATION_PDF_VALUE,consumes = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getBookingForTicket(@PathVariable("idBooking") Long idBooking) throws JRException, FileNotFoundException {
        JRBeanArrayDataSource jrBeanArrayDataSource = new JRBeanArrayDataSource(new TicketDto[]{bookingService.getBookingForTicket(idBooking)});

            var generatePDF = new GeneratePdf();
            byte[] data = generatePDF.generatePdf(jrBeanArrayDataSource);

            var headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename=ticket.pdf");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }
}
