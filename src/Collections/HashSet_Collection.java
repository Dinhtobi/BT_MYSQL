package Collections;

import java.util.HashSet;

public class HashSet_Collection {
	public static void main(String [] args) {
		HashSet<Integer> hash = new HashSet<Integer>();
		for(int i = 0 ; i< 5; i++) {
			double ran = Math.random();
			ran = ran*100 + 1;
			hash.add((int)ran);
		}
		Integer[] array = new Integer[hash.size()];
		hash.toArray(array);
	}
}
