package Collections;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedList_Collection {
	public static void main(String []args) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		LinkedList<Integer> linkedList2 = new LinkedList<Integer>();
		for(int i = 0 ; i < 5 ; i++ ) {
			double ran = Math.random();
			ran = ran * 100 + 1;
			linkedList.add((int)ran );
			linkedList.addFirst(28);
			linkedList.addLast(6);
			linkedList2.add((int)ran+10);
		}
		System.out.println(linkedList.size());
		ListIterator<Integer> inIterator = linkedList.listIterator();
		System.out.println( "Duyet xuoi ");
		
		while(inIterator.hasNext()) {
			System.out.print(inIterator.next() + " ");
		}
		System.out.println( "\nDuyet nguoc ");
		
		while(inIterator.hasPrevious()) {
			System.out.print(inIterator.previous() + " ");
		}
		System.out.println( "\ngop linkedList");
		while(inIterator.hasNext()) {
			System.out.print(inIterator.next() + " ");
		}
		System.out.println( "\n Sau gop");
		linkedList.addAll(3, linkedList2);
		System.out.println( linkedList);
		System.out.println( "\n Cap nhat ");
		linkedList.set(9, 100);
		System.out.println( linkedList);
		System.out.println( "\n Xoa ");
		linkedList.remove(9);
		System.out.println( linkedList);
		System.out.println( "\n Xoa phần tử có trong cả list2 và list 1");
		System.out.println("List 2"+  linkedList2);
		linkedList.removeAll(linkedList2);
		System.out.println("List 1"+  linkedList);
		// Sort list
		
		Collections.sort(linkedList);
		System.out.println( linkedList);
		// coppy 
		Collections.copy(linkedList, linkedList2);

		System.out.println( linkedList);
		
		Collections.shuffle(linkedList2);
		System.out.println( linkedList2);
		
	}
}
