package Collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamInJava {
	public static void main(String[] args) {
		// map
		List<Integer> list = Arrays.asList(1,2,3,4);
		List<Integer> listmap = list.stream().map(x -> x+2).collect(Collectors.toList());
		listmap.forEach(x -> System.out.print(x + " "));
		System.out.print("\n");
		List<String> listString = list.stream().map(x -> "Số " + x).collect(Collectors.toList());
		listString.forEach(x -> System.out.print(x + " "));
		
		// filter
		List<String> strings = Arrays.asList("DinhNguyen", "HungHoang" ,"NhanABC" ,"HungNguyen");
		List<String> result = strings.stream().filter(s-> s.startsWith("H")).collect(Collectors.toList());
		System.out.print("\n");
		result.forEach(s -> System.out.print(s + " "));
		
		// sort
		List<String> sort = strings.stream().sorted().collect(Collectors.toList());
		System.out.print("\n");
		sort.forEach(s -> System.out.print(s + " "));
		
		// max min trong 1,2,3,4
		System.out.print("\n");
		Integer max = list.stream().max(Integer::compare).get();
		Integer min = list.stream().min(Integer::compareTo).get();
		System.out.println("max = " + max+ ", min = " + min );
		
		//reduce
		String concat  = strings.stream().reduce("Begin :", String::concat);
		System.out.println("Nối chuỗi :" + concat );
		System.out.println("Limit 2");	
		//limit
		list.stream().limit(2).forEach(System.out::print);
		System.out.println("\nSkip 2" );
		//skip
		list.stream().skip(2).forEach(System.out::print);
	}
}
