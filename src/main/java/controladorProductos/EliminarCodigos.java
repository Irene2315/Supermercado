package controladorProductos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ModeloProducto;


/**
 * Servlet implementation class EliminarCodigos
 */
@WebServlet("/EliminarCodigos")
public class EliminarCodigos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCodigos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto productoM = new ModeloProducto();
		
		
		String codigosProductosTxurro = request.getParameter("codigoProducto");
		String[] codigosProductos = codigosProductosTxurro.split(",");
		boolean hayProducto =false;
	
		productoM.conectar();
		
		for (int i = 0; i < codigosProductos.length; i++) {
			
			 hayProducto =false;
			
			String codigoProducto = codigosProductos[i];
			
			hayProducto= productoM.productoExiste(codigoProducto);
			
			if (hayProducto==false) {
				break;
			}
			
	}	
		productoM.cerrar();
		
		if(hayProducto==true) {
			productoM.conectar();
			
			for (int i = 0; i < codigosProductos.length; i++) {
				
				productoM.eliminarProductoCodigo(codigosProductos[i]);
			}
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
