package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.User;

public class UserDao {
	Connection connection = DBConnection.connect();

	public boolean isUserExists(String username) {
		String sql = "SELECT * FROM users WHERE username=?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean validateCredentials(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getUserRole(String username) {
		String sql = "SELECT role FROM users WHERE username=?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("role");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertUser(User user) {
		String sql = "INSERT INTO users(username, password, role) VALUES (?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(new User(rs.getInt("userid"), rs.getString("username"), rs.getString("password"),
						rs.getString("role"), ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}