package vn.iostar.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category;
import vn.iostar.service.ICategoryService;
import vn.iostar.service.impl.CatergoryServiceImpl;
import vn.iostar.util.constants;

@MultipartConfig()


@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",


 "/admin/category/edit", "/admin/category/update", "/admin/category/delete" })

public class AdminController extends HttpServlet  {


	 private static final long serialVersionUID = 1L;

	 public ICategoryService cateService = new CatergoryServiceImpl();

	 @Override

	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	 String url = req.getRequestURI();

	 if (url.contains("/admin/categories")) {

	 List<Category> list = cateService.findAll();

	 req.setAttribute("listcate", list);

	 req.getRequestDispatcher("/resources/views/admin/category-list.jsp").forward(req, resp);

	 } else if (url.contains("/admin/category/add")) {

	 req.getRequestDispatcher("/resources/views/admin/category-add.jsp").forward(req, resp);

	 } else if (url.contains("/admin/category/edit")) {

	 int id = Integer.parseInt(req.getParameter("id"));

	 Category category = null;
	try {
		category = cateService.findById(id);
	} catch (Exception e) {
		e.printStackTrace();
	}

	 req.setAttribute("cate", category);

	 req.getRequestDispatcher("/resources/views/admin/category-edit.jsp").forward(req, resp);

	 } else {

	 int id = Integer.parseInt(req.getParameter("id"));

	 try {

	 cateService.delete(id);

	 } catch (Exception e) {

	 // TODO Auto-generated catch block
	 e.printStackTrace();

	 }

	 // chuyển trang

	 resp.sendRedirect(req.getContextPath() + "/admin/categories");

	 }

	 }

	 @Override

	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	 String url = req.getRequestURI();

	 if (url.contains("/admin/category/insert")) {


	 String categoryname = req.getParameter("categoryname");

	 boolean status = Boolean.parseBoolean(req.getParameter("status"));

	 String images = req.getParameter("images");


	 Category category = new Category();

	 category.setCategoryname(categoryname);

	 category.setStatus(status);

	 String fname = "";

	 String uploadPath = constants.DIR; 

	 File uploadDir = new File(uploadPath);

	 if (!uploadDir.exists()) {
		uploadDir.mkdir();
	}
	 try {

	 Part part = req.getPart("images1");

	 if (part.getSize() > 0) {

	 String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

	 int index = filename.lastIndexOf(".");

	 String ext = filename.substring(index + 1);

	 fname = System.currentTimeMillis() + "." + ext;

	 part.write(uploadPath + "/" + fname);

	 category.setImages(fname);

	 } else if (images != null) {

	 category.setImages(images);

	 } else {

	 category.setImages("avatar.png");

	 }

	 } catch (FileNotFoundException fne) {

	 fne.printStackTrace();

	 }

	 cateService.insert(category);


	 resp.sendRedirect(req.getContextPath() + "/admin/categories");

	 }

	 if (url.contains("/admin/category/update")) {


	 int categoryid = Integer.parseInt(req.getParameter("categoryid"));

	 String categoryname = req.getParameter("categoryname");

	 boolean status = Boolean.parseBoolean(req.getParameter("status"));

	 String images = req.getParameter("images");


	 Category category = null;
	try {
		category = cateService.findById(categoryid);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 String fileold = category.getImages();

	 category.setCategoryname(categoryname);

	 category.setStatus(status);

	 String fname = "";

	 String uploadPath = constants.DIR; // upload vào thư mục bất kỳ

	 File uploadDir = new File(uploadPath);

	 if (!uploadDir.exists()) {
		uploadDir.mkdir();
	}

	 try {

	 Part part = req.getPart("images1");

	 if (part.getSize() > 0) {


	 if (!category.getImages().substring(0, 5).equals("https") ) {

	 deleteFile(uploadPath+ "\\" + fileold);

	 }

	 String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

	 int index = filename.lastIndexOf(".");

	 String ext = filename.substring(index + 1);

	 fname = System.currentTimeMillis() + "." + ext;

	 part.write(uploadPath + "/" + fname);

	 category.setImages(fname);

	 } else if (images != null) {

	 category.setImages(images);

	 } else {

	 category.setImages(fileold);

	 }

	 } catch (FileNotFoundException fne) {

	 fne.printStackTrace();

	 }

	 try {
		cateService.update(category);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 // chuyển trang

	 resp.sendRedirect(req.getContextPath() + "/admin/categories");

	 }

	 }

	 public static void deleteFile(String filePath) throws IOException {

	 Path path = Paths.get(filePath);

	 Files.delete(path);

	 }


}

