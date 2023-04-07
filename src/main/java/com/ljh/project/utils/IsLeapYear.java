package com.ljh.project.utils;

public class IsLeapYear {
    public static boolean isLeapYear(int year) {
        return ((year%4==0)&&(year%100!=0))||((year%4==0))&&(year%400==0);
    }
    public static String timeFormat(int year, int month) {
        return year + "-" + (month < 10 ? "0" + month : month);
    }
    public static int days(int year, int month) {
        if(month==2) {
            //判断年是不是闰年
            if((isLeapYear(year))){
                return 29;
            }else {
               return 28;
            }
        }else if(month==4||month==6||month==9||month==11) {
            return 30;
        }else{
           return 31;
        }
    }
}
