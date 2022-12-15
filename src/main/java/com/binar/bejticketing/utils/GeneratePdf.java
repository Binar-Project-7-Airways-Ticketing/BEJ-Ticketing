package com.binar.bejticketing.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class GeneratePdf {

    public byte[] generatePdf(JRBeanArrayDataSource jrBeanArrayDataSource) throws JRException, FileNotFoundException {
        JasperReport compileManager =  JasperCompileManager.compileReport(new FileInputStream("src/main/resources/template/ticket.jrxml"));
        HashMap<String, Object> map = new HashMap<>();
        JasperPrint report = JasperFillManager.fillReport(compileManager, map, jrBeanArrayDataSource);
        return JasperExportManager.exportReportToPdf(report);
    }
}
