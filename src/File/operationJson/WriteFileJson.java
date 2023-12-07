package File.operationJson;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.*;
public class WriteFileJson {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("ID", "1");
		json.put("Name", "Dinh");
		json.put("Age", "21");
		json.put("Address", "Quang Tri");
//		 JSONArray employeeList = new JSONArray();
//	        employeeList.add(json);
		try {
			FileWriter file = new FileWriter("G:\\job\\BT_JAVA\\upload\\WriteJSON.json");
			file.write(json.toJSONString());
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}	
