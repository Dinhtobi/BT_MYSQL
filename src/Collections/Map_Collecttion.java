package Collections;

import java.util.HashMap;
import java.util.Map;

public class Map_Collecttion {
	public static void main(String [] args) {
		Map<Integer, String> Citys = new HashMap<Integer, String>();
		
		for(int i= 0 ; i < 10 ; i++) {
			Citys.put(i, "City" + i);
		}
		System.out.println(Citys);
		System.out.println("Key Map City ");
		
		for(Integer i : Citys.keySet()) {
			System.out.print(i + " ");
				
		}
		System.out.println("Values Map City ");
		
		for(String i : Citys.values()) {
			System.out.print(i + " ");
				
		}
		System.out.println("Lay entry " + Citys.get(6)) ;
		System.out.println("Xoa entry " + Citys.remove(3));
		Citys.put(6, "Da thay doi");
		System.out.println("Entry 6 Thay the thanh " + Citys.get(6) );
		Citys.replace(6, "Thay doi lan 2");
		System.out.println("Entry 6 Thay the thanh " + Citys.get(6) );
		if(Citys.containsKey(6)) {
			Citys.replace(5, "City5", "City15");
		}
		System.out.println("tim va  Thay the entry 5 thanh " + Citys.get(5) );
		Map<Integer, String> MapCoppy = new HashMap<Integer, String>();
		MapCoppy.putAll(Citys);
		System.out.println("Map sau sao chep " + MapCoppy );
		
	}
}
