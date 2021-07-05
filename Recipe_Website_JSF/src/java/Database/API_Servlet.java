/*
 * This is the servlet that gets user request
 * calls the API to search for the requested
 * recipe. This servlet gets results from the
 * API call and parses them using JSON Parser.
 * The parsed data is then structured in HTML
 * format and an HTML page is sent back to the
 * client as a response. The page contains all
 * the search results along with their images
 * and the user is redirected to the source
 * website if they click on the image.
 */
package Database;

//importing all the required libraries
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ammar Ali Moazzam
 * MISIS: M00696114
 */

public class API_Servlet extends HttpServlet {

    //Overriding the doGet ethod to process the GET request of the clients
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //app id for the API
            String appId = "95f29795";
            
            //app key for the API
            String appKey = "afe2b0893e84bfad19b50b9689b35a77";

            //getting search query from the client request
            String search = request.getParameter("search-bar");
            System.out.println("printing search");
            System.out.println(search);
            search = search.trim();
            //replacing all the spaces in the search query with %20 to be able to search using it
            search = search.replaceAll("\\s", "%20");
            System.out.println("searching for " + search);

            //actually calling the API using the search query
            String url = "https://api.edamam.com/search?q=" + search + "&app_id=" + appId + "&app_key=" + appKey;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //using GET request
            con.setRequestMethod("GET");
            //fetching the response code
            int responseCode = con.getResponseCode();

            System.out.println("sending req to " + url);
            System.out.println("resp code : " + responseCode);

            //reading the results from the API
            Scanner input = new Scanner(obj.openStream());
            String inputLine = "";

            while (input.hasNext()) {
                inputLine += input.nextLine();
            }

            input.close();

            //creates an instance of printWriter to write response to the client
            PrintWriter out = response.getWriter();

            //Writing HTML code that will be sent to the user as a response
            out.println("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "    <head>\n"
                    + "        <meta charset=\"utf-8\">\n"
                    + "            <title>Recipe Website</title>\n"
                    + "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "            <link rel=\"stylesheet\" href=\"styles.css\">\n"
                    + "\n"
                    + "            <!-- jQuery 1.7.2+ or Zepto.js 1.0+ -->\n"
                    + "            <script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>\n"
                    + "\n"
                    + "            <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\" integrity=\"sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp\" crossorigin=\"anonymous\">\n"
                    + "            <script src=\"https://kit.fontawesome.com/a781a7e36b.js\" crossorigin=\"anonymous\"></script>\n"
                    + "            <script src=\"onClickMenu.js\"></script>\n"
                    + "            <script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js\"></script>\n"
                    + "            <script src=\"magnefic/jquery.magnific-popup.min.js\"></script>\n"
                    + "            <link rel=\"stylesheet\" href=\"magnefic/magnific-popup.css\">\n"
                    + "            <script src=\"magnefic/magnefic.js\"></script>\n"
                    + "            <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">\n"
                    + "            <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"
                    + "            <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx\" crossorigin=\"anonymous\"></script>\n"
                    + ""
                    + "    </head>\n"
                    + "\n"
                    + "    <body>\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "        <nav>\n"
                    + "            <input id=\"nav-toggle\" type=\"checkbox\" />\n"
                    + "            <div class=\"logo\">Recipe<strong>Website</strong></div>\n"
                    + "            <ul class=\"links\">\n"
                    + "                <li><a href=\"index.html\">Home</a></li>\n"
                    + "                <li><a href=\"signIn.html\">Sign In</a></li>\n"
                    + "                <li><a href=\"signUp.html\">Sign Up</a></li>\n"
                    + "                <li><a href=\"aboutUs.xhtml\">About Us</a></li>\n"
                    + "\n"
                    + "            </ul>\n"
                    + "            <label for=\"nav-toggle\" class=\"icon-burger\">\n"
                    + "                <div class=\"line\"></div>\n"
                    + "                <div class=\"line\"></div>\n"
                    + "                <div class=\"line\"></div>\n"
                    + "            </label>\n"
                    + "        </nav>\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "        <!--Header Begin===================-->\n"
                    + "        <header class=\"header\">\n"
                    + "            <div class=\"hero\">\n");
            
            //Setting the search query as the title of the page
            String title = "Showing Search Results for " + request.getParameter("search-bar");
            out.println("                <h1 class=\"title\">" + title + "</h1>\n"
                    + "            </div>\n"
                    + "        </header>\n"
                    + "        <!--Header End=====================-->\n"
                    + "\n");
            out.println("<!--Cards Section Begins=================================-->\n"
                    + "        <section id=\"food\">\n"
                    + "            <div>\n");
            
            //Using JSON Parser to parse the results from the API
            String heading = "";
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(inputLine);
            //getting all the results
            JSONArray hits = (JSONArray) json.get("hits");

            //Creating arrays to store info regarding each recipe
            String[] label = new String[10];
            String[] image = new String[10];
            String[] info = new String[10];
            String[] link = new String[10];

            //checking if the result contain any output
            if (hits.size() == 0) {
                heading = "Oops!! No results found for " + request.getParameter("search-bar").toUpperCase();
            } else {
                heading = request.getParameter("search-bar").toUpperCase();
            }

            //getting all the recipes 
            for (int i = 0; i < hits.size(); i++) {
                String info_temp = "Ingredients: ";
                JSONObject recipe = (JSONObject) hits.get(i);

                //getting the recipe object
                JSONObject recipe1 = (JSONObject) recipe.get("recipe");

                //Getting the recipe name
                label[i] = recipe1.get("label").toString();
                System.out.println("Recipe Label: " + label);

                link[i] = recipe1.get("url").toString();
                System.out.println("URL: " + link);

                //Getting the recipe image
                image[i] = recipe1.get("image").toString();
                System.out.println("Image URI: " + image);

                System.out.println("Ingredients: ");
                //Getting the recipe ingredients
                JSONArray ingredientLines = (JSONArray) recipe1.get("ingredientLines");
                
                //for loop to store the ingredient lines as asingle string in an array
                for (int j = 0; j < ingredientLines.size(); j++) {
                    String ing = ingredientLines.get(j).toString();
                    String temp = "";
                    
                    //checks if the sentence is too long and divides it into 2 shorter sentences
                    if (ing.length() > 80) {
                        String[] strArray = ing.split(" ");
                        int wordCount = strArray.length;
                        int halfSent = Math.round(wordCount / 2);

                        for (int k = 0; k < halfSent; k++) {
                            temp += strArray[k] + " ";
                        }
                        temp += "\n";
                        for (int l = halfSent; l < wordCount; l++) {
                            temp += strArray[l] + " ";
                        }
                        ing = temp;
                    }
                    System.out.println(ing);
                    //storing everything in info temp
                    info_temp += ing + "\n";
                }

                //getting the calories from the results
                double calories = Double.parseDouble(recipe1.get("calories").toString());
                System.out.println("Total Calories: " + calories);
                info_temp += "\nTotal Calories: " + calories + "\n";

                //getting the time from the results
                double totalTime = Double.parseDouble(recipe1.get("totalTime").toString());
                if (totalTime == 0.0) {
                    System.out.println("Total Time: Unknown");
                    info_temp += "\nTotal Time: Unknown";
                } else {
                    System.out.println("Total Time: " + totalTime);
                    info_temp += "\nTotal Time: " + totalTime + " minutes\n";
                }

                System.out.println("");
                System.out.println("");
                System.out.println("");
                info[i] = info_temp;
            }
            
            //setting the name of the recipe as the heading
            out.println("                <h2 class=\"title-text\">" + heading + "</h2>\n"
                    + "            </div>\n"
                    + "            <div >\n");

            //Using a for loop to create food cards in HTML to show the output
            //The number of cards is equal to the number of recipe results
            for (int i = 0; i < hits.size(); i++) {
                out.println("                <!--Article Begins==================-->\n"
                        + "                <article class=\"food-card\">\n"
                        + "                <a href=" + link[i] + " target=\"_blank\">"
                        + "                    <img src=" + image[i] + " class=\"food-img\" alt=\"\">\n"
                        + "                    </a>\n"
                        + "                    <div class=\"img-text\">\n"
                        + "                        <h1>" + label[i] + "</h1>\n"
                        + "                        <pre style=\"font-size:24px\"><b>" + info[i] + "</pre>\n"
                        + "                    </div>\n"
                        + "\n"
                        + "                </article>\n"
                        + "                <!--Article Ends==================-->\n");
            }

            //The remaining HTML code for the website
            //that contains the social media icons and the footer
            out.println("            </div>\n"
                    + "        </section>\n"
                    + "        <!--Cards Section Ends=================================-->\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "        <!--Social Media Icons Begin===========================-->\n"
                    + "        <section id=\"social-icons\">\n"
                    + "            <a><i class=\"fab fa-facebook facebook\"></i></a>\n"
                    + "            <a><i class=\"fab fa-twitter twitter\"></i></a>\n"
                    + "            <a><i class=\"fab fa-instagram instagram\"></i></a>\n"
                    + "            <a><i class=\"fab fa-google-plus plus\"></i></a>\n"
                    + "        </section>    \n"
                    + "        <!--Social Media Icons End===========================-->\n"
                    + "\n"
                    + "        <!--Footer--============================ -->\n"
                    + "        <footer class=\"footer\">\n"
                    + "            <div class=\"section-center\">\n"
                    + "                <p class=\"text\">\n"
                    + "                    &copy;<span>Recipe Website</span>\n"
                    + "                </p>\n"
                    + "            </div>\n"
                    + "        </footer>\n"
                    + "        <!--Footer Ending--============================ -->\n"
                    + "    </body>\n"
                    + "\n"
                    + "</html>");
            out.close();

            //catching the exceptions and printing them for easier debugging
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
