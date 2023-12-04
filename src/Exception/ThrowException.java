package Exception;

import java.util.Scanner;

public class ThrowException {
	public static void CheckExcception(int age , int weight) {
		if(age < 12 && weight < 45) {
			throw new ArithmeticException("Lỗi không đủ điều kiện tham gia");
		}else {
			System.out.println("Đủ điều kiện tham gia");
		}
	}
	
	public static void CheckMyException(int i) throws MyException {
		System.out.println("Check error in CheckMyException");
		throw new MyException(" ERROR 401");
	}
	public static void main(String[] args) {
		System.out.println("Nhập thông tin thí sinh");
		Scanner sc = new Scanner(System.in);
		int age , weight ; 
		age = sc.nextInt() ; sc.nextLine();
		
		weight = sc.nextInt() ; sc.nextLine();
		
		CheckExcception(age, weight);
	
		try {
			CheckMyException(3);
		} catch (MyException  e) {
			System.out.println(e);
		}
		
		
	}
}
