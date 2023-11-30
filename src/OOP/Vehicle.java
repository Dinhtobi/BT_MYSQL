package OOP;

public class Vehicle {
	private int maXe;
	
	private int dungTich;
	
	private double triGia;
	
	private String chuXe;
	
	private String moTa;

	public int getMaXe() {
		return maXe;
	}

	public void setMaXe(int maXe) {
		this.maXe = maXe;
	}

	public Vehicle(int maXe, int dungTich, double triGia, String chuXe, String moTa) {
		super();
		this.maXe = maXe;
		this.dungTich = dungTich;
		this.triGia = triGia;
		this.chuXe = chuXe;
		this.moTa = moTa;
	}

	public Vehicle() {
	}

	public double tinhthue() {
		if(dungTich < 100 ) {
			return triGia*1/100;
		}else if(dungTich >= 100 && dungTich <= 200 ) {
			return triGia*3/100;
		}else {
			return triGia*5/100;
		}
	}
	
	public int getDungTich() {
		return dungTich;
	}

	public void setDungTich(int dungTich) {
		this.dungTich = dungTich;
	}

	public double getTriGia() {
		return triGia;
	}

	public void setTriGia(double triGia) {
		this.triGia = triGia;
	}

	public String getChuXe() {
		return chuXe;
	}

	public void setChuXe(String chuXe) {
		this.chuXe = chuXe;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
}
