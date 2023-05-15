package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Seccion;
import conexion.Conector;

public class ModeloSeccion extends Conector {

public Seccion getSeccion (int id) {
		
		PreparedStatement prt;
		
		Seccion seccion= new Seccion ();
		
		
		
				try {
					prt = con.prepareStatement("SELECT id,nombre FROM `secciones` WHERE id=?");
					

					prt.setInt(1, id);
					
					ResultSet result = prt.executeQuery();
					
					while(result.next()) {
						seccion.setId(result.getInt(1));
						seccion.setNombre(result.getString(2));
						
				} 
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					
					return seccion;
			
	}
}
