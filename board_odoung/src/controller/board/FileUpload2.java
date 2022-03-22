package controller.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/file2")
public class FileUpload2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("form.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDir = "D:\\upload";
		int size = 10 * 1024 * 1024; 
		
		File currentDir = new File(saveDir);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDir);
		factory.setSizeThreshold(size);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for(FileItem fi : items) {
				if (fi.isFormField()) {
					System.out.println(fi.getFieldName() + " = " + fi.getString("utf-8"));
				}
				else {
					System.out.println(fi.getFieldName());
					
//					System.out.println(fi.getName()); //얘가 실제 파일이름 
					String origin = fi.getName(); //이 오리진에서 ex)free.html 이면 free만 가져오니까 확장자를 넣어줘야함
					System.out.println(origin);
					String ext = origin.substring(origin.lastIndexOf("."));
					
					UUID uuid = UUID.randomUUID();
					String name = uuid + ext;
					
					System.out.println(fi.getSize());
					
//					fi.write(new File(currentDir + "\\" + name));
					//currentDir 밑에 년 밑에 월 밑에 일 밑에 저장시킬거임
//					fi.write(new File(currentDir + "\\" + getTodayStr() + name)); //FileNotFoundException (실제 년/월/일 파일이 없어서)
					
					File upPath = new File(currentDir + "\\" + getTodayStr());
					if(!upPath.exists()) {
						upPath.mkdirs(); //dirs중요
					}
					fi.write(new File(upPath, name)); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//오늘 날짜에 대한 연,월,일 형태의 정보를 가져옴
	private String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
}
