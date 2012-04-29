package ru.ncedu.magpie.basicClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for working with database
 * @author IndisN
 */
public class DBInterface {
	private static DBInterface instance;
	
	private Connection conn;
	private Statement stat;
	//private PreparedStatement prep;
	private ResultSet rs;
	
	private static final Logger logger = LoggerFactory.getLogger(DBInterface.class);
	
	public static synchronized DBInterface getInstance(){
		if (instance == null) {
			instance = new DBInterface();
		}
		return instance;
	}
	
	/**
	 * Loads users from file
	 * @return Collection of users from database
	 * @throws SQLException
	 */
	public Collection<VKUser> loadDB () throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException", e);
		}
		
		Collection<VKUser> users = new ArrayList<VKUser>();
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + "magpieDB.sqlite");
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
			logger.error("SQLException", e);
		} finally {
//			rs.close();
//			prep.close();
//			stat.close();
			conn.close();
		}
		return users;
	}
	
	/**
	 * Saves users to file
	 * @param users
	 * @throws SQLException
	 */
	public void saveDB(Collection<VKUser> users) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException", e);
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + "magpieDB.sqlite");
			stat = conn.createStatement();
			
			PreparedStatement ps= conn.prepareStatement
					("insert into Users (Id, FirstName, LastName, Sex, BirthDate, " +
							"Photo50URL, MobilePhone, HomePhone, Nickname, ScreenName, City, Country, PhotoURL, Photo100URL, Photo200URL, Online, HasMobile) " +
							"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			for (VKUser user : users){
				ps.setString(1, user.getUserId());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				ps.setString(4, user.getUserSex());
				ps.setString(5, user.getBirthDate());
				ps.setString(6, user.getPhoto50URL());
				ps.setString(7, user.getMobilePhone());
				ps.setString(8, user.getHomePhone());
				ps.setString(9, user.getNickname());
				ps.setString(10, user.getScreenName());
				ps.setString(11, user.getCity());
				ps.setString(12, user.getCountry());
				ps.setString(13, user.getPhotoURL());
				ps.setString(14, user.getPhoto100URL());
				ps.setString(15, user.getPhoto200URL());
				ps.setString(16, user.getOnline());
				ps.setString(17, user.getHasMobile());
			}
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.error("SQLException", e);
		} finally {
			conn.close();
		}
	}
	/**
	 * Loads VK user's friends from database
	 * @param userID
	 * @return Collection of friends
	 * @throws SQLException
	 */
	public Collection<VKUser> loadFriends(String userID) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException", e);
		}
		
		Collection<String> friendsIds = new ArrayList<String>();
		Collection<VKUser> friends = new ArrayList<VKUser>();
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + "magpieDB.sqlite");
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
			logger.error("SQLException", e);
		}
		finally {
			conn.close();
		}
		return friends;
	}
	
	/**
	 * Saves user's friends to database
	 * @param userID
	 * @param friends
	 * @throws SQLException
	 */
	public void saveFriends(String userID, Collection<VKUser> friends) throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException", e);
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + "magpieDB.sqlite");
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
			logger.error("SQLException", ex);
		} finally {
			conn.close();
		}
	}
}

