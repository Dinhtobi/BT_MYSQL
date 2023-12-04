package File;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile {
	public static void main(String[] args) {
		File file = new File("G:\\Job\\BT_JAVA\\upload\\test.txt");
		FileInputStream filein = null ;
		BufferedInputStream bf = null ;
		
		try {
			filein = new FileInputStream(file);
			
			bf = new BufferedInputStream(filein);
			while(bf.available()> 0) {	
				System.out.print((char) bf.read());
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error file" + fnfe);
		} 
		catch (IOException ioe) {
			System.out.println("Error: " + ioe);
		}
		finally {
			try {
				if(filein != null && bf != null) {
					filein.close();
					bf.close();
				}
			} catch (IOException e) {
				System.out.println("Error close IO: " + e);
			}
		}
	}
}
