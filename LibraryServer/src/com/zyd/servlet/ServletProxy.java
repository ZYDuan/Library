package com.zyd.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class ServletProxy
 */
@WebServlet("/ServletProxy")
public class ServletProxy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String targetServletBean;
	private Servlet proxy;

	@Override
	public void init() throws ServletException {
		this.targetServletBean = this.getInitParameter("targetServletBean");
		this.getServletBean();
		this.proxy.init(this.getServletConfig());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proxy.service(request, response);
	}

	private void getServletBean() {
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext wac = null;
		wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		this.proxy = (Servlet) wac.getBean(targetServletBean);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProxy() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
