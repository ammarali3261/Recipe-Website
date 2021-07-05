/*
 * This is the servlet for processing the GET request
 * from the about us page. This servlet connects to 
 * the database and reads data from the table. Once it
 * reads the data, it then sends the data back as a 
 * response to the client.
 */
package Database;

//importing the necessary libraries
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ammar Ali Moazzam
 */
public class AboutUs_Servlet extends HttpServlet {

    //Overriding the doGet ethod to process the GET request of the clients
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //creates an instance of printWriter to write response to the client
        PrintWriter out = response.getWriter();
        String info = "Cooking is a process that often becomes a great pastime for many people. Many cooking schools and chefs offer their services through the web. Our vision is to provide authentic and amazing recipes to everyone. Everyone can cook with the help of our webisite. Forget ordering food and cook your own food. Cook healthy and tasty food in the comfort of your kitchen.";
        //writing the data back to the client
        out.write(info);
        out.close();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
