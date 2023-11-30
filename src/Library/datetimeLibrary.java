package Library;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class datetimeLibrary {
	public static void main(String []args) {
		Calendar cal = Calendar.getInstance();
		System.out.println("thoi gian hien tai la " + cal.getTime());
		
		System.out.println("Ngay hom nay la " + cal.get(Calendar.DAY_OF_MONTH) + " thang " + cal.get(Calendar.MONTH) +" nam " + cal.get(Calendar.YEAR));
		
		System.out.println("Bay gio la " + cal.get(Calendar.HOUR_OF_DAY) + "h "  + cal.get(Calendar.MINUTE) + "m " + cal.get(Calendar.SECOND)+"s");
		
		System.out.println("Set ngay");
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2002, Calendar.JUNE, 28, 11, 11,11);
		System.out.print("Ngay sau khi set " + cal2.getTime());
		cal2.add(Calendar.MINUTE, +17);
		cal2.add(Calendar.SECOND, -5);
		System.out.print("\nNgay sau khi cong tru " + cal2.getTime());
		
		cal2.roll(Calendar.MONTH, +7);
		System.out.print("\nNgay sau khi + thang bang roll " + cal2.getTime());
		
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("\nNgay thang dang dd/MM/yyyy " + date.format(cal.getTime()));
		
	}
}
