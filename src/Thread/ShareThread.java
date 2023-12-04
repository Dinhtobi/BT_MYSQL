package Thread;

public class ShareThread implements Runnable{

	private  int shareValue = 0 ;
	@Override
	public void run() {
		for(int i = 0 ; i<2 ; i++) {
			System.out.println(Thread.currentThread().getId() +"\t" + Thread.currentThread().getName() +"\t" + shareValue);
			shareValue += 2;
		}
	}

}
