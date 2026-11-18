package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Gody.BTrazadoFormal;


public class BTrazadoFormalDao{
	public Boolean guardar(BTrazadoFormal p)
	{
		String url = "jdbc:mysql://127.0.0.1:3306/tp_finaljeg";
		String usr = "root";
		String pss = "admin";
		Connection connection = null;
		int filasafectadas  = 0;
		try {
			connection = DriverManager.getConnection(url, usr, pss);
			String sql = "INSERT INTO `tp_finaljeg`.`barrio`(`Superficie`,`PAsfalto`,`Nombre`,`EdPh`,`numComu`) VALUES(?,?,?,?,?);";
			PreparedStatement ps = 	connection.prepareStatement(sql);
			ps.setDouble(1, p.getSuperficie());
			ps.setInt(2, p.getpAsfalto());
			ps.setString(3, p.getNombreB());
			ps.setInt(4, p.getEdPh());
			ps.setInt(5, p.getComuna());
			filasafectadas = ps.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try
			{
				if(connection != null )
				{
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return filasafectadas == 1;
	}
	  
	public Boolean Actualizar(BTrazadoFormal p, String nombreAntiguo)
	{	
		String url = "jdbc:mysql://127.0.0.1:3306/tp_finaljeg";
		String usr = "root";
		String pss = "admin";
		Connection connection = null;
		int filasafectadas = 0;
		try {
			
			connection = DriverManager.getConnection(url, usr, pss);
			String sql = "UPDATE `tp_finaljeg`.`barrio` SET `Superficie` = ?,`PAsfalto` = ?,`Nombre`= ?,`EdPh`= ?,`numComu`= ? WHERE `Nombre` = ?;";
			PreparedStatement ps = 	connection.prepareStatement(sql);
			ps.setDouble(1, p.getSuperficie());
			ps.setInt(2, p.getpAsfalto());
			ps.setString(3, p.getNombreB());
			ps.setInt(4, p.getEdPh());
			ps.setString(6, nombreAntiguo);
			ps.setInt(5, p.getComuna());
			
			filasafectadas = ps.executeUpdate();
			if(filasafectadas == 1)
			{
				System.out.println("Se actualizo el registro");
			}
			else {
				System.out.println("no se encontro el registro");
				
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try
			{
				if(connection != null )
				{
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return filasafectadas == 1;
	}
	public Boolean Borrar(String nombre)
	{
		String url = "jdbc:mysql://127.0.0.1:3306/tp_finaljeg";
		String usr = "root";
		String pss = "admin";
		Connection connection = null;
		int filasafectadas = 0;
		try {
			
			connection = DriverManager.getConnection(url, usr, pss);
			String sql = "DELETE FROM `tp_finaljeg`.`barrio` WHERE Nombre = ?;";
			PreparedStatement ps = 	connection.prepareStatement(sql);
			ps.setString(1, nombre);
			
			filasafectadas = ps.executeUpdate();
			if(filasafectadas == 1)
			{
				System.out.println("El registro se borro adecuadamente");
			}
			else {
				System.out.println("no se encontro el registro");
				
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try
			{
				if(connection != null )
				{
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return filasafectadas == 1;
	}
	public ArrayList<BTrazadoFormal> devolverTodos()
	{
		String url = "jdbc:mysql://127.0.0.1:3306/tp_finaljeg";
		String usr = "root";
		String pss = "admin";
		Connection connection = null;
		ArrayList<BTrazadoFormal> devuelve = new ArrayList<>();
		try {
			
			connection = DriverManager.getConnection(url, usr, pss);
			Statement st = connection.createStatement();
			String sql = "SELECT`barrio`.`Superficie`,`barrio`.`PAsfalto`,`barrio`.`Nombre`,`barrio`.`EdPh`,`barrio`.`numComu` FROM `tp_finaljeg`.`barrio`;";
			ResultSet rs =st.executeQuery(sql);
			
			while(rs.next())
			{
				double superficie = rs.getDouble("Superficie");
				int PAsfalto = rs.getInt("PAsfalto");
				String nombre = rs.getString("Nombre");
				int EdPh = rs.getInt("EdPh");
				int comuna = rs.getInt("numComu");
				BTrazadoFormal po = new BTrazadoFormal(EdPh, PAsfalto, nombre, superficie,comuna);
				devuelve.add(po);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try
			{	
				if(connection != null )
				{
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return devuelve;
	}
}
