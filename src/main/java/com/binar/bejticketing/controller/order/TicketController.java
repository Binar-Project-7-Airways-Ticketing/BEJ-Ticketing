package com.binar.bejticketing.controller.order;

import com.binar.bejticketing.dto.ResponseData;
import com.binar.bejticketing.dto.TicketDto;
import com.binar.bejticketing.service.BookingService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/{idBooking}")
    public ResponseEntity<ResponseData<String>> getBookingForTicket(@PathVariable("idBooking") Long idBooking){
        JRBeanArrayDataSource jrBeanArrayDataSource = new JRBeanArrayDataSource(new TicketDto[]{bookingService.getBookingForTicket(idBooking)});
        try {
            System.out.println(jrBeanArrayDataSource.getData().length);
            JasperReport compileManager =  JasperCompileManager.compileReport(new FileInputStream("src/main/resources/template/ticket.jrxml"));
            HashMap<String, Object> map = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileManager, map, jrBeanArrayDataSource);
            System.out.println(jasperPrint);
            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(
                    new SimpleOutputStreamExporterOutput("Ticket.pdf"));

            SimplePdfReportConfiguration reportConfig
                    = new SimplePdfReportConfiguration();
            reportConfig.setSizePageToContent(true);
            reportConfig.setForceLineBreakPolicy(false);

            SimplePdfExporterConfiguration exportConfig
                    = new SimplePdfExporterConfiguration();
            exportConfig.setMetadataAuthor("Fathan");
            exportConfig.setEncrypted(true);
            exportConfig.setAllowedPermissionsHint("PRINTING");

            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exportConfig);

            exporter.exportReport();
            ResponseData<String> responseData = new ResponseData<>();
            responseData.setStatus(true);
            responseData.setPayload("Generated");
            return ResponseEntity.ok(responseData);
        } catch (JRException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
