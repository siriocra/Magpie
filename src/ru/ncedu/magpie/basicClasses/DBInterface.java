package ru.ncedu.magpie.basicClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBInterface {
	private static DBInterface instance;
	
	private List<VKUser> users;
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement prep;
	private ResultSet rs;
	
	public static synchronized DBInterface getInstance() throws Exception {
		if (instance == null) {
			instance = new DBInterface();
		}
		return instance;
	}
	
	// load database from file
	public void loadDB (String filename) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
			stat = conn.createStatement();

			rs = stat.executeQuery("SELECT COUNT(*) AS COUNT FROM Users");
			System.out.println("Number of users" + rs.getInt(1));

			VKUser tmpUser = new VKUser();
			for (int i = 0; i < rs.getInt(1); i++) {
				
				rs = stat.executeQuery("SELECT * FROM Users WHERE rowid = " + i);
				tmpUser.setUserId(rs.getString("Id"));
				tmpUser.setFirstName(rs.getString("FirstName"));
				tmpUser.setLastName(rs.getString("LastName"));
				tmpUser.setUserSex(rs.getString("Sex"));
				tmpUser.setBirthDate(rs.getString("BirthDate"));
				tmpUser.setPhoto50URL(rs.getString("Photo50URL"));
				tmpUser.setMobilePhone(rs.getString("MobilePhone"));
				tmpUser.setHomePhone(rs.getString("HomePhone"));
				// need to add list of his friends
				users.add(tmpUser);
				System.out.println("added new user " + tmpUser.getFirstName() + " " + tmpUser.getLastName() );
			}

		} catch (SQLException e) {
			System.out.println("Error occured");
		} finally {
//			rs.close();
//			prep.close();
//			stat.close();
			conn.close();
		}
	}
	
	public void saveDB(String filename) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
			stat = conn.createStatement();
			
			PreparedStatement ps= conn.prepareStatement("insert into Users (Id) values (?, ?, ?)");
			
			ps.setString(1,"125"// users.get(0).getUserId()
					);
			
			ps.executeUpdate();
	/*		
			for (int i = 0; i < users.size(); i++) {
				rs = stat.executeQuery("INSERT INTO 'Users' "
						+ "('Id', 'FirstName', 'LastName', 'Sex', 'BirthDate', 'Photo50URL', 'MobilePhone', 'HomePhone')"
						+ "VALUES (" +  users.get(i).getUserId() + "," + 
										users.get(i).getFirstName() + "," +
										users.get(i).getLastName() + "," +
										users.get(i).getUserSex() + "," +
										users.get(i).getBirthDate() + "," +
										users.get(i).getPhoto50URL() + "," +
										users.get(i).getMobilePhone() + "," +
										users.get(i).getHomePhone() +
										")");
			}*/
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error occured");
		} finally {
			conn.close();
		}
	}
}
