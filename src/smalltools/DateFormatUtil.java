package smalltools;

import java.text.SimpleDateFormat;

public class DateFormatUtil {
	/*
	 * 返回SimpleDateFormat
	 */
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
	
	public static SimpleDateFormat getSimpleDateFormat(){
		return simpleDateFormat;
	}
	
}
