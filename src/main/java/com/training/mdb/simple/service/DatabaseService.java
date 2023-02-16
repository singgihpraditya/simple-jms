package com.training.mdb.simple.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import com.training.mdb.util.DataSourceServiceFactory;

public class DatabaseService {
	public boolean isConnected(){
		boolean isAbleToConnect = false;
		
		DataSourceServiceFactory dataSourceServiceFactory = new DataSourceServiceFactory();
		try{
			Connection connection = dataSourceServiceFactory.getConnection();
			if(!connection.isClosed()){
				isAbleToConnect = true;
			}
			connection.close();		
		}
		catch(SQLException e){
			System.err.println("error SQLException : " + e.getMessage());
		}
		catch (Exception e) {
			System.err.println("error Exception : " +e.getMessage());
		}
		
		return isAbleToConnect;
	}
	
	public void insertData(String value){
		DataSourceServiceFactory dataSourceServiceFactory = new DataSourceServiceFactory();
		try{
			Connection conn = dataSourceServiceFactory.getConnection();
			String sqlInsertQuery = "insert into table(id,value) "+"values(?,?)";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sqlInsertQuery);
			preparedStatement.setString(1,  UUID.randomUUID().toString().replace("-", ""));
			preparedStatement.setString(2, value);
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			dataSourceServiceFactory.closeConnection();
		} catch(SQLException e){
			System.err.println("error SQLException : " + e.getMessage());
		}
		catch (Exception e) {
			System.err.println("error Exception : " +e.getMessage());
		}
	}
}
