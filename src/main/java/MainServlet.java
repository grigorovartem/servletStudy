import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;


public class MainServlet extends HttpServlet {


    //private static Connection con;
    private static PreparedStatement stmt;
    //private static ResultSet rs;


    //@Override
    /*protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.print("<h1>Hello Servlet</h1>");
    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //JavaToMySQL.dbConnector("insert into video.catvideo (id, name, file)"+"values (3, 'abc', '123');");

        String book = req.getParameter("book");
        String author = req.getParameter("author");

        PrintWriter out = resp.getWriter();
        out.print("<html>"+book+"<br>"+author+"</html>");


       // String query = "insert into video.catvideo (id, name, file)"+"values (3, ?, ?);";
       // out.print("  "+query);


        try
        {

            // getting Statement object to execute query
            stmt = Connection.getInstance().prepareStatement("insert into video.catvideo (id, name, file)"+"values (3, ?, ?);");

            stmt.setString(1, book);
            stmt.setString(2, author);

            // executing SELECT query
            stmt.executeUpdate();


        }
        catch (SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }


        finally {//тут какой-то бок
        //close connection ,stmt and resultset here

        try { stmt.close(); } catch(SQLException se) {  System.out.println("Fuck!"); }
        //try { rs.close(); } catch(SQLException se) {  }
    }

}
}











