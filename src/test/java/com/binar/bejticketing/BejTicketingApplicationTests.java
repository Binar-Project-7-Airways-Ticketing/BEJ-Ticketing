package com.binar.bejticketing;

import com.binar.bejticketing.utils.ConvertDateToTime;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class BejTicketingApplicationTests {

	@Test
	void contextLoads() throws ParseException {
//		String regex = "\"(\\d{4})[-]\\d{2}[-]\\d{02}[ ]\"gm";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher m = pattern.matcher("2012-07-08 24:00");
//		final String regex = "(\\d{4})[-]\\d{2}[-]\\d{02}[ ]";
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
//		String strDate = dateFormat.format(date);
//
//		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
//		final Matcher m = pattern.matcher(strDate);
//		StringBuffer sb = new StringBuffer();
//		while (m.find()){
//			m.appendReplacement(sb, "");
//		}
//		m.appendTail(sb);
////		String result = m.replaceAll("CAT");
		ConvertDateToTime convertDateToTime = new ConvertDateToTime();
		Date convert = convertDateToTime.convertDate(date);
		System.out.println(dateFormat.format(convert));
//		System.out.println(m.group(0));
//		System.out.println(m.group(1));
//		System.out.println(m.group(2));
	}

}
