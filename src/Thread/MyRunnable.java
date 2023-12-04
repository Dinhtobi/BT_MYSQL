package Thread;

public class MyRunnable implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i =0 ; i< 3 ; i++) {
			System.out.println(Thread.currentThread().getId() + "\t" + Thread.currentThread().getName());
		}
	}
}
