package com.binar.bejticketing.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertDateToTime {
    public Date convertTime(Date datetime) throws ParseException {
        final String regex = "(\\d{4})[-]\\d{2}[-]\\d{02}[ ]";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(datetime);

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher m = pattern.matcher(strDate);
        StringBuffer sb = new StringBuffer();
        while (m.find()){
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        System.out.println(sb);
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date date1 = formatter.parse(sb.toString());
        return date1;
    }

    public Date convertDate(Date datetime) throws ParseException {
        final String regex = "(\\d{4})[-]\\d{2}[-]\\d{02}[ ]";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(datetime);

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher m = pattern.matcher(strDate);
        StringBuffer sb = new StringBuffer();
        while (m.find()){
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        System.out.println(sb);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(sb.toString());
        return date1;
    }
}
