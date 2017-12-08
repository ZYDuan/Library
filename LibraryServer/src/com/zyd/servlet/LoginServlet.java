package com.zyd.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.zyd.common.CommonResponse;
import com.zyd.utils.DBUtils;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		String UserId = requestParam.getString("userName");
		String password = requestParam.getString("password");
		
		String sql = String.format("SELECT ID FROM %s WHERE STUDENTID = '%s' AND PASSWORD = '%s'", DBUtils.Table_Student, UserId, password);
		System.out.println(sql);
		CommonResponse res = new CommonResponse();
		
		try {
			HashMap<String, String> map =new HashMap();
			ResultSet result = DBUtils.query(sql);
			if(result.next()) {
				res.setResCode("0");
				map.put("userId", result.getString("ID"));
				res.addListItem(map);
			}else {
				res.setResCode("1");
				System.out.println("找不到数据库对应的数据");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res.setResult("300","连接数据库出现错误");
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=utf-8");
		String resStr = new JSONObject(res).toString();
		System.out.println(resStr);
		response.getWriter().append(resStr).flush();
	}
	

}
