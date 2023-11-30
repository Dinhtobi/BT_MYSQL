package Library;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberLibrary {
	public static void  main(String []args) {
		// NumberFormat
		System.out.print("\n Định dạng kiểu anh");
		Locale locale1 = new Locale("en" , "EN");		
		NumberFormat numberFormat = NumberFormat.getInstance(locale1);
		
		double doubleNumber = 28.06;
		String str1 = numberFormat.format(doubleNumber);
		System.out.print("\n Số " + doubleNumber + " sau khi định dạng là " + str1 );
		long longNumber = 2862002;
		String str2 = numberFormat.format(longNumber);
		System.out.print("\n Số " + longNumber + " sau khi định dạng là " + str2 );

		System.out.print("\n Định dạng kiểu VN");
		Locale locale2 = new Locale("vi" , "VN");		
		NumberFormat numberFormat2 = NumberFormat.getInstance(locale2);
		
		double doubleNumber2 = 28.06;
		String str3 = numberFormat2.format(doubleNumber2);
		System.out.print("\n Số " + doubleNumber + " sau khi định dạng là " + str3 );
		long longNumbe2 = 2862002;
		String str4 = numberFormat2.format(longNumbe2);
		System.out.print("\n Số " + longNumber + " sau khi định dạng là " + str4 );
		
		//DecimalFormat 
		
		double doubleDecimal = 28.6666d;
		
		DecimalFormat dcf = new DecimalFormat("#.##");
		System.out.print("\n Số " + doubleDecimal +" có định dạng là " + dcf.format(doubleDecimal));
		
		
		double doubleDecimal2 = 123123.212310d;
		DecimalFormat dcf2 = (DecimalFormat) NumberFormat.getNumberInstance(locale1);
		String patern = "##,####.##";
		dcf2.applyLocalizedPattern(patern);
		System.out.print("\n Số " + doubleDecimal2 +" có định dạng là " + dcf2.format(doubleDecimal2));
		
	}
}
