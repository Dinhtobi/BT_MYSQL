package File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	
	public static void ghinoifile() {
		try {
			File file = new File("G:\\Job\\BT_JAVA\\upload\\writefile.txt");
			String message ="to be continue";
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(message);
			bw.close();
			System.out.println("Nối file thành công");
		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
		}
	}
	
	public static void main(String [] args) {
		FileOutputStream fo = null ;
		File file = new File("G:\\Job\\BT_JAVA\\upload\\writefile.txt");
		String message = "Today is 2023/12/4";
		try {
			fo = new FileOutputStream(file);
			byte[] bt = message.getBytes();
			fo.write(bt);
			fo.flush();
			System.out.println("Write Successfully");
			ghinoifile();
		}
		catch (IOException e) {
			System.out.print("Error :" + e.getMessage());
		}finally {
			try {
				if(fo != null) {
					fo.close();
				}
			} catch (IOException  e2) {
				System.out.println("Error close IO: " + e2.getMessage());
			}
		}
	}
}
