package Library;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class NumberLibrary {
	public static void  main(String []args) {
		// NumberFormat
		System.out.println("Định dạng kiểu anh");
		Locale locale1 = new Locale("en" , "EN");		
		NumberFormat numberFormat = NumberFormat.getInstance(locale1);
		
		System.out.println("Country " +  locale1.getDisplayCountry() + ", Language " + locale1.getDisplayLanguage());
		System.out.println("Code Country " + locale1.getCountry() + ", code language " + locale1.getLanguage() );
		System.out.println("Info System country " + System.getProperty("user.country") + 
				", Info System language " + System.getProperty("user.language"));
		System.out.println("Định dạng kiểu Anh");
		double doubleNumber = 28.06;
		String str1 = numberFormat.format(doubleNumber);
		System.out.println(" Số " + doubleNumber + " sau khi định dạng là " + str1 );
		long longNumber = 2862002;
		String str2 = numberFormat.format(longNumber);
		System.out.println(" Số " + longNumber + " sau khi định dạng là " + str2 +"\n");

		System.out.println(" Định dạng kiểu VN");
		Locale locale2 = new Locale("vi" , "VN");		
		NumberFormat numberFormat2 = NumberFormat.getInstance(locale2);
		
		double doubleNumber2 = 28.06;
		String str3 = numberFormat2.format(doubleNumber2);
		System.out.println(" Số " + doubleNumber + " sau khi định dạng là " + str3 );
		long longNumbe2 = 2862002;
		String str4 = numberFormat2.format(longNumbe2);
		System.out.println(" Số " + longNumber + " sau khi định dạng là " + str4  +"\n");
		System.out.println(" Định dạng tiền tệ");
		NumberFormat money1 = NumberFormat.getCurrencyInstance();
		long longmoney = 2862002;
		String strmoney = money1.format(longmoney);
		System.out.println("Số " + longmoney + " sau khi định dạng là " + strmoney);
		money1.setCurrency(Currency.getInstance(locale2));
		System.out.println( " Chuyển sang định dạng VN " +  money1.format(longNumber) + "\n");
		
		System.out.println(" Định dạng phần trăm");
		NumberFormat numEN = NumberFormat.getPercentInstance();
		String percen = numEN.format(0.286d);
		System.out.println("Số 0.286 sau khi định dạng phần trăm " + percen + "\n");
		
		System.out.println(" Định dạng làm tròn");
		
		NumberFormat lamtron = NumberFormat.getNumberInstance();
		lamtron.setMaximumFractionDigits(2);
			
		
		System.out.println("Số 123.286 sau khi làm tròn " + lamtron.format(123.286) + "\n");
		
		lamtron.setRoundingMode(RoundingMode.DOWN);
		
		System.out.println("Số 123.286 sau khi làm tròn " + lamtron.format(123.286) + "\n");
		
		
		//DecimalFormat 
		
		double doubleDecimal = 28.6666d;
		
		DecimalFormat dcf = new DecimalFormat("#.##");
		System.out.println(" Số " + doubleDecimal +" có định dạng là " + dcf.format(doubleDecimal));
		
		
		double doubleDecimal2 = 123123.212310d;
		DecimalFormat dcf2 = (DecimalFormat) NumberFormat.getNumberInstance(locale1);
		String patern = "##,####.##";
		dcf2.applyLocalizedPattern(patern);
		System.out.println("Số " + doubleDecimal2 +" có định dạng là " + dcf2.format(doubleDecimal2));
		
	}
}
