package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static void baitap1() {
		// bt1 
			bt1 bt = new bt1();
				
	}
	public static void baitap2() {
		bt2 bt = new bt2();
				
	}
	public static void baitap3() {
		bt3 bt = new bt3() ;
	}
	public static void baitap4() {
		bt4 bt = new bt4();
	}
	public static void main(String [] args) {
		
		boolean out = true ;
		do {
			System.out.print("\n Nhấn 1 để chạy bt1: ");
			System.out.print("\n Nhấn 2 để chạy bt2: ");
			System.out.print("\n Nhấn 3 để chạy bt3: ");
			System.out.print("\n Nhấn 4 để chạy bt4: ");
			System.out.print("\n Nhấn 5 để thoát ");
			int button = sc.nextInt() ;sc.nextLine();
			switch (button) {
			case 1: {
				baitap1();
				System.out.print("\nẤn bất kỳ để thoát");
				String back = sc.nextLine();
				continue;
			}
			case 2:{
				baitap2();
				System.out.print("\nẤn bất kỳ để thoát");
				String back = sc.nextLine();
				continue;
			}
			case 3: {
				baitap3();
				System.out.print("\nẤn bất kỳ để thoát");
				String back = sc.nextLine();
				continue;
			}
			case 4: {
				baitap4();
				System.out.print("\nẤn bất kỳ để thoát");
				String back = sc.nextLine();
				continue;
			}
			default:
				out = false ;
				System.out.print("\nKết thúc chương trình");
				break;
			}
		}while(out);
	}	
}
