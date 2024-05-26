package com.example.PAOProiect.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
    public List<String[]> readAllFromCsv(String path, String[] HEADERS) throws IOException {
        try (Reader reader = new FileReader(path)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(HEADERS)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> parser = csvFormat.parse(reader);
            List<String[]> rows = new ArrayList<>();
            for (CSVRecord record : parser) {
                rows.add(record.values());
            }
            return rows;
        }
    }

    public void writeToCsv(String path, List<String[]> rows) throws IOException {
        try (Writer writer = new FileWriter(path, true);
             final CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            for (String[] row : rows) {
                printer.printRecord(row);
            }
            printer.flush();
        }
    }
}
