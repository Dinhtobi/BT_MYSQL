 package Library;

import java.util.StringTokenizer;

public class StringLibrary {
	public static void main(String [] args) {
		StringBuilder stringBuilder = new StringBuilder("Hi Im Java");
		for(int i = 0 ; i <= 10 ; i++ ) {	
			stringBuilder.append("S" + i + "|");
		}
		System.out.println(stringBuilder);
		boolean bool = true;
		stringBuilder.insert(6,bool );
		System.out.println("" +stringBuilder);
		char [] c = {'a'  , 'b' ,'c'};
		stringBuilder.insert(4, c, 0, 3);
		System.out.println(" Chèn vào chuỗi 3 kí tự " +stringBuilder);
		
		stringBuilder.delete(0, 6);
		System.out.println(" Xoá  vào chuỗi  6 kí tự " +stringBuilder);
		stringBuilder.deleteCharAt(6);
		System.out.println(" Xoá  tại kí tự số 6 " +stringBuilder);
		stringBuilder.reverse();
		System.out.println(" Đảo kí tự " +stringBuilder);
		
		String tokenstr1 = "StringToken dang 1";
		StringTokenizer tokenizer = new StringTokenizer(tokenstr1);
		System.out.println(" Tokenizaer dạng 1 " +tokenizer);
		
		System.out.println(" Lấy token phân tách bằng khoảng trắng" );
		while(tokenizer.hasMoreElements()) {
			System.out.println(" token: " +tokenizer.nextToken());
		}
		String tokenstr2 = "int, float, long, String" ;
		StringTokenizer tokenizer2 = new StringTokenizer(tokenstr2 , ", ");
		
		System.out.println(" Lấy token phân tách bằng dấu , và khoảng trắng" );
		while(tokenizer2.hasMoreElements()) {
			System.out.println(" Token dạng 2 : " + tokenizer2.nextToken());
		}
		
		String tokenstr3 = "Monday;TuesDay;Wednesday;Thursday;Friday;Saturday;sunday";
		StringTokenizer tokenizer3 = new StringTokenizer(tokenstr3, ";",false);
		while(tokenizer3.hasMoreElements()) {
			System.out.println(" Token dạng 3 : " + tokenizer3.nextToken());
			System.out.println("Số token còn lại có trong Stringtokenizer3 là :" + tokenizer3.countTokens());
				
		}
	}
	
}
