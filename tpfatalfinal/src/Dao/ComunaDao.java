
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Gody.Comuna;

public class ComunaDao {

    private static final String url = "jdbc:mysql://localhost:3306/tp_finaljeg";
    private static final String usr = "root";
    private static final String pss = "admin";

    
    public ArrayList<Comuna> mostrar() {

        ArrayList<Comuna> lista = new ArrayList<Comuna>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, usr, pss);

            String sql = "SELECT `comuna`.`nombreSede`,    `comuna`.`NCom`,    `comuna`.`JuntaComunal`FROM `tp_finaljeg`.`comuna`;";

            PreparedStatement ppsmt = connection.prepareStatement(sql);

            ResultSet resultado = ppsmt.executeQuery();

            while (resultado.next()) {

                String sede = resultado.getString("nombreSede");
                int numero = resultado.getInt("NCom");
                String junta = resultado.getString("JuntaComunal");

                Comuna c = new Comuna(sede, numero, junta);

                lista.add(c);
            }

            resultado.close();
            ppsmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    
    public boolean actualizar(Comuna c, int Numant) {

        int Sehizo = 0;
        Connection connection = null;
        

        try {
            connection = DriverManager.getConnection(url, usr, pss);

            String sql = "UPDATE comuna SET nombreSede = ?, NCom = ?, JuntaComunal = ? WHERE NCom = ?";

            PreparedStatement ppsmt = connection.prepareStatement(sql);

            ppsmt.setString(1, c.getNombreSede());
            ppsmt.setInt(2, c.getnCom());
            ppsmt.setString(3, c.getJuntaComunal());
            ppsmt.setInt(4, Numant);

            Sehizo = ppsmt.executeUpdate();

            ppsmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Sehizo == 1;
    }


    public boolean guardar(Comuna comuna) {

        int Sehizo = 0;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, usr, pss);

            String sql = "INSERT INTO comuna (nombreSede, NCom, JuntaComunal) VALUES (?, ?, ?)";

            PreparedStatement ppsmt = connection.prepareStatement(sql);

            ppsmt.setString(1, comuna.getNombreSede());
            ppsmt.setInt(2, comuna.getnCom());
            ppsmt.setString(3, comuna.getJuntaComunal());

            Sehizo = ppsmt.executeUpdate();

            ppsmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Sehizo == 1;
    }


    public boolean borrar(Comuna c) {

        int Sehizo = 0;
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, usr, pss);

            String sql = "DELETE FROM comuna WHERE NCom = ?;";

            PreparedStatement ppsmt = connection.prepareStatement(sql);

            ppsmt.setInt(1, c.getnCom());

            Sehizo = ppsmt.executeUpdate();

            ppsmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Sehizo == 1;
    }
}
