/**
 * Created by User on 06.03.16.
 */
import java.sql.*;

public class DBUtil {
    private static final String url = "jdbc:mysql://localhost:3306/video";
    private static final String user = "root";
    private static final String password = "admin";

    private static java.sql.Connection con;
    private DBUtil() {

    }
    public static java.sql.Connection getInstance() {
        if( con == null ) {
            try
            {
                String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                Class.forName(JDBC_DRIVER);
                con= DriverManager.getConnection(url, user, password);

            }
            catch (SQLException sqlEx)
            {
                sqlEx.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
