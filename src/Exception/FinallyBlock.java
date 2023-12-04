package Exception;

public class FinallyBlock {
	
	public static String test() {
		try {
			return "Đã xong try";
		} finally {
			System.out.println("Finally Block");
		}
	}
	public static void main(String [] args) {
		System.out.println(test());
	}
}
