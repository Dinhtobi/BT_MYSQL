package Thread;

public class MyThread extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for(int i=0; i < 4 ; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}

}
