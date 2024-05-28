package com.example.PAOProiect.audit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Service
public class AuditService {

    private static final String AUDIT_FILE = "audit_log.csv";

    public void logMethodCall(String methodName, String[] params) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AUDIT_FILE, true));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Timestamp", "Method", "Parameters"))) {

            csvPrinter.printRecord(LocalDateTime.now(), methodName, String.join(", ", params));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
