import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;


public class MainServlet extends HttpServlet {

    //private static PreparedStatement stmt;
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

        Webm web = new Webm();
        WebmDao dao = new WebmDao();

        web.setDuration(req.getParameter("duration"));
        web.setHeight(Integer.parseInt(req.getParameter("height")));
        web.setWidth(Integer.parseInt(req.getParameter("width")));
        web.setName(req.getParameter("name"));
        web.setNsfw(Integer.parseInt(req.getParameter("nsfw")));
        web.setSize(Integer.parseInt(req.getParameter("size")));
        web.setPath(req.getParameter("path"));
        web.setThumbnail(req.getParameter("thumbnail"));
        web.setTn_height(Integer.parseInt(req.getParameter("tn_height")));
        web.setTn_width(Integer.parseInt(req.getParameter("tn_width")));

        dao.save(web);
        PrintWriter out = resp.getWriter();
        //out.print("<html>"+book+"<br>"+author+"</html>");

       /* try
        {

            // getting Statement object to execute query
            stmt = DBUtil.getInstance().prepareStatement("insert into video.catvideo (id, name, file)"+"values (3, ?, ?);");

            stmt.setString(1, duration);
            stmt.setInt(2, height);

            // executing SELECT query
            stmt.executeUpdate();


        }
        catch (SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }


        finally {
        //close connection ,stmt and resultset here

        try { stmt.close(); } catch(SQLException se) {  System.out.println("Fuck!"); }
        //try { rs.close(); } catch(SQLException se) {  }
    }*/

}
}











