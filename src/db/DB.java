package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

	private static Connection connection = null;

	public static Connection getConnection()  {
		try{
			if (connection == null){
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursejdbc?useSSL=false&allowPublicKeyRetrieval=true",
						"developer",
						"developer123");
			}
		}catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		return  connection;
	}

	public static void closeConnection(){
		if (connection != null){
			try{connection.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}

		}
	}

	public static void closeStatement(Statement statement){
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}}
	}

	public static  void closeResultMent(ResultSet resultSet){
		if(resultSet != null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties(){
		try(FileInputStream fileInputStream = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(fileInputStream);
			return properties;
		}
		catch (IOException e){
			throw new DbException(e.getMessage());
		}
	}
}
