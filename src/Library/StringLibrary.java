package Library;

import java.util.StringTokenizer;

public class StringLibrary {
	public static void main(String [] args) {
		StringBuilder stringBuilder = new StringBuilder("Hi Im Java");
		for(int i = 0 ; i <= 10 ; i++ ) {	
			stringBuilder.append("S" + i + "|");
		}
		System.out.print(stringBuilder);
		boolean bool = true;
		stringBuilder.insert(6,bool );
		System.out.print("\n" +stringBuilder);
		char [] c = {'a'  , 'b' ,'c'};
		stringBuilder.insert(4, c, 0, 3);
		System.out.print("\n Chèn vào chuỗi 3 kí tự " +stringBuilder);
		
		stringBuilder.delete(0, 6);
		System.out.print("\n Xoá  vào chuỗi  6 kí tự " +stringBuilder);
		stringBuilder.deleteCharAt(6);
		System.out.print("\n Xoá  tại kí tự số 5 " +stringBuilder);
		stringBuilder.reverse();
		System.out.print("\n Đảo kí tự " +stringBuilder);
		
		String tokenstr1 = "StringToken dang 1";
		StringTokenizer tokenizer = new StringTokenizer(tokenstr1);
		System.out.print("\n Tokenizaer dạng 1 " +tokenizer);
		
		System.out.print("\n Lấy token phân tách bằng khoảng trắng" );
		while(tokenizer.hasMoreElements()) {
			System.out.print("\n token: " +tokenizer.nextToken());
		}
		String tokenstr2 = "int, float, long, String" ;
		StringTokenizer tokenizer2 = new StringTokenizer(tokenstr2 , ", ");

		System.out.print("\n Lấy token phân tách bằng dấu , và khoảng trắng" );
		while(tokenizer2.hasMoreElements()) {
			System.out.print("\n Token dạng 2 : " + tokenizer2.nextToken());
		}
		
		String tokenstr3 = "Monday;TuesDay;Wednesday;Thursday;Friday;Saturday;sunday";
		StringTokenizer tokenizer3 = new StringTokenizer(tokenstr3, ";",false);
		
		while(tokenizer3.hasMoreElements()) {
			System.out.print("\n Token dạng 3 : " + tokenizer3.nextToken());
				
		}
	}
	
}
