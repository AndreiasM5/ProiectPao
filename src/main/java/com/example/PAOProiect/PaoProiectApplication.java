package com.example.PAOProiect;

import com.example.PAOProiect.csv.CsvService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PaoProiectApplication implements CommandLineRunner {
	CsvService csvService = new CsvService();

	public static void main(String[] args) {
		SpringApplication.run(PaoProiectApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		String path = "src\\main\\resources\\data\\download.csv";
		List<String[]> rows = new ArrayList<>();
		rows.add(new String[]{"John", "11"});
		rows.add(new String[]{"Jane Smith", "10"});
		rows.add(new String[]{"Tom Johnson", "22"});
		csvService.writeToCsv(path, rows);
	}
}
