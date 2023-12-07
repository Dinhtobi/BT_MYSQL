package File.operationCsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class WriteFIleCsv {
	public static String path = "G:\\job\\BT_JAVA\\upload\\write.csv";
	public static void WritteDataAtOnce(String file) throws IOException  {
		FileWriter fileWriter = new FileWriter(file);
		
		CSVWriter csvWriter = new CSVWriter(fileWriter);
		
		String[] header = {"ID" , "Name" ,"GPA"};
		csvWriter.writeNext(header);
		String[] data= {"1" , "Dinh" ,"3.3"};
		csvWriter.writeNext(data);
		csvWriter.close();
	}
	public static void WriteAllData(String file) throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		CSVWriter csvWriter = new CSVWriter(fileWriter, ';', 
                CSVWriter.NO_QUOTE_CHARACTER, 
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                CSVWriter.DEFAULT_LINE_END); 
		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {"ID" ,"Name" ,"GPA"});
		data.add(new String[] {"1","dinh" , "3.6"});
		data.add(new String[] {"2","hung" , "1.2"});
		csvWriter.writeAll(data);
		csvWriter.close();
	}
	public static void main(String[] args) {
		try {
			WritteDataAtOnce(path);
			System.out.println("---------------------------------");
			WriteAllData(path);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
