package BT_RenLuyen;

import java.util.HashMap;
import java.util.Map;

public class BT_Chuoi {
	
	private static String str = "nguyen Van Sinh Dinh";
	
	private static String bt1(String str) {	
		String str2 = str.replaceAll(" ","");
		return str2;
	}
	
	
	private static String bt2(String str) {
		String str1 = str.substring(0, 1);
		str1 =str1.toUpperCase();
		String str2 = str.substring(1, str.length());
		return str1 + str2;
	}
	
	private static String bt3(String str) {
		char [] Upper = str.toCharArray();
		for(int i = 0 ; i < Upper.length ; i++) {
			if(Upper[i] >= 97 && Upper[i] <= 122) {
				Upper[i] -= 32;
			}
		}
		String newstr = String.valueOf(Upper);
		return newstr;
	}
	
	private static void bt4(String str) {
		char [] chars = str.toCharArray();
		Map<Character, Integer>  maps = new HashMap<Character, Integer>();
		
		for(Character c : chars) {
			if(maps.containsKey(c)) {
				maps.put(c, maps.get(c) + 1);
			}else {
				maps.put(c, 1);
			}
		}
		maps.forEach((Char,Number) -> System.out.print("Ky tu " + Char + " có " + Number + "\n"));
	}
	
	private static boolean bt5(String str) {
		char [] chars = str.toCharArray();
		String daochuoi = "";
		for(int i = chars.length -1  ; i >= 0 ; i--) {
			daochuoi += String.valueOf(chars[i]);
		}
		if(str.equals(daochuoi)) {
			return true;
		}
		return false;
	}
	
	public static void main(String []args) {
		
		//bt1 
		System.out.println(bt1(str));
		//bt2 
		System.out.println(bt2(str));
		//bt3
		System.out.println(bt3(str));
		//bt4
		bt4(str);
		if(bt5("abccba")) {
			System.out.println("Doi xung");
				
		}else {
			System.out.println("Khong doi xung");
			
		}
	}
}
