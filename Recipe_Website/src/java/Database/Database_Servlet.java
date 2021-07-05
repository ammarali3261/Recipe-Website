/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ammar
 */
@WebServlet(name = "Database_Servlet", urlPatterns = {"/Database_Servlet"})
public class Database_Servlet extends HttpServlet {

    private void signUp(HttpServletRequest request) throws SQLException {
        System.out.println("writing to db");
        //Adding data to the database using a prepared statement
        Connection conn = DBConnection.connect();
        String sqlInsert = "insert into usercredentials values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ps.setString(1, request.getParameter("emailId"));
        ps.setString(2, request.getParameter("username"));
        ps.setString(3, request.getParameter("password"));

        //executing the sql statement
        ps.execute();
    }

    private boolean singIn(HttpServletRequest request) throws SQLException {
        System.out.println("reading from db");
        Connection conn = DBConnection.connect();

        if (isCorrectUserName(request.getParameter("username"), conn)) {
            System.out.println("username match found");
            String SQL = "SELECT password FROM usercredentials WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, request.getParameter("username"));
            ResultSet rs = ps.executeQuery();
            rs.next();
            //returns true if the username and password are correct and else false
            if (rs.getString("password").equals(request.getParameter("password"))) {
                System.out.println("password match");
                return true;
            }
        }
        return false;
    }

    public boolean isCorrectUserName(String str, Connection conn) throws SQLException {
        String SQL = "SELECT UPPER(username) FROM staff WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, str);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest2(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            signUp(request);
            response.sendRedirect("signIn.html");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void processRequest2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Database_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Database_Servlet with post  at " + request.getParameter("emailId") + request.getParameter("username")
                    + request.getParameter("password") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
