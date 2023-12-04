package Library;

import java.util.Random;

public class RandomLibriry {
	public static void main(String []args) {
		Random rd = new Random() ;
		int int1 = rd.nextInt();
		System.out.println("Số random kiểu Int là " + int1 );
		int int2 = rd.nextInt(6);
		System.out.println("Số random kiểu Int có giới hạn từ [0 - 6) là " + int2 );
		int int3 = rd.nextInt(-6, 16);
		System.out.println("Số random kiểu Int có giới hạn từ [6 - 16) là " + int3 );
		
		float fl1 =rd.nextFloat();
		float fl2 = rd.nextFloat( 5.4f);
		float fl3 = rd.nextFloat(1.1f, 10.2f);
		System.out.println("Số random kiểu float lần luật là  là " + fl1 + " " + fl2 + " " + fl3 );
		
	}
}
