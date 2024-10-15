package vn.iostar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.util.constants;


@WebServlet(urlPatterns = {"/images"})
public class Download extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
	 String fileName = req.getParameter("fname");
	 File file = new File(constants.DIR + "/" + fileName);
	 resp.setContentType("image/jpeg");
	 if (file.exists()) {
	 IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
	 }
	 }
	 @Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.doPost(req, resp);
		}
}
