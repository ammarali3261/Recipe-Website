/*
 * This is the servlet that is used to sign up 
 * a user. The user details are added into to 
 * the database so that the user can sign in. 
 */
package Database;

//importing required libraries
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ammar Ali Moazzam
 * MISIS: M00696114
 */
@WebServlet(name = "SignUp_Servlet", urlPatterns = {"/SignUp_Servlet"})
public class SignUp_Servlet extends HttpServlet {

    //Method to sign up
    private void signUp(HttpServletRequest request) throws SQLException {
        System.out.println("writing to db");
        //Adding data to the database using a prepared statement
        Connection conn = DBConnection.connect();
        String sqlInsert = "insert into usercredentials values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ps.setString(1, request.getParameter("emailId").toLowerCase());
        ps.setString(2, request.getParameter("username").toLowerCase());
        ps.setString(3, request.getParameter("password"));

        //executing the sql statement
        ps.execute();
        conn.close();
    }

    //Overriding the doPost ethod to process the POST request of the clients
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //call the sigup method
            signUp(request);
            PrintWriter out = response.getWriter();
            out.write("success");
            out.close();
            
            //defines what happens if the user tries to signup using a duplicate email.
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            //creates an instance of printWriter to write response to the client
            PrintWriter out = response.getWriter();
            out.write("Duplicate Email");
            out.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
