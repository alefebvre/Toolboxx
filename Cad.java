package data;


import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/// CAD is a singleton
public class Cad {
	private static Cad instance = null;
	private static Connection connexion;
	private static Statement state;
	private final String url="jdbc:mysql://localhost/minisigdb";
	private final String user="root";
	private final String mdp="cmlRdvCcmlFdvP34614773";
	
	//local constructor
	private Cad()
	{
		if(Cad.connexion == null)
			connect();
	}
	
	//instance getter
	public static Cad getInstance(){
		if(Cad.instance == null)
			Cad.instance = new Cad();
		return Cad.instance;
	}
	
	//return a Prepared statement
	public PreparedStatement getPreparedStatement(String baseQuery)
	{
		try {
			return (PreparedStatement) connexion.prepareStatement(baseQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//connectivity manager
	private void connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Cad.connexion = (Connection) DriverManager.getConnection(url, user, mdp);
			Cad.state = (Statement) Cad.connexion.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC : connection error!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//SQL action request
	public Boolean doSQL(String query)
	{
		try {
			Cad.state.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	//prepared
	public Boolean doSQL(String query, String... params)
	{
		try {
			
			PreparedStatement rqPrep = (PreparedStatement) Cad.connexion.prepareStatement(query);
			int maxParams = countParam(query);
			if(params.length > maxParams)
				System.out.println("WARNING : You have extra args for this query : \n" + query +"\n");
			for(int i = 0 ; i < maxParams; i++)
			{
				rqPrep.setString(i+1, params[i]);
			}
			rqPrep.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//SQL selection request
	public ResultSet getSQL(String query)
	{
		try {
			return (ResultSet)Cad.state.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	//prepared
	public ResultSet getSQL(String query, String... params)
	{
		try {
			
			PreparedStatement rqPrep = (PreparedStatement) Cad.connexion.prepareStatement(query);
			int maxParams = countParam(query);
			if(params.length > maxParams)
				System.out.println("WARNING : You have extra args for this query : \n" + query +"\n");
			for(int i = 0 ; i < maxParams; i++)
			{
				rqPrep.setString(i+1, params[i]);
			}
			return (ResultSet) rqPrep.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//Disconnection
	public void disconnect(){
		try {
			Cad.state.close();
			Cad.connexion.close();
			Cad.instance.finalize();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}
	
	/** Queries validation **/
	private int countParam(String query)
	{
		int index = 0;
		int count = 0;
		
		while(index > -1)
		{
			index = query.indexOf("?", index+1);
			if(index > -1)
				count++;
		}
		return count;
	}
}