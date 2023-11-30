package OOP;

public class bt1 {
	public bt1() {
		System.out.println("Bài tập tính diện tích hình chử nhật");
		
		HinhChuNhat hcn = new HinhChuNhat();
		hcn.setDai(3);
		hcn.setRong(6);
		System.out.println("Diện tích: " +hcn.dientich());
		System.out.println("Chu vi: " +hcn.chuvi());
	}
}
