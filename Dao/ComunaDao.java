package Dao;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import Gody.Comuna;


	public class ComunaDao {
		private static final String url="jdbc:mysql://localhost:3306/tp_finaljeg";
		private static final String usr="root";
		private static final String pss="admin";
		
	public ArrayList<Comuna> mostrar(Comuna c) {
			ArrayList<Comuna> cd = new ArrayList<Comuna>();
			int Sehizo=0;
			Connection connection=null;
			try {
				connection=DriverManager.getConnection(url, usr, pss);
				String sql="SELECT `comuna`.`nombreSede`,    `comuna`.`NCom`,    `comuna`.`JuntaComunal`,    `comuna`.`id_com`FROM `tp_finaljeg`.`comuna`;";
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
		return cd;

	}
		
		
		
		
		public boolean actualizar(Comuna c, int Numant) {
			
			int Sehizo=0;
			Connection connection=null;
			try {
				connection=DriverManager.getConnection(url, usr, pss);
				String sql="UPDATE `tp_finaljeg`.`comuna`SET`nombreSede` = ?,`NCom` = ?,`JuntaComunal` = ? WHERE `Ncom` = ?;"
						+ "";
				PreparedStatement ppsmt= connection.prepareStatement(sql);
				ppsmt.setString(1, c.getNombreSede());
				ppsmt.setInt(2, c.getnCom());
				ppsmt.setString(3, c.getJuntaComunal());
				ppsmt.setInt(4, Numant);
				
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
		public boolean guardar(Comuna c) {
			
			int Sehizo=0;
			Connection connection=null;
			try {
				connection=DriverManager.getConnection(url, usr, pss);
				String sql="INSERT INTO `tp_finaljeg`.`comuna`(`nombreSede`,`NCom`,`JuntaComunal`)VALUES(?,?,?);";
				PreparedStatement ppsmt= connection.prepareStatement(sql);

				ppsmt.setString(1, c.getNombreSede());
				ppsmt.setInt(2, c.getnCom());
				ppsmt.setString(3, c.getJuntaComunal());
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
	public boolean borrar(Comuna c) {
			
			int Sehizo=0;
			Connection connection=null;
			try {
				connection=DriverManager.getConnection(url, usr, pss);
				String sql="DELETE FROM `tp_finaljeg`.`comuna` WHERE `NCom`=?;";
				PreparedStatement ppsmt= connection.prepareStatement(sql);
				ppsmt.setInt(1, c.getnCom());
				
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
