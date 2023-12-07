package File;

import java.io.File;
import java.io.IOException;

public class CreateFile {
	public static void main(String[] args) {
		try {
			File file = new File("G:\\Job\\BT_JAVA\\upload\\test.csv");
			boolean check = file.createNewFile();
			
			if(check) {
				System.out.println("File đã được tạo");
			}else {
				System.out.println("File đã có sẵn");
			}
			
		} catch (IOException e) {
			System.out.println("Lỗi tạo file"  + e.getMessage());
		}
	}
}
