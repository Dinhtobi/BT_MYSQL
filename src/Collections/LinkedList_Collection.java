package Collections;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedList_Collection {
	public static void main(String []args) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		LinkedList<Integer> linkedList2 = new LinkedList<Integer>();
		
		for(int i = 0 ; i < 10 ; i++ ) {
			linkedList.add(i);
			linkedList2.add(i+10);
		}
		
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
		linkedList.addAll(linkedList2);
		System.out.println( linkedList);
		System.out.println( "\n Cap nhat ");
		linkedList.set(9, 100);
		System.out.println( linkedList);
		System.out.println( "\n Xoa ");
		linkedList.remove(9);
		System.out.println( linkedList);
		
		
	}
}
