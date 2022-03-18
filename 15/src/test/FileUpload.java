package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/file")
public class FileUpload extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDir = "D:\\upload";
		int size = 10 * 1024 * 1024; 
																									//동명파일 처리정책 
//		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		MultipartRequest multi = new MultipartRequest(req, saveDir, size, "utf-8", new DefaultFileRenamePolicy());
		String name = multi.getFilesystemName("f");
		String origin = multi.getOriginalFileName("f");
		long fileSize = multi.getFile("f").length();
		System.out.println("multi.getFilesystemName : " + name);
		System.out.println("multi.getOriginalFileName : " + origin);
		System.out.println(fileSize);
		
		
		System.out.println(req.getParameter("id"));
		System.out.println(multi.getParameter("id"));
	}
}
