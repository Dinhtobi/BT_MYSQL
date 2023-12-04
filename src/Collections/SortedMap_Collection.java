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
		SortedMap<Integer, String> SubMap2 = sortMap.headMap(6);
		 System.out.println("SubMap2: " );
		SubMap2.forEach((key , value) -> System.out.println("Key  " + key + "  ,Value  " + value ));
		SortedMap<Integer, String> SubMap3 = sortMap.tailMap(6);
		 System.out.println("SubMap3: " );
		SubMap3.forEach((key , value) -> System.out.println("Key  " + key + "  ,Value  " + value ));
		System.out.println("GTNN: "+  SubMap3.get(SubMap3.firstKey()) );
		System.out.println("GTLN: "+  SubMap3.lastKey() );
		
	}
}
