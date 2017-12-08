package com.zyd.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zyd.algorithm.OneAlgorithm;
import com.zyd.algorithm.ThreeAlgorithm;
import com.zyd.algorithm.TwoAlgorithm;
import com.zyd.common.CommonResponse;
import com.zyd.entity.Seat;
import com.zyd.utils.DBUtils;

/**
 * Servlet implementation class ComputerSeat
 */
@WebServlet("/ComputerSeat")
public class ComputerSeat extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OneAlgorithm oneAlgorithm;
    private TwoAlgorithm twoAlgorithm;
    private ThreeAlgorithm threeAlgorithm;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerSeat() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() throws ServletException {

		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		oneAlgorithm = (OneAlgorithm) ctx.getBean("oneAlgorithm");
		twoAlgorithm = (TwoAlgorithm) ctx.getBean("twoAlgorithm");
		threeAlgorithm = (ThreeAlgorithm) ctx.getBean("threeAlgorithm");

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
		
		int people = Integer.parseInt(requestParam.getString("people"));
		
		CommonResponse res = new CommonResponse();
		HashMap<String, String> map = new HashMap<>();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		switch(people) 
		{
			case 1:
				Seat seat = oneAlgorithm.getBestSeat();
				map.put("floor", String.valueOf(seat.getFloor().getFid()));
				map.put("column", String.valueOf(seat.getColumn()));
				map.put("row", String.valueOf(seat.getRow()));
				res.addListItem(map);
				break;
			case 2:
				list = twoAlgorithm.getBestSeat();
				res.setList(list);
				break;
			case 3:
//				list = twoAlgorithm.getBestSeat();
//				res.setList(list);
				break;
		}
			
			res.setResCode("0");
		
		request.setCharacterEncoding("utf-8");  
		response.setContentType("text/html;charset=utf-8");
		String resStr = new JSONObject(res).toString();
		System.out.println(resStr);
		response.getWriter().append(resStr).flush();
	}
	

}
