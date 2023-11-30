package OOP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class bt4 {
	public static Scanner sc = new Scanner(System.in);
	public 	Map<String, Account> maps = new HashMap<String, Account>();
	public bt4() {
		System.out.println("Bài tập quản lí tài khoản ngân hàng đơn giản");
		boolean tieptuc = true ;
	
		// tạo tài khoản mặc định để đăng nhập 
		Account account = new Account(1L, "Dinh");
		maps.put(account.getName(), account);
		do {
			System.out.println("1.Đăng nhập tài khoản (đăng nhập bằng name)");
			System.out.println("2.Đăng ký tài khoản");
			System.out.println("3.Thoát chương trình");
			System.out.println("Chọn chức năng");
			int button = sc.nextInt(); sc.nextLine();
			switch (button) {
			case 1: {
				System.out.println("Nhập tên tài khoản");
				String name =  sc.nextLine();
				if(maps.containsKey(name)) {
					Giaodienchinh(name);
					continue;
				}else {
					System.out.println("Sai tên tài khoản");
					System.out.println("Nhấn bất kỳ để thoát");
					sc.nextLine();
					continue;
				}
				
			}
			case 2 :{
				System.out.println("Đăng ký tài khoản");
				Account accountregister = new Account();
				System.out.println("Nhập ID ");
				accountregister.setAccountId(sc.nextLong()); sc.nextLine();
				System.out.println("Nhập Tên tài khoản");
				accountregister.setName(sc.nextLine());
				maps.put(accountregister.getName(), accountregister);
				System.out.println("Đăng ký thành công");
				System.out.println("Nhấn bất kỳ để thoát");
				sc.nextLine();
				continue;
			}
			default:{
				tieptuc = false ;
				break;
			}
			}
		}while(tieptuc);
	}
	
	public void Giaodienchinh(String name) {
		boolean tieptuc = true ;
		do {
			Account account = maps.get(name);
			System.out.println("1.Xem tài khoản của mình");
			System.out.println("2.Xem tài khoản mọi người");
			System.out.println("3.Nạp tiền");
			System.out.println("4.Rút tiền");
			System.out.println("5.Chuyển tiền");
			System.out.println("6.Tiền đáo sẽ nhận được");
			System.out.println("7.Đăng xuất");
			int button = sc.nextInt(); sc.nextLine();
			switch (button) {
			case 1: {
				System.out.println("Thông tin tài khoản của bạn");
				account.tostring();
				System.out.println("Nhấn bất kỳ để thoát");
				sc.nextLine();
				continue;
			}
			case 2 :{
				System.out.println("Thông tin tài khoản của mọi người");
				System.out.println("accountId\tname\tmoney" );
				maps.forEach((keyname , acount) -> System.out.println(acount.getAccountId() + "\t\t" + acount.getName() + "\t" + acount.getMoney()));
				System.out.println("Nhấn bất kỳ để thoát");
				sc.nextLine();
				continue;
			}
			case 3:{
				System.out.println("Nhập số tiền nạp");
				double tiennap = sc.nextDouble(); 
				sc.nextLine();
				System.out.println(account.Naptien(tiennap));;
				System.out.println("Nhấn bất kỳ để thoát");
				sc.nextLine();
				continue;
			}
			case 4:{
				System.out.println("Nhập số tiền rút");
				double tienrut = sc.nextDouble(); 
				sc.nextLine();
				System.out.println(account.Ruttien(tienrut));;
				System.out.println("Nhấn bất kỳ để thoát");
				sc.nextLine();
				continue;
			}
			case 5:{
				System.out.println("Nhập tên người nhận");
				String tennguoinhan = sc.nextLine();
				if(maps.containsKey(tennguoinhan)) {
					Account account2 = maps.get(tennguoinhan);
					System.out.println("Nhập số tiền cần chuyển");
					double tienchuyen = sc.nextDouble(); 
					sc.nextLine();
					System.out.println(account.chuyentien(account, account2 , tienchuyen));;
				}else {
					System.out.println("Người nhận không tồn tại");
				}
				
				System.out.println("Nhấn bất kỳ để thoát");
				sc.nextLine();
				continue;
			}
			case 6:{
				System.out.println("Tiền đáo hạn nhận được");
				System.out.println(account.DaoHan());;
				System.out.println("Nhấn bất kỳ để thoát");
				sc.nextLine();
				continue;
			}
			default:
				tieptuc = false ;
				break;
			}
		}while(tieptuc);
	}
}
