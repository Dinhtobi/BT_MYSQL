package OOP;

public class HinhChuNhat {
	private double dai ;
	
	private double rong;

	public double getDai() {
		return dai;
	}

	public void setDai(double dai) {
		this.dai = dai;
	}

	public double getRong() {
		return rong;
	}

	public void setRong(double rong) {
		this.rong = rong;
	} 
	
	public double dientich() {
		return dai * rong;
	}
	public double chuvi() {
		return (dai + rong)*2;
	}
}
