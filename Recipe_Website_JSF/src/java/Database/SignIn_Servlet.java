/*
 * This is the servlet responsible for signing in 
 * This servlet contacts the database and verifies 
 * if the user credentials are correct.
 */
package Database;

//importing required libraries
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ammar ALi Moazzam MISIS: M00696114
 */
@WebServlet(name = "SignIn_Servlet", urlPatterns = {"/SignIn_Servlet"})
public class SignIn_Servlet extends HttpServlet {

    //Overriding the doGet ethod to process the GET request of the clients
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println(request.getParameter("username"));
            //Checking if sign in is successfull or not
            singIn(request, response);
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean singIn(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        //creates an instance of printWriter to write response to the client
        PrintWriter out = response.getWriter();
        System.out.println("reading from db");
        //Connecting to the database
        Connection conn = DBConnection.connect();
        System.out.println("Connected");
        //getting email id from the request parameter
        System.out.println(request.getParameter("emailId"));

        //chhecking if the email id provided is registered in our database
        if (isCorrectEmail(request.getParameter("emailId"), conn)) {
            System.out.println("emailId match found");
            //if the emial id is found
            //then check if the password provided matches the password stored in the database
            String SQL = "SELECT password FROM usercredentials WHERE emailId = ?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, request.getParameter("emailId"));
            ResultSet rs = ps.executeQuery();
            rs.next();
            //returns true if the username and password are correct or else false
            if (rs.getString("password").equals(request.getParameter("password"))) {
                System.out.println("password match");
                conn.close();
                out.write("success");
                out.close();
                return true;
            } else {
                out.write("Incorrect Password");
                conn.close();
                out.close();
                return false;
            }
        } else {
            out.write("Invalid Email ID");
            conn.close();
            out.close();
            return false;
        }
    }

    //Method to check if the emailID provided by the user is present in out database
    public boolean isCorrectEmail(String str, Connection conn) throws SQLException {
        //SQL Query to check if the provided email id is valid
        String SQL = "SELECT LOWER(emailId) FROM usercredentials WHERE emailId = ?";
        System.out.println("searching");
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, str);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            //return true if a match is found
            System.out.println("found");
            return true;
        } else {
            //return false if the emailid is not in our database
            System.out.println("not found");
            return false;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
