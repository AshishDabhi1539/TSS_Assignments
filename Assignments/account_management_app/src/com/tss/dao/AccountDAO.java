package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.Account;

public class AccountDAO {
	private Connection connection;

	public AccountDAO() {
		super();
		this.connection = DBConnection.connect();
	}

	public void insertAccount(Account acc) {
		String sqlQuery = "INSERT INTO accounts (id, name, balance) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			preparedStatement.setInt(1, acc.getId());
			preparedStatement.setString(2, acc.getName());
			preparedStatement.setDouble(3, acc.getBalance());
			preparedStatement.executeUpdate();
			System.out.println("Account added.");
		} catch (SQLException e) {
			System.out.println("Error inserting account: " + e.getMessage());
		}
	}

	public List<Account> getAllAccounts() {
		List<Account> list = new ArrayList<>();
		try (Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM accounts")) {
			while (result.next()) {
				Account account = new Account(result.getInt("id"), result.getString("name"),
						result.getDouble("balance"));
				list.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean transferAmount(int senderId, int receiverId, double amount) {
		String deductSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
		String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
		Savepoint debitSavepoint = null;

		try {
			connection.setAutoCommit(false);

			PreparedStatement checkStmt = connection.prepareStatement("SELECT balance FROM accounts WHERE id = ?");
			checkStmt.setInt(1, senderId);
			ResultSet rs = checkStmt.executeQuery();

			if (!rs.next()) {
				System.out.println("Sender not found.");
				return false;
			}

			double senderBalance = rs.getDouble("balance");
			if (senderBalance < amount) {
				System.out.println("Insufficient balance in sender's account.");
				return false;
			}

			PreparedStatement debitStmt = connection.prepareStatement(deductSQL);
			debitStmt.setDouble(1, amount);
			debitStmt.setInt(2, senderId);
			int debitRows = debitStmt.executeUpdate();

			if (debitRows == 0) {
				System.out.println("Debit failed.");
				return false;
			}

			debitSavepoint = connection.setSavepoint("AfterDebit");

			PreparedStatement creditStmt = connection.prepareStatement(creditSQL);
			creditStmt.setDouble(1, amount);
			creditStmt.setInt(2, receiverId);
			int creditRows = creditStmt.executeUpdate();

			if (creditRows == 0) {
				System.out.println("Receiver not found. Rolling back to savepoint (keeping debit).");
				connection.rollback(debitSavepoint);
				connection.commit();
				return false;
			}

			connection.commit();
			System.out.println("Transfer successful.");
			return true;

		} catch (SQLException e) {
			try {
				System.out.println("Exception occurred. Rolling back.");
				if (debitSavepoint != null) {
					connection.rollback(debitSavepoint);
					connection.commit();
				} else {
					connection.rollback();
				}
			} catch (SQLException rollbackEx) {
				System.out.println("Rollback failed: " + rollbackEx.getMessage());
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
