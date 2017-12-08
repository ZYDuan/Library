package com.zyd.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zyd.common.CommonResponse;
import com.zyd.entity.Seat;
import com.zyd.utils.DBUtils;

/**
 * Servlet implementation class SelectSeat
 */
@WebServlet("/FindSeat")
public class FindSeat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindSeat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader read = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = read.readLine()) != null) {
			sb.append(line);
		}
		String req = sb.toString();
		System.out.println(req);
		
		JSONObject object = new JSONObject(req);
		String requestCode = object.getString("requestCode");
		JSONObject requestParam = object.getJSONObject("requestParam");
		
		final int id = Integer.parseInt(requestParam.getString("id"));
		int floor = Integer.parseInt(requestParam.getString("floor"));
		int column = Integer.parseInt(requestParam.getString("column"));
		int row = Integer.parseInt(requestParam.getString("row"));
		
		Seat seat = new Seat();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		int sid = 0;
		String getSql = String.format("select * from %s where col = %s and rows = %s and fsid = %s", DBUtils.Table_Seat, column, row, floor);
		try {
			ResultSet resultSet = (ResultSet) DBUtils.query(getSql);
			if(resultSet.next()) {
				seat.setIsFull(1);
				int i = resultSet.getInt("PRIORITY")+1;
				seat.setPriority(i);
				seat.setIsFull(1);
				seat.setSid(resultSet.getInt("SID"));
				sid = resultSet.getInt("SID");
				
				}
			}catch (SQLException e) {
					e.printStackTrace();
				}
		
			try {
				String sql = String.format("INSERT INTO %s (STUSID, SEATSID, TIME) VALUES "+"(%s, %s, '%s')",
						DBUtils.Table_Sit, Integer.parseInt(requestParam.getString("id")), sid, date);
				
				DBUtils.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String updateSql = String.format("UPDATE %s SET ISFULL = %s , PRIORITY = %s WHERE SID = %s", 
										DBUtils.Table_Seat, seat.getIsFull(), seat.getPriority(),seat.getSid());
		System.out.println(updateSql);
		try {
			DBUtils.update(updateSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonResponse res = new CommonResponse();
		res.setResCode("0");
		response.setContentType("text/html;charset=utf-8");
		String resStr = new JSONObject(res).toString();
		System.out.println(resStr);
		response.getWriter().append(resStr).flush();
	}
	

}
