package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class bt3 {
	public static Scanner sc = new Scanner(System.in);
	public static Vehicle NhapXe() {
		Vehicle vehicle = new Vehicle();
		System.out.println("Nhap ma xe");
		vehicle.setMaXe(sc.nextInt()); sc.nextLine();
		System.out.print("Nhap dung tích");
		vehicle.setDungTich(sc.nextInt()); sc.nextLine();
		System.out.print("Nhap chủ xe");
		vehicle.setChuXe(sc.nextLine()); 
		System.out.print("Nhap trị giá");
		vehicle.setTriGia(sc.nextDouble()); sc.nextLine();
		System.out.print("Nhap Mô tả");
		vehicle.setMoTa(sc.nextLine()); 
		return vehicle ;
	}
	public bt3() {
		System.out.println("Bài tập quản lí tiền thuế xe");
		Map<Integer, Vehicle> maps = new HashMap<Integer, Vehicle>();
		
		boolean tieptuc = true ;
		do {
			System.out.print("\n Nhấn 1 để tạo đối tượng xe: ");
			System.out.print("\n Nhấn 2 để xem danh sách xe: ");
			System.out.print("\n Nhấn 3 để thoát: ");
			int button = sc.nextInt() ;sc.nextLine();
			switch (button) {
			case 1: {
				System.out.println("Nhap thong tin xe ");
				Vehicle vehicle = NhapXe();
				maps.put(vehicle.getMaXe(), vehicle);
				System.out.print("\n Thông tin xe đã tạo: ");
				System.out.print("\n Mã xe\t" + "Dung tích\t" + "Chủ xe\t" + "giá\t" + "Mô tả" );
				System.out.print("\n"+vehicle.getMaXe() +"\t" +  vehicle.getDungTich()+"\t\t" +  vehicle.getChuXe()+"\t" + vehicle.getTriGia()+"\t" + vehicle.getMoTa() );
				System.out.print("\n Thoát?");
				System.out.print("\nẤn bất kỳ để thoát");
				String back1 = sc.nextLine();
				continue;
			}
			case 2:{
				System.out.println("Tiền thuế các xe");
				System.out.print("\n Mã xe\t" + "Dung tích\t" + "Chủ xe\t" + "giá\t" + "tiền thuế\t" + "Mô tả" );
				maps.forEach((Maxe, xe) -> 	System.out.print("\n"+xe.getMaXe() +"\t" +  xe.getDungTich()+"\t\t" +  xe.getChuXe()+"\t" + xe.getTriGia()+"\t" 
						+ xe.tinhthue()+"\t\t" + xe.getMoTa() )
				);
				System.out.print("\nẤn bất kỳ để thoát");
				String back2 = sc.nextLine();
				continue;
			}
			default:
				tieptuc = false ;

				System.out.print("\nKết thúc chương trình bt3");
				break;
			}
		}while(tieptuc);
	}
}
