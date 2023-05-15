package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Producto;

import conexion.Conector;

public class ModeloProducto extends Conector{

	public ArrayList<Producto> getProductos() {
		
		PreparedStatement prt;
		ModeloSeccion mSeccion = new ModeloSeccion();
		mSeccion.setConexion(this.con);
		ArrayList <Producto> productos = new ArrayList<Producto>();
		
		try {
			prt = con.prepareStatement("SELECT id,codigo,nombre,cantidad,precio,caducidad,id_seccion FROM productos ");
			
			
			ResultSet result = prt.executeQuery();
			
			while(result.next()) {
					Producto producto = new Producto();
					
					producto.setId(result.getInt(1));
					producto.setCodigo(result.getString(2));
					producto.setNombre(result.getString(3));
					producto.setCantidad(result.getInt(4));
					producto.setPrecio(result.getDouble(5));
					producto.setCaducidad(result.getDate(6));
					
					
					producto.setIdSeccion(mSeccion.getSeccion(result.getInt(7)));
					
					productos.add(producto);
					
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return productos;
	}
	
	
	
	
				
			
			
			
		
	}
