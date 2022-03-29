package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownloader extends HttpServlet{

	//입출력 스트림 그대로 사용
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String origin = req.getParameter("origin");
		String uuid = req.getParameter("uuid");
		String path = req.getParameter("path");
		
		//저장경로
		String saveDir = "D:\\upload";
		
		File file = new File(new File(saveDir, path), uuid); //path밑에 있는 uuid
		System.out.println(file);
		String mime = getServletContext().getMimeType(file.toString());
		System.out.println(mime); //어플리케이션 스코프중에 getMimeType있음
		
		if (mime == null) { //마임타입이 없을 때 조건문
			mime = "application/octet-stream";
		}
		
		String fileName = new String(origin.getBytes("utf-8"), "iso-8859-1");
		System.out.println(fileName);
		
		resp.setContentType(mime);
		resp.setHeader("Content-Disposition", "attachment; filename=" + fileName); 

		FileInputStream fis = new FileInputStream(file);
		OutputStream os = resp.getOutputStream();
		
		int b;
		byte[] bytes = new byte[8192];
		while((b = fis.read(bytes, 0, bytes.length)) != -1 ){
			os.write(bytes, 0, b);
		}
		fis.close();
		os.close();
	}
	
}
