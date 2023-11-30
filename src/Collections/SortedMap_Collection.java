package Collections;

import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMap_Collection {
	public static void main(String []args) {
		SortedMap<Integer,String> sortMap = new TreeMap<Integer, String>();
		
		for(int i =0 ; i< 10 ; i++) {
			sortMap.put(i,"Home" + i);
		}
		 System.out.println("Map: " );
		sortMap.forEach((Key, value) -> System.out.println("Key  " + Key + "  ,Value  " + value ));
		
		 System.out.println("SubMap: " );
		SortedMap<Integer, String> SubMap = sortMap.subMap(6, 9);
		SubMap.forEach((key , value) -> System.out.println("Key  " + key + "  ,Value  " + value ));
		
		
		// tuong tu sortSet
	}
}
