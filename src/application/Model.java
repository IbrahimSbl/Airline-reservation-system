package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
	public Connection connect(String user,String passw)  {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dbURL = "jdbc:sqlserver://DE\\SQLEXPRESS;databaseName=AirlineSystemDB";
		Connection connect = null;
		try {
			connect= DriverManager.getConnection(dbURL,user, passw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	public ResultSet getData(Connection myConn,String select,String from,String where) {
		String query="";
		if(where.equals("")) {
			query = "SELECT "+select+" FROM "+from;
		}else {
			query = "SELECT "+select+" FROM "+from+" WHERE "+where;
		}
		System.out.println(query);
		Statement statement = null;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet q = null;
		try {
			q = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
	}
	public boolean Insert(Connection myConn,String table,String values) throws SQLException {
		String insertFields = "INSERT INTO "+table+" VALUES ("+values+")";
		System.out.println(insertFields);
		Statement statement = null;
		try {
			statement= myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		statement.execute(insertFields);
		return true;
	}
	
	public void remove(Connection myConn,String table,String where) {
		String query = "DELETE FROM "+table+" WHERE "+where+";";
		System.out.println(query);
		Statement statement = null;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void update(Connection myConn,String table,String set,String where) {
		String query = "UPDATE "+table+" SET "+set+" WHERE "+where+" ;";
		System.out.println(query);
		Statement statement =null;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet executeProcedure(Connection myConn,String procedure) {
		Statement statement = null;
		ResultSet q =null;
		try {
			statement = myConn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			q = statement.executeQuery("EXEC "+procedure);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return q;
	}

}
