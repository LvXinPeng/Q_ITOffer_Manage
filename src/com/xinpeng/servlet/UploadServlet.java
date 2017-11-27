package com.xinpeng.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.core.ApplicationPart;

@MultipartConfig
public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	public  UploadServlet(){
		super();
	}
	
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	// �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    /*private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
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


		
	    /**
	     * �ϴ����ݼ������ļ�
	     */

	        // ����Ƿ�Ϊ��ý���ϴ�
	       /* if (!ServletFileUpload.isMultipartContent(request)) {
	            // ���������ֹͣ
	            PrintWriter writer = response.getWriter();
	            writer.println("Error: ��������� enctype=multipart/form-data");
	            writer.flush();
	            return;
	        }*/
/*	 
	        // �����ϴ�����
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // ������ʱ�洢Ŀ¼
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         
	        // ��������ļ��ϴ�ֵ
	        //upload.setFileSizeMax(MAX_FILE_SIZE);
	         
	        // �����������ֵ (�����ļ��ͱ�����)
	       // upload.setSizeMax(MAX_REQUEST_SIZE);
	        
	        // ���Ĵ���
	        upload.setHeaderEncoding("UTF-8"); 

	        // ������ʱ·�����洢�ϴ����ļ�
	        // ���·����Ե�ǰӦ�õ�Ŀ¼
	        String uploadPath = getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
	       
	         
	        // ���Ŀ¼�������򴴽�
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	 
	        try {
	            // ���������������ȡ�ļ�����
	            List<FileItem> formItems = upload.parseRequest(request);
	 
	            if (formItems != null && formItems.size() > 0) {
	                // ����������
	                for (FileItem item : formItems) {
	                    // �����ڱ��е��ֶ�
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        String filePath = uploadPath + File.separator + fileName;
	                        File storeFile = new File(filePath);
	                        // �ڿ���̨����ļ����ϴ�·��
	                        System.out.println(filePath);
	                        // �����ļ���Ӳ��
	                        item.write(storeFile);
	                        request.setAttribute("message",
	                            "�ļ��ϴ��ɹ�!");
	                    }
	                }
	            }
	        } catch (Exception ex) {
	            request.setAttribute("message",
	                    "������Ϣ: " + ex.getMessage());
	        }
	        // ��ת�� message.jsp
	        getServletContext().getRequestDispatcher("/success.jsp").forward(
	                request, response);*/
		
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ�ϴ��ļ���
		Part part = request.getPart("headShot");		
		// ��ȡ�ϴ��ļ�����
		String fileName = ((ApplicationPart) part).getSubmittedFileName();
		// Ϊ��ֹ�ϴ��ļ����������ļ�����������
		String newFileName = System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf("."));
		// ���ϴ����ļ������ڷ�������Ŀ����·����applicant/imagesĿ¼��
		String filepath = getServletContext().getRealPath("upload");
		System.out.println("ͷ�񱣴�·��Ϊ��" + filepath);
		File f = new File(filepath);
		if (!f.exists())
			f.mkdirs();
		part.write(filepath + "/" + newFileName);
		response.sendRedirect("success.jsp");
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
