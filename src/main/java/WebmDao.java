import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by User on 08.03.16.
 */
public class WebmDao {

    private static PreparedStatement stmt;

    public void save(Webm web){
        try
        {

            // getting Statement object to execute query
            stmt = DBUtil.getInstance().prepareStatement(
                    "insert into video.webm(duration, height, width, name, nsfw, size, path, thumbnail, tn_height, tn_width)"
                    +"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            stmt.setString(1, web.getDuration());
            stmt.setInt(2, web.getHeight());
            stmt.setInt(3, web.getWidth());
            stmt.setString(4, web.getName());
            stmt.setInt(5, web.getNsfw());
            stmt.setInt(6, web.getSize());
            stmt.setString(7, web.getPath());
            stmt.setString(8, web.getThumbnail());
            stmt.setInt(9, web.getTn_height());
            stmt.setInt(10, web.getTn_width());

            // executing query
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
        }
    }
}
