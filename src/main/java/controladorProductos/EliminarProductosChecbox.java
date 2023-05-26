package controladorProductos;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ModeloProducto;

/**
 * Servlet implementation class EliminarProductosChecbox
 */
@WebServlet("/EliminarProductosChecbox")
public class EliminarProductosChecbox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProductosChecbox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] productos = request.getParameterValues("productos"); 
		int[] idsProductos = Arrays.stream(productos).mapToInt(Integer::parseInt).toArray();
		
		ModeloProducto productoM = new ModeloProducto();
		
		productoM.conectar();
		for (int i = 0; i < idsProductos.length; i++) {
			
			productoM.eliminarProductoId(idsProductos[i]);
			
		}
		productoM.cerrar();
		
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
