package OOP;

public class Account {
	private Long accountId ;
	
	private String name;
	
	private double money ;

	
	public Account(Long accountId, String name) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.money = 50;
	}

	public Account() {

	}
	
	public void tostring() {
		System.out.println("accountId\tname\tmoney" );
		System.out.println(accountId + "\t\t" + name + "\t" + money);
	}
	
	public String Naptien(double tiennap) {
		if(tiennap > 0) {
			this.money += tiennap;
			return "Nạp tiền thành công";
		}else {
			return "Nạp tiền thất bại, xin thử lại";
		}
	}
	public String Ruttien(double tienrut) {
		if(this.money < tienrut) {
			return "Số tiền không đủ để rút" ;
		}else {
			this.money -= tienrut;
			return "Rút tiền thành công";
		}
		
	}
	public double DaoHan() {
		return  this.money*0.035;
	}
	
	public String chuyentien(Account ac1, Account ac2, double tienchuyen) {
		if(ac1.money < tienchuyen) {
			return "Số tài khoản của bạn không đủ để thực hiện giao dịch này";
		}else {
			ac2.money += tienchuyen;
			ac1.money -= tienchuyen;
			return "chuyển tiền thành công";
		}
		
	}
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	
}
