package com.xuxueya.atcrowdfunding.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_StringUtil {
public static String dateToString (Date date) {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	return sdf.format(date);
}
public static Date stringToDate (String str) {
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
try {
	return  sdf.parse(str);
} catch (ParseException e) {
	e.printStackTrace();
	
}
return null;
}
}
