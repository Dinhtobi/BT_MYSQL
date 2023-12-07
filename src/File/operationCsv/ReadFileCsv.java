package File.operationCsv;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class ReadFileCsv {
	
	public static String path = "G:\\job\\BT_JAVA\\upload\\read.csv";
	
	public static void read1(String file) throws CsvValidationException, IOException {
		FileReader fileReader = new FileReader(file);
		CSVReader csvReader = new CSVReader(fileReader);
		
		String[] nextRecord;
		
		while((nextRecord = csvReader.readNext()) != null) {
			for(String cell : nextRecord) {
				System.out.print(cell +" \t");
			}
			System.out.println();
		}
	}
	
	public static void read2(String file) throws IOException, CsvException {
		FileReader fileReader = new FileReader(file);
	
		CSVReader csvReader2 = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
		List<String[]> allData = csvReader2.readAll();
		for(String[] row : allData) {
			for(String cell : row) {
				System.out.print(cell +" \t");
			}
			System.out.println();	
		}

	}
	public static void read3(String file) throws IOException, CsvException {
		FileReader fileReader = new FileReader(file);
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		CSVReader csvReader3 = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();
		List<String[]> alldata = csvReader3.readAll();
		
		for(String[] row : alldata) {
			for(String cell : row) {
				System.out.print(cell + "\t");
			}
			System.out.println();
		
		}
	}
	public static void main(String [] args) {
		try {
			read1(path);
			System.out.println("----------------------");
			read2(path);
			System.out.println("----------------------");
			read3(path);
		
 		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
