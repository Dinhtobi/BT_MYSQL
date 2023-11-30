package OOP;

public class SinhVien {
	private int id;
	
	private String HoVaTen;
	
	private double DiemLT;
	
	private double DiemTH;
	public SinhVien() {
		this.id = 0;
		this.DiemLT = 0;
		this.DiemTH = 0;
		this.HoVaTen = "";
	}

	public SinhVien(int id, String hoVaTen, double diemLT, double diemTH) {
		super();
		this.id = id;
		HoVaTen = hoVaTen;
		DiemLT = diemLT;
		DiemTH = diemTH;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoVaTen() {
		return HoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		HoVaTen = hoVaTen;
	}

	public double getDiemLT() {
		return DiemLT;
	}

	public void setDiemLT(double diemLT) {
		DiemLT = diemLT;
	}

	public double getDiemTH() {
		return DiemTH;
	}

	public void setDiemTH(double diemTH) {
		DiemTH = diemTH;
	}

	public double TB() {
		return (DiemLT+DiemTH) / 2;
	}
	public String toString() {
		return "Ho va Ten" + HoVaTen + " Diem TB " + String.valueOf(TB()) + "\n";
	}
}
