package ru.ncedu.magpie.basicClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBInterface {
	private static DBInterface instance;
	
	private List<VKUser> users;
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement prep;
	private ResultSet rs;
	
	public static synchronized DBInterface getInstance(){
		if (instance == null) {
			instance = new DBInterface();
		}
		return instance;
	}
	
	// load users from file
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
				tmpUser.setNickname(rs.getString("Nickname"));
				tmpUser.setScreenName(rs.getString("ScreenName"));
				tmpUser.setCity(rs.getString("City"));
				tmpUser.setCountry(rs.getString("Country"));
				tmpUser.setPhotoURL(rs.getString("PhotoURL"));
				tmpUser.setPhoto100URL(rs.getString("Photo100URL"));
				tmpUser.setPhoto200URL(rs.getString("Photo200URL"));
				tmpUser.setOnline(rs.getString("Online"));
				tmpUser.setHasMobile(rs.getString("HasMobile"));
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
			
			PreparedStatement ps= conn.prepareStatement
					("insert into Users (Id, FirstName, LastName, Sex, BirthDate, " +
							"Photo50URL, MobilePhone, HomePhone, Nickname, ScreenName, City, Country, PhotoURL, Photo100URL, Photo200URL, Online, HasMobile) " +
							"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			for (int i = 0; i < users.size(); i++) {
				ps.setString(1, users.get(i).getUserId());
				ps.setString(2, users.get(i).getFirstName());
				ps.setString(3, users.get(i).getLastName());
				ps.setString(4, users.get(i).getUserSex());
				ps.setString(5, users.get(i).getBirthDate());
				ps.setString(6, users.get(i).getPhoto50URL());
				ps.setString(7, users.get(i).getMobilePhone());
				ps.setString(8, users.get(i).getHomePhone());
				ps.setString(9, users.get(i).getNickname());
				ps.setString(10, users.get(i).getScreenName());
				ps.setString(11, users.get(i).getCity());
				ps.setString(12, users.get(i).getCountry());
				ps.setString(13, users.get(i).getPhotoURL());
				ps.setString(14, users.get(i).getPhoto100URL());
				ps.setString(15, users.get(i).getPhoto200URL());
				ps.setString(16, users.get(i).getOnline());
				ps.setString(17, users.get(i).getHasMobile());
			}
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error occured");
		} finally {
			conn.close();
		}
	}
	
	//where to use it?
	public List<VKUser> loadFriends(String filename, String userID) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<String> friendsIds = new ArrayList<String>();
		List<VKUser> friends = new ArrayList<VKUser>();
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
			stat = conn.createStatement();

			PreparedStatement ps = conn.prepareStatement("SELECT FriendId FROM Friends WHERE UserId = ? AND rowid = ?");
			ps.setString(1, userID);
			
			
			ResultSet countrs = stat.executeQuery("SELECT COUNT(*) AS COUNT FROM Friends WHERE UserId = " + userID);
			
			for (int i = 0; i < countrs.getInt(1); i++) {
				ps.setInt(2, i);
				ps.executeUpdate();

				rs = ps.getResultSet();
				friendsIds.add(rs.getString(2));
			}
			
			for (String friendId : friendsIds) {
				rs = stat.executeQuery("SELECT Friend FROM Users WHERE UserId = " + friendId);
				VKUser newFriend = new VKUser();
				newFriend.setBirthDate(rs.getString("BirthDate"));
				newFriend.setCity(rs.getString("City"));
				newFriend.setCountry(rs.getString("Country"));
				newFriend.setFirstName(rs.getString("FirstName"));
				newFriend.setHasMobile(rs.getString("HasMobile"));
				newFriend.setHomePhone(rs.getString("HomePhone"));
				newFriend.setLastName(rs.getString("LastName"));
				newFriend.setMobilePhone(rs.getString("MobilePhone"));
				newFriend.setNickname(rs.getString("Nickname"));
				newFriend.setOnline(rs.getString("Online"));
				newFriend.setPhoto100URL(rs.getString("Photo100URL"));
				newFriend.setPhoto200URL(rs.getString("Photo200URL"));
				newFriend.setPhoto50URL(rs.getString("Photo50URL"));
				newFriend.setPhotoURL(rs.getString("PhotoURL"));
				newFriend.setScreenName(rs.getString("ScreenName"));
				newFriend.setUserId(rs.getString("Id"));
				newFriend.setUserSex(rs.getString("Sex"));
				
				friends.add(newFriend);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			conn.close();
		}
		return friends;
	}
	
	public void saveFriends(String filename, String userID, Collection<VKUser> friends) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
			stat = conn.createStatement();
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Friends WHERE UserId = ?");
			ps.setString(1,userID);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("INSERT INTO Friends (UserId, FriendId) VALUES (?, ?)");
			ps.setString(1, userID);
			for (VKUser friend : friends) {
				ps.setString(2, friend.getUserId());
				ps.executeUpdate();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.close();
		}
	}
}

