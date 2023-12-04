package Thread;


public class SynchronizedThread {
	private int Sotien = 100000;

	public SynchronizedThread() {
		System.out.println("Số tiền trong tài khoản là " + Sotien);
	}
	
	public synchronized void RutTien(int sotienrut) {
		System.out.println("Đang thực hiện rút tiền");
		if(Sotien < sotienrut)
			try {
				wait();
				} catch (Exception e) {
				System.out.println("Xảy ra lỗi khi rút tiền");
			}
		Sotien -=sotienrut;
		System.out.println("Rút tiền thành công ");
	}
	
	public synchronized void Naptien(int sotiennap) {
		System.out.println("Đang thực hiện nạp tiền");
		Sotien +=sotiennap;
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Xảy ra lỗi khi nạp tiền");
		}
		System.out.println("Nạp tiền thành công");
		notify();
	}
	
	public static void main(String[] args) {
		final SynchronizedThread customer = new SynchronizedThread();
		
		Thread th1 = new Thread() {

			@Override
			public void run() {
				customer.RutTien(200000);
			}
			
		};
		th1.start();
		
		Thread th2 = new Thread() {

			@Override
			public void run() {
				customer.Naptien(800000);
			}
			
		};
		th2.start();
	}
}
