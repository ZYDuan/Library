package com.zyd.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sun.glass.ui.Pixels.Format;
import com.zyd.common.CommonResponse;
import com.zyd.utils.DBUtils;

/**
 * Servlet implementation class GetSeatData
 */
@WebServlet("/GetSeatData")
public class GetSeatData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSeatData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String req = sb.toString();
		System.out.println(req);
		
		JSONObject object = new JSONObject(req);
		String requestCode = object.getString("requestCode");
		JSONObject requestParam = object.getJSONObject("requestParam");
		
		int floor = Integer.parseInt(requestParam.getString("floor"));
		String sql = String.format("SELECT * FROM %s WHERE FSID = %s AND ISFULL = 1",DBUtils.Table_Seat,floor);
		System.out.println(sql);
		CommonResponse res = new CommonResponse();
		try {
			ResultSet resultSet = DBUtils.query(sql);
			while(resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("row",resultSet.getString("ROWS"));
				map.put("column", resultSet.getString("COL"));
				res.addListItem(map);
			}
			res.setResCode("0");
		}catch(SQLException e) {
			res.setResult("300","连接数据库出现错误");
			e.printStackTrace();
		}
		
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		String resStr = new JSONObject(res).toString();
		System.out.println(resStr);
		response.getWriter().append(resStr).flush();
	}

}
