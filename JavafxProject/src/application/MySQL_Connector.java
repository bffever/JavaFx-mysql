package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class MySQL_Connector {
	private Connection databaseLink;
	private String userid;
	private String transfer_user;
	
	MySQL_Connector()
	{
		GetConnection();
	}
	
	private void GetConnection()
	{
		String databaseName="javafx";
		String databaseUser="JavaFX";
		String databasePassword="javafx123123";
		String url="jdbc:mysql://localhost/"+databaseName;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public boolean LoginCheck(String password)
	{
		String connectQuery="Select userid from accounts where password=?";
	
		try(PreparedStatement preparedStatement= databaseLink.prepareStatement(connectQuery);)
		{
			preparedStatement.setString(1,password);
			
			ResultSet resultset=preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				userid=resultset.getString(1);
				return true;
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	public int GetBalance()
	{
		String connectQuery="call get_cash_balance(?)";
	
		try(PreparedStatement preparedStatement= databaseLink.prepareStatement(connectQuery);)
		{
			preparedStatement.setString(1,userid);
			
			ResultSet resultset=preparedStatement.executeQuery();
			
			if(resultset.next())
			{
				return resultset.getInt(1);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public void Update_Cash(int amount)
	{
		String query="call update_cash_amount(?,?)";
		
		try(PreparedStatement prepared=databaseLink.prepareStatement(query))
		{
			prepared.setString(1, userid);
			prepared.setString(2, Integer.toString(amount));
			
			ResultSet resultset=prepared.executeQuery();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean Get_TransferAccount(String accountnumber)
	{
		String query="call get_userid_from_accountid(?)";
		
		try(PreparedStatement prepared=databaseLink.prepareStatement(query))
		{
			prepared.setString(1, accountnumber);
			ResultSet resultset=prepared.executeQuery();
			
			if(resultset.next())
			{
				transfer_user=resultset.getString(1);
				return true;
			}else
			{
				return false;
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public void Transfer_Money(String amount)
	{
		String query="call decrease_cashAmount(?,?)";
		String query_two="call update_cash_amount(?,?)";
		
		try
		{
			try(PreparedStatement prepared=databaseLink.prepareStatement(query))
			{
				prepared.setString(1, userid);
				prepared.setString(2, amount);
				
				ResultSet resultset=prepared.executeQuery();
				
			}
			
			try(PreparedStatement prepared_t=databaseLink.prepareStatement(query_two))
			{
				prepared_t.setString(1, transfer_user);
				prepared_t.setString(2, amount);
				
				ResultSet resultset=prepared_t.executeQuery();
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}

	public void Clear()
	{
		userid="";
		transfer_user="";
	}
}
