package Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import bou.Residente;


public class ResidenteDao {
	
	private static final String url="jdbc:mysql://127.0.0.1:3306/tp_finaljeg";
	private static final String usr="root";
	private static final String pss="admin";
	
public ArrayList<Residente> mostrar(Residente p) {
		ArrayList<Residente> per = new ArrayList<Residente>();
		int Sehizo=0;
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url, usr, pss);
			String sql="SELECT `residente`.`Dni`,    `residente`.`nombre`,    `residente`.`FNacimiento`,    `residente`.`id_res`,    `residente`.`FK_barrio`,    `residente`.`fk_SituacionLaboral`FROM `tp_finaljeg`.`residente`;";
			PreparedStatement ppsmt= connection.prepareStatement(sql);
			Sehizo= ppsmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return per;

}
	
	
	
	
	public boolean actualizar(Residente p, String DNIAnt) {
		
		int Sehizo=0;
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url, usr, pss);
			String sql="UPDATE `tp_finaljeg`.`residente`SET`Dni` = ?,`nombre` = ?,`FNacimiento` = ?,`Tienehijos` = ? , WHERE `id_res` = ?;";
			PreparedStatement ppsmt= connection.prepareStatement(sql);
			ppsmt.setString(1, p.getDNI());
			ppsmt.setString(2, p.getNombre());
			ppsmt.setString(3, p.getFnacimiento());
			ppsmt.setBoolean(4, p.isTienehijos());
			ppsmt.setString(5, DNIAnt);
			
			Sehizo= ppsmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return Sehizo==1;

}
	public boolean guardar(Residente p) {
		
		int Sehizo=0;
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url, usr, pss);
			String sql="INSERT INTO `tp_finaljeg`.`residente`(`Dni`,`nombre`,`FNacimiento`, `Tienehijos`)VALUES(?,?,?,?);";
			PreparedStatement ppsmt= connection.prepareStatement(sql);

			ppsmt.setString(1, p.getDNI());
			ppsmt.setString(2, p.getNombre());
			ppsmt.setString(3, p.getFnacimiento());
			ppsmt.setBoolean(4, p.isTienehijos());
			Sehizo= ppsmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return Sehizo==1;

}
public boolean borrar(Residente p) {
		
		int Sehizo=0;
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url, usr, pss);
			String sql="DELETE FROM `tp_finaljeg`.`residente`WHERE `Dni`=?;";
			PreparedStatement ppsmt= connection.prepareStatement(sql);
			ppsmt.setString(1, p.getDNI());
			
			Sehizo= ppsmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(connection!=null)
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return Sehizo==1;


}
public boolean actualizarBarrio(Residente p, String DNI) {
	
	int Sehizo=0;
	Connection connection=null;
	try {
		connection=DriverManager.getConnection(url, usr, pss);
		String sql="UPDATE `tp_finaljeg`.`residente`SET` `` = ? , WHERE `id_res` = ?;";
		PreparedStatement ppsmt= connection.prepareStatement(sql);
		ppsmt.setString(1, p.getDNI());
		ppsmt.setString(2, p.getNombre());
		ppsmt.setString(3, p.getFnacimiento());
		ppsmt.setBoolean(4, p.isTienehijos());
		ppsmt.setString(5, DNI);
		
		Sehizo= ppsmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if(connection!=null)
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
return Sehizo==1;

}
}
