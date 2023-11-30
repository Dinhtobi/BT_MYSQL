package Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class sortedSet {
	public static void main(String []args) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < 11 ; i++ ) {
			list.add(i);
		}
		SortedSet<Integer> sortSet1 = new TreeSet<Integer>(list);
		
		SortedSet<Integer> sortSet2 = sortSet1.headSet(5);
		
		SortedSet<Integer> sortSet3 = sortSet1.tailSet(5);

		SortedSet<Integer> sortSet4 = sortSet1.subSet(3, 5);
		System.out.println(sortSet1);
		System.out.println("Head" + sortSet2);
		System.out.println("Tail" + sortSet3);
		System.out.println("Sub" + sortSet4);
		System.out.println("Min" + sortSet1.first());
		System.out.println("Max" + sortSet1.last());
	}
}
