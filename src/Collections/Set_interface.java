package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Set_interface {
	public static void main(String []args) {
	
		// gan list vao set
		List<String> strings = new ArrayList<String>();
		strings.add("A");
		strings.add("B");
		strings.add("C");
		strings.add("A");
		Set<String> setlist= new TreeSet<String>(strings);
		System.out.println(setlist);
		//
		List<Integer> intList1 = new ArrayList<Integer>();
		
		for(int i = 10 ; i >=0 ; i--) {
			intList1.add(i);
		}
		Set<Integer> setInt1 = new TreeSet<Integer>();
		Set<Integer> setInt2 = new HashSet<Integer>();
		setInt1 = intList1.stream().filter(number -> number % 2 ==0).collect(Collectors.toSet());
		setInt2 = intList1.stream().filter(number -> number % 3 == 0).collect(Collectors.toSet());
		if(setInt1.contains(4)) {
				System.out.println("Có số 4 trong Set");
				setInt1.remove(4);
				System.out.println("Đã xoá 4 trong Set");
			}
		System.out.println("Size cua Set 1 la "  + setInt1.size());
		if(!setInt1.containsAll(setInt2)) {
			System.out.println("Set 2 khong la con Set 1");
		}
		Set<Integer> set3 = setInt2;
		
		set3.addAll(setInt1);
		System.out.println("Set 3 "   + set3);
		set3.retainAll(setInt1);
		System.out.println("Set 3 sau khi bỏ phần tử có trong set 3 nhưng không có trong set 1 " + set3);
		set3.removeAll(setInt1);
		System.out.println("Set 3 sau khi bỏ phần tử có trong set 3 củng có trong set 1 " + set3);
		
	}
}
