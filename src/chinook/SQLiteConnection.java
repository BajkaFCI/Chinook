/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PSluis
 */
public class SQLiteConnection {
public static Connection sqliteconnect(){
	    Connection litecon = null;
	        try
	        {
	            String url = "jdbc:sqlite:E:\\NetBeans projects\\Chinook\\src\\db\\chinook.db";
	            litecon = DriverManager.getConnection(url);
	            System.out.println("Connection to database /db/chinook.db has been established");
	            return litecon;            
	        }
	        catch(SQLException e){
	        System.out.println(e.getMessage());
	        return null;    
	        }    
}

}