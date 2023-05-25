package controladorProductos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Producto;
import modelo.ModeloProSuper;
import modelo.ModeloProducto;

/**
 * Servlet implementation class EliminarProducto
 */
@WebServlet("/EliminarProducto")
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		Producto producto = new Producto();
		
		ModeloProducto productoM = new ModeloProducto();
		
		ModeloProSuper proSupM = new ModeloProSuper();
		
		productoM.conectar();
		
		producto=productoM.getProducto(id);
		
		productoM.cerrar();
		
		proSupM.conectar();
		
		boolean productoSuper = proSupM.productoEnSupermercados(id);
		
		
		proSupM.cerrar();
		
		if (producto.getCantidad()>0) {
			productoM.conectar();
			productoM.disminuirCantidad(producto);
			productoM.cerrar();
		}
		
		else if (producto.getCantidad()==0 && productoSuper==true ) {
			
			
			proSupM.conectar();
			proSupM.eliminarRelacionSuper(id);
			proSupM.cerrar();
			
		}
		
		else if (producto.getCantidad()==0 && productoSuper==false ) {
			
			productoM.conectar();
			
			productoM.eliminarProducto(id);
			
			productoM.cerrar();
			
		}
		
		
		response.sendRedirect("VerProductos");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
