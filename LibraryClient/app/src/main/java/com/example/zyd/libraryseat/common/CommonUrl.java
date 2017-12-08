package com.example.zyd.libraryseat.common;

/**
 * Created by zyd on 2017/10/29.
 */

public abstract class CommonUrl {
    public static String parent_url = "http://172.20.10.2:8080/Library";
//    public static String parent_url = "http://192.168.1.131:8080/Library";

    public static String login_url = parent_url+"/LoginServlet";
    public static String register_url = parent_url + "/RegisterServlet";
    public static String getSeatData_url = parent_url + "/GetSeatData";
    public static String selectSeat_url = parent_url + "/FindSeat";
    public static String getComputer_url = parent_url + "/ComputerSeat";
}
