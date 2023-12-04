package Thread;

public class RunPool implements Runnable{
	int id ;

	public RunPool(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Đang xử lí luồng " + id );
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {
			
		}
		System.out.println("Đã xử lí luồng " + id);
	}
	
	
}
