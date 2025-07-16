package com.example;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.servlet.ServletException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@MultipartConfig(
	//location = "/usr/local/tomcat/uploads",
	fileSizeThreshold = 1024 * 1024,       // 1 MB in memory
	maxFileSize        = 10 * 1024 * 1024, // 10 MB per file
	maxRequestSize     = 15 * 1024 * 1024  // 15 MB overall
)
public class FileUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		// /usr/local/tomcat/webapps/ROOT/uploads
		String uploadsPath = getServletContext().getRealPath("/uploads");

		// writeable java.io.File with the same path above
		File uploadDir = new File(uploadsPath);
		if(!uploadDir.exists()) uploadDir.mkdirs();
		uploadDir.setWritable(true, false);

		// jakarta.servlet.http.Part chứa metadata và nội dung file
		Part filePart = req.getPart("file");  
		String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		String fullPath = Paths.get("/tmp", filename).toString();

		filePart.write(fullPath);
		res.getWriter().printf("Uploaded as <a href=\"/uploads/%s\">%s</a>",filename, filename);
	}
}

