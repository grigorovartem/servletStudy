import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class MainServlet extends HttpServlet {

    private static final String url = "jdbc:mysql://localhost:3306/video";
    private static final String user = "root";
    private static final String password = "admin";

    private static Connection con;
    private static Statement stmt;
    //private static ResultSet rs;

    public class MySingleton {
        private static MySingleton instance;
        private MySingleton() {
        }
        public static MySingleton getInstance() {
            if( instance == null ) {
                instance = new MySingleton();
            }
            return instance;
        }
    }

    //private static Connection con = Connection.getInstance();



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


        String query = "insert into video.catvideo (id, name, file)"+"values (3, '"+book+"', '"+author+"');";
        out.print(query);


        Connection con = Connection.getInstance();


        try
        {
            // opening database connection to MySQL server
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(JDBC_DRIVER);

            //Connection c = Connection.getInstance();
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            stmt.executeUpdate(query);

            //PrintWriter out = resp.getWriter();
            //out.print("<h1>Hello TRY!!!</h1>");


        }
        catch (SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }


        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {//тут какой-то бок
        //close connection ,stmt and resultset here
        try { con.close(); } catch(SQLException se) { System.out.println("Fuck!"); }
        try { stmt.close(); } catch(SQLException se) {  System.out.println("Fuck!"); }
        //try { rs.close(); } catch(SQLException se) {  }
    }
        //PrintWriter out = resp.getWriter();
        //out.print(book);*/




}
}











