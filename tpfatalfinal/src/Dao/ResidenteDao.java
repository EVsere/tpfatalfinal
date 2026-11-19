package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Gody.Residente;


public class ResidenteDao {
	
	private static final String url="jdbc:mysql://127.0.0.1:3306/tp_finaljeg";
	private static final String usr="root";
	private static final String pss="admin";
	
public ArrayList<Residente> mostrar(Residente p) {

ArrayList<Residente> per = new ArrayList<Residente>();
Connection connection=null;

try {
    connection=DriverManager.getConnection(url, usr, pss);
    String sql="SELECT r.Dni, r.nombre, r.FNacimiento, r.Tienehijos, b.Nombre AS Barrio, CASE WHEN sl.Ocupado = 1 THEN 'Ocupado' WHEN sl.Desocupado = 1 THEN 'Desocupado' WHEN sl.semiOcupado = 1 THEN 'Subocupado' END AS SituacionLaboral FROM residente r INNER JOIN barrio b ON r.FK_barrio = b.ID_Barrio INNER JOIN situacion_laboral sl ON r.fk_SituacionLaboral = sl.ID_SituacionLaboral;";
    PreparedStatement ppsmt= connection.prepareStatement(sql);
    ResultSet rs=ppsmt.executeQuery();

    while(rs.next()) {
        Residente residente = new Residente(rs.getString("Dni"), rs.getString("nombre"), rs.getString("FNacimiento"), rs.getString("SituacionLaboral"), rs.getBoolean("Tienehijos"));
        residente.setBarrio(rs.getString("Barrio"));
        per.add(residente);
    }

} catch (SQLException e) {
    e.printStackTrace();
} finally {
    try {
        if(connection!=null)
            connection.close();
    } catch (SQLException e) {
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
        connection.setAutoCommit(false);

        String sqlBarrio="SELECT ID_Barrio FROM barrio WHERE Nombre = ?";
        PreparedStatement psBarrio=connection.prepareStatement(sqlBarrio);
        psBarrio.setString(1, p.getBarrio());
        ResultSet rsBarrio=psBarrio.executeQuery();

        if (!rsBarrio.next()) {
            connection.rollback();
            return false;
        }

        int idBarrio=rsBarrio.getInt("ID_Barrio");

        String sql="UPDATE residente SET nombre = ?, FNacimiento = ?, FK_barrio = ?, Tienehijos = ? WHERE Dni = ?";
        PreparedStatement ppsmt=connection.prepareStatement(sql);

        ppsmt.setString(1, p.getNombre());
        ppsmt.setString(2, p.getFnacimiento());
        ppsmt.setInt(3, idBarrio);
        ppsmt.setBoolean(4, p.isTienehijos());
        ppsmt.setString(5, DNIAnt);

        Sehizo=ppsmt.executeUpdate();

        if (Sehizo != 1) {
            connection.rollback();
            return false;
        }

        String sqlSituacion="UPDATE situacion_laboral sl INNER JOIN residente r ON sl.ID_SituacionLaboral = r.fk_SituacionLaboral SET sl.Ocupado = ?, sl.Desocupado = ?, sl.semiOcupado = ? WHERE r.Dni = ?";
        PreparedStatement psSituacion=connection.prepareStatement(sqlSituacion);

        psSituacion.setBoolean(1, p.getSitLaboral().equals("Ocupado"));
        psSituacion.setBoolean(2, p.getSitLaboral().equals("Desocupado"));
        psSituacion.setBoolean(3, p.getSitLaboral().equals("Subocupado"));
        psSituacion.setString(4, DNIAnt);

        psSituacion.executeUpdate();

        connection.commit();

    } catch (SQLException e) {
        try {
            if (connection != null)
                connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return Sehizo==1;
}


public boolean guardar(Residente p, String barrio) {

    int Sehizo=0;
    Connection connection=null;

    try {

        connection=DriverManager.getConnection(url, usr, pss);
        connection.setAutoCommit(false);

        String sqlBarrio="SELECT ID_Barrio FROM barrio WHERE Nombre = ?";
        PreparedStatement psBarrio=connection.prepareStatement(sqlBarrio);
        psBarrio.setString(1, barrio);
        ResultSet rsBarrio=psBarrio.executeQuery();

        if (!rsBarrio.next()) {
            connection.rollback();
            return false;
        }

        int idBarrio=rsBarrio.getInt("ID_Barrio");

        String sqlSituacion="INSERT INTO situacion_laboral (Ocupado, Desocupado, semiOcupado) VALUES (?, ?, ?)";
        PreparedStatement psSituacion=connection.prepareStatement(sqlSituacion, java.sql.Statement.RETURN_GENERATED_KEYS);

        psSituacion.setBoolean(1, p.getSitLaboral().equals("Ocupado"));
        psSituacion.setBoolean(2, p.getSitLaboral().equals("Desocupado"));
        psSituacion.setBoolean(3, p.getSitLaboral().equals("Subocupado"));

        psSituacion.executeUpdate();

        ResultSet rsSituacion=psSituacion.getGeneratedKeys();

        if (!rsSituacion.next()) {
            connection.rollback();
            return false;
        }

        int idSituacion=rsSituacion.getInt(1);

        String sql="INSERT INTO residente (Dni, nombre, FNacimiento, FK_barrio, fk_SituacionLaboral, Tienehijos) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ppsmt=connection.prepareStatement(sql);

        ppsmt.setString(1, p.getDNI());
        ppsmt.setString(2, p.getNombre());
        ppsmt.setString(3, p.getFnacimiento());
        ppsmt.setInt(4, idBarrio);
        ppsmt.setInt(5, idSituacion);
        ppsmt.setBoolean(6, p.isTienehijos());

        Sehizo=ppsmt.executeUpdate();

        connection.commit();

    } catch (SQLException e) {
        try {
            if (connection != null)
                connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
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
        connection.setAutoCommit(false);

        String sqlBuscar="SELECT fk_SituacionLaboral FROM residente WHERE Dni = ?";
        PreparedStatement psBuscar=connection.prepareStatement(sqlBuscar);
        psBuscar.setString(1, p.getDNI());
        ResultSet rs=psBuscar.executeQuery();

        if (!rs.next()) {
            connection.rollback();
            return false;
        }

        int idSituacion=rs.getInt("fk_SituacionLaboral");

        String sql="DELETE FROM residente WHERE Dni = ?";
        PreparedStatement ppsmt=connection.prepareStatement(sql);
        ppsmt.setString(1, p.getDNI());

        Sehizo=ppsmt.executeUpdate();

        if (Sehizo != 1) {
            connection.rollback();
            return false;
        }

        String sqlSituacion="DELETE FROM situacion_laboral WHERE ID_SituacionLaboral = ?";
        PreparedStatement psSituacion=connection.prepareStatement(sqlSituacion);
        psSituacion.setInt(1, idSituacion);
        psSituacion.executeUpdate();

        connection.commit();

    } catch (SQLException e) {
        try {
            if (connection != null)
                connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    } finally {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
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




public ArrayList<String> mostrarBarrios() {
    ArrayList<String> lista = new ArrayList<String>();
    Connection connection = null;

    try {
        connection = DriverManager.getConnection(url, usr, pss);
        String sql = "SELECT Nombre FROM barrio ORDER BY Nombre";
        PreparedStatement ppsmt = connection.prepareStatement(sql);
        ResultSet rs = ppsmt.executeQuery();

        while (rs.next()) {
            lista.add(rs.getString("Nombre"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return lista;
}
}
