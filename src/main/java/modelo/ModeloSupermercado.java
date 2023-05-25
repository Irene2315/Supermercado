package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Supermercado;
import conexion.Conector;

public class ModeloSupermercado extends Conector {

	public ArrayList<Supermercado> getSupermercados() {
		PreparedStatement prt;
		
		ArrayList <Supermercado> supermercados = new ArrayList<>();
		try {
			prt = con.prepareStatement("SELECT id, nombre FROM supermercados");
			
			ResultSet result = prt.executeQuery();
			
			while(result.next()) {
				Supermercado supermercado = new Supermercado();
				
				supermercado.setId(result.getInt(1));
				supermercado.setNombre(result.getString(2));
				
				supermercados.add(supermercado);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return supermercados;
	}


	

	
	
	
}
