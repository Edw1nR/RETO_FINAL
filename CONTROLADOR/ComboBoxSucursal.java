package CONTROLADOR;

import MODELO.Base_De_Datos;
import MODELO.*;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComboBoxSucursal {

    Base_De_Datos conexion = new Base_De_Datos();
    Connection cn;
    Statement st;
    ResultSet rs;

    public ComboBoxSucursal() {
    }

    public ArrayList getListaSucursales() {
        System.out.println("FUNCION getListaSucursales()");
        ArrayList mListaSucursales = new ArrayList();
        SUCURSAL sucursal = null;

        try {
            cn = conexion.getCConnection();
            st = cn.createStatement();
            rs = st.executeQuery("SELECT idSucursal, nombreSucursal FROM sucursal; ");
            while (rs.next()) {
                sucursal = new SUCURSAL();
                sucursal.setIdSucursal(rs.getInt("idSucursal"));
                sucursal.setNombreSucursal(rs.getString("nombreSucursal"));
                mListaSucursales.add(sucursal);

            }
        } catch (SQLException e) {

            System.out.println(e);

        }
        return mListaSucursales;
    }
}
