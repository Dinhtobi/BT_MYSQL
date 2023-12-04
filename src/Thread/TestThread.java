package Thread;

public class TestThread {

	public static void main(String[] args) {

		MyThread my1 = new MyThread();
		my1.start();
		
		MyThread my2 = new MyThread();
		my2.start();
		
		MyThread my3 = new MyThread();
		my3.setName("Luồng 3");
		my3.start();
		
		MyRunnable mr1 = new MyRunnable();
		Thread th1 = new Thread(mr1);
		th1.start();
		
		MyRunnable mr2 = new MyRunnable();
		Thread th2 = new Thread(mr2);
		th2.setName("Luồng thứ 2 Runnable");
		th2.start();
		
		MyRunnable mr3 = new MyRunnable();
		Thread th3 = new Thread(mr3);
		th3.start();
		
		ShareThread share = new ShareThread();
		
		Thread th4 = new Thread(share);
		th4.setName("Luồng thứ 4 Share");
		th4.start();
		try {
			th4.join();
		}catch(InterruptedException e) {
			
		}
		
		

		Thread th5= new Thread(share);
		th5.setName("Luồng thứ 5 Share");
		
		th5.start();
		try {
			th5.join();
		}catch(InterruptedException e) {
			
		}

		Thread th6 = new Thread(share);
		th6.setName("Luồng thứ 6 Share");
		
		th6.start();
		try {
			th6.join();
		}catch(InterruptedException e) {
			
		}
	}
}
