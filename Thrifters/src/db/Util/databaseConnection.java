package db.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseConnection {
	
	static String user, pass;
	static double crntSav;
	static Connection con;
	
	public static Connection getConnection() {
		String url = "jdbc:sqlite::resource:thriftersDB.db";
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;	
	}
	
	public static void status2() throws SQLException {
		
		String sql = "Update userTb Set status2 = 'online' ";
		PreparedStatement state;
		
		try {
			state = con.prepareStatement(sql);
			state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void usTb(String UN, String US, String ol) throws SQLException {
		String sql = "Insert Into userTb(userName, password, status) values (?,?,?)";
		PreparedStatement state;
		
		state = con.prepareStatement(sql);
		state.setString(1, UN);
		state.setString(2, US);
		state.setString(3, ol);
		state.executeUpdate();
		
	}
	
	public static void logout() throws SQLException {
		String sql = "Update userTb Set status = NULL";
		PreparedStatement state;
		state = con.prepareStatement(sql);
		state.executeUpdate();
	}
	
	public static void logIn() throws SQLException {
		String sql = "Update userTb set status = 'online'";
		PreparedStatement state;
		state = con.prepareStatement(sql);
	
		state.executeUpdate();
	}
	
	
	public static String welcomeUser() throws SQLException {
		String sql = "Select userName FROM userTb where id = (Select MAX(id) from userTb)";
		
		Statement state = con.createStatement();
		ResultSet res;
		
		res = state.executeQuery(sql);
		
		while(res.next()) {
			user = res.getString(1);
		}
		return user;
	}
	
	
	
	public static void accDb(String dt, String tm, double amt, double rs, String sos) throws SQLException {
		String sql = "Insert Into accHistory(date, time, amount, rSaving, sos) values (?, ?, ?, ?, ?)";
		PreparedStatement state = con.prepareStatement(sql);
		
		state.setString(1, dt);
		state.setString(2,  tm);
		state.setDouble(3, amt);
		state.setDouble(4, rs);
		state.setString(5, sos);
		state.executeUpdate();
	}
	
	public static String setCrntSav() throws SQLException {
		String sql = "Select rSaving from accHistory where id = (Select MAX(id) from accHistory)";
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(sql);
		
		while(res.next()) {
			crntSav = res.getDouble(1);
		}
		return Double.toString(crntSav);	
	}
	
	
	public static String loginpass() throws SQLException {
		String sql = "Select password from userTb where id = (Select MAX(id) from userTb)";
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(sql);
		
		while(res.next()) {
			pass = res.getString(1);
		}
		return pass;	
	}
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

