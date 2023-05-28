package controladorProductos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Producto;
import modelo.ModeloProducto;

/**
 * Servlet implementation class BuscadorPrecio
 */
@WebServlet("/BuscadorPrecio")
public class BuscadorPrecio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscadorPrecio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double precioMin = Double.parseDouble(request.getParameter("precioMin"));
		double precioMax = Double.parseDouble(request.getParameter("precioMax"));
		
		ModeloProducto productoM = new ModeloProducto();
		productoM.conectar();
		ArrayList <Producto> productos = productoM.getProductos();
		
		productoM.cerrar();
		
		Iterator<Producto> it = productos.iterator();
		while (it.hasNext()) {
			Producto producto = it.next();
			if((producto.getPrecio()<precioMin) || (producto.getPrecio()>precioMax)) {
				it.remove();
			}
		}
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("VerProductos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
