package vn.iostar.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import vn.iostar.entity.Video;
import vn.iostar.service.IVideoService;
import vn.iostar.service.impl.VideoServiceImpl;
import vn.iostar.util.constants;

@MultipartConfig()

@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/insert", "/admin/video/add", "/admin/video/search" })
public class AdminControllerVideo extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public IVideoService vdService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("videos")) {
            List<Video> list = vdService.findAll();
            req.setAttribute("listvd", list);
            req.getRequestDispatcher("/resources/views/admin/video-list.jsp").forward(req, resp);

        } else if (url.contains("insert")) {
            req.getRequestDispatcher("/resources/views/admin/video-insert.jsp").forward(req, resp);
        }else if (url.contains("search")) {
            req.getRequestDispatcher("/resources/views/admin/video-search.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (url.contains("add")) {
            String vdname = req.getParameter("vdname");
            String description = req.getParameter("vddescription");
            boolean Active = Boolean.parseBoolean(req.getParameter("status"));
            String images = req.getParameter("poster");

            int categoryID = 0;
            try {
                categoryID = Integer.parseInt(req.getParameter("categoryID"));
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid category ID!");
                req.getRequestDispatcher("/resources/views/admin/video-insert.jsp").forward(req, resp);
                return;
            }

            // Đưa dữ liệu vào model
            Video video = new Video();
            video.setTitle(vdname);
            video.setDescription(description);
            video.setActive(Active);

            Category category = new Category();
            category.setCategoryId(categoryID);
            video.setCategory(category);

            String fname = "";
            String uploadPath = constants.DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                Part part = req.getPart("poster");
                if (part != null && part.getSize() > 0) {
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = filename.lastIndexOf(".");
                    String ext = filename.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fname);
                    video.setPoster(fname);
                } else if (images != null && !images.isEmpty()) {
                    video.setPoster(images);
                } else {
                    video.setPoster("avatar.png");
                }

                vdService.insert(video);
                resp.sendRedirect(req.getContextPath() + "/admin/videos");

            } catch (FileNotFoundException fne) {
                fne.printStackTrace();
                req.setAttribute("errorMessage", "File not found!");
                req.getRequestDispatcher("/resources/views/admin/video-insert.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "An error occurred while processing!");
                req.getRequestDispatcher("/resources/views/admin/video-insert.jsp").forward(req, resp);
            }
        }
        String title = req.getParameter("title");
        if (url.contains("search"))
        {
        	Video vd = null ; 
        	try {     		
				 vd = vdService.findByVideoname(title);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	req.setAttribute("vd", vd); 
            req.getRequestDispatcher("/resources/views/admin/video-search.jsp").forward(req, resp);
        }
    }
}
