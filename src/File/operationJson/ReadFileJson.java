package File.operationJson;
import java.io.FileReader;

import org.json.simple.parser.*;
import org.json.simple.*;
public class ReadFileJson {
	public static void main(String[] args) {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader file = new FileReader("G:\\job\\BT_JAVA\\upload\\WriteJSON.json");
			Object object = jsonParser.parse(file);
			System.out.println(object);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
