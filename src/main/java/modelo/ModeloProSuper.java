package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conector;

public class ModeloProSuper extends Conector {

	
public void registrarSuperProducto(int idProducto, int idSupermercado) {
		
		PreparedStatement prt;
		
		try {
			prt = con.prepareStatement("INSERT INTO productos_supermercados(id_producto, id_supermercado) VALUES (?,?)");
		
			prt.setInt(1, idProducto);
			prt.setInt(2, idSupermercado);
			
			prt.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

public boolean productoEnSupermercados(int idProducto) {
	PreparedStatement prt;
	boolean superSi=false;
	
	try {
		prt = con.prepareStatement("SELECT id_supermercado FROM productos_supermercados WHERE id_producto=?");
	
		prt.setInt(1, idProducto);
		ResultSet result = prt.executeQuery();
		
		if(result.next()) {
			superSi=true;
		}
		else {
			superSi=false;
		}
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	return superSi;
}

public void eliminarRelacionSuper(int idProducto) {
	
	PreparedStatement prt;
	
	try {
		prt = con.prepareStatement("DELETE FROM productos_supermercados WHERE id_producto=?");
	
		prt.setInt(1, idProducto);
		
		prt.execute();
		
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
}

}
