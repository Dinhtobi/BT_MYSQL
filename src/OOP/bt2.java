package OOP;

public class bt2 {
	public bt2() {
		//bt2 
		System.out.println("Bài tập thêm và hiển thị siên viên");
	
		SinhVien sv1 = new SinhVien() ;
		
		SinhVien sv2 = new SinhVien();
		sv2.setId(1);
		sv2.setHoVaTen("NVA");
		sv2.setDiemTH(3);
		sv2.setDiemLT(10);
		SinhVien sv3 = new SinhVien(2, "NVB" , 5,5);
		System.out.println("SV1: " + sv1.toString() + "SV2 : "+  sv2.toString() + "SV3:" + sv3.toString());
	}
}
