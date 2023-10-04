package com.pkg.falcon;

import com.dbcon.DbCon;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig
public class UploadPic extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException, IOException {
       HttpSession session= req.getSession();
        String email2=(String)session.getAttribute("session_email");
        PrintWriter out= resp.getWriter();
        out.println(email2);
        Part p = req.getPart("files");
        String fileName = p.getSubmittedFileName();
        try{
            Connection con=DbCon.getConnect();
            PreparedStatement ps = con.prepareStatement
                    ("update profile_pics set path=? where email=?");

            ps.setString(1, fileName);
            ps.setString(2, email2);

            int i = ps.executeUpdate();

            if (i > 0) {

                String path = getServletContext().getRealPath("") + "images";

                File file = new File(path);
                System.out.println(path);

                p.write(path + File.separator + fileName);
                session.setAttribute("session_pic", path);
                session.setAttribute("session_filename", fileName);



                resp.sendRedirect("Profile.jsp");

            } else {
                System.out.println("Error in server");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }




//        try {
//
//            Connection con= DbCon.getConnect();
//
//            PreparedStatement ps = con.prepareStatement("insert into img_details(img_name,remark) values(?,?)");
////            ps.setString(1, fileName);
////            ps.setString(2, remark);
//
//            int i = ps.executeUpdate();
//
//            if (i == 1) {
//
//                String path = getServletContext().getRealPath("") + "imgs";
//
//                File file = new File(path);
//
//                p.write(path + File.separator + fileName);
//
//                session.setAttribute("msg", "Upload Sucess");
//
//                resp.sendRedirect("index.jsp");
//
//            } else {
//                System.out.println("Error in server");
//            }
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }

}
