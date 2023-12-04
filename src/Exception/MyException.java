package Exception;

public class MyException extends Exception{
	private String error ;

	public MyException(String error) {
		this.error = error;
	}
	
	public String toString() {
		return ("Có lỗi khi thực hiện" + error);
	}
	
}
