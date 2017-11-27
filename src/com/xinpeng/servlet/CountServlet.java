package com.xinpeng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
         * 1. ��ȡServletContext����
         * 2. ��ServletContext�����л�ȡ��Ϊcount������
         *   3. ������ڣ�����������1��Ȼ���ٱ����ȥ��
         *   4. ��������ڣ�˵���ǵ�һ�η��ʣ���Servletcontext�б�����Ϊcount�����ԣ�ֵΪ1
         */
        ServletContext sc = this.getServletContext();
        Integer count = (Integer) sc.getAttribute("count");
        if (count == null) {
        	count = 1;
        	
        } else {
        	count++;
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /*
         * ����������
         *   ��Ҫʹ����Ӧ����
         */
        sc.setAttribute("count", count);
        System.out.println("����վ������:"+count+"��");
        PrintWriter out = response.getWriter();
        out.print("����վ������:" + count + "��");
        
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
