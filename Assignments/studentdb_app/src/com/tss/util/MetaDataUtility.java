package com.tss.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MetaDataUtility {
	public static void printDatabaseMetadata(Connection connection) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("=> Database Metadata:");
            System.out.println("Driver Name    : " + metaData.getDriverName());
            System.out.println("Driver Version : " + metaData.getDriverVersion());

            // Get all table names
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Tables in the Database:");
            while (tables.next()) {
                System.out.println(" - " + tables.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printTableMetadata(Connection connection, String tableName) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1");

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            System.out.println("\n=> Table Metadata for: " + tableName);
            System.out.println("Number of Columns: " + columnCount);
            System.out.println("Column Names with Data Types:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(" - " + rsMetaData.getColumnName(i) + " : " + rsMetaData.getColumnTypeName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
