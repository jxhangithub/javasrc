package database.jdbc;

import java.sql.*;

import com.darwinsys.sql.ConnectionUtil;

public class ResultSetUpdate {
    public static void main(String args[]) {

        Connection con;
        Statement stmt;
		ResultSet rs;

        try {
            con = ConnectionUtil.getConnection(args[0]);
			stmt = con.createStatement(
			ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM Users where nick=\"ian\"");

			// Get the resultset ready, update the passwd field, commit
			rs.first();
			rs.updateString("password", "unguessable");
			rs.updateRow();

			rs.close();
			stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
	}
}
