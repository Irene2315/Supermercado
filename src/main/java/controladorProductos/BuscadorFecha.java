package controladorProductos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Producto;
import modelo.ModeloProducto;

/**
 * Servlet implementation class BuscadorFecha
 */
@WebServlet("/BuscadorFecha")
public class BuscadorFecha extends HttpServlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscadorFecha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaInicio = new Date();
		Date fechaFin= new Date();
		try {
			 fechaInicio = formatoFecha.parse( request.getParameter("fechaInicio"));
			 fechaFin = formatoFecha.parse(request.getParameter("fechaFin"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	ModeloProducto productoM = new ModeloProducto();
		productoM.conectar();
		ArrayList <Producto> productos = productoM.getProductos();
		
		Iterator<Producto> it = productos.iterator();
		while(it.hasNext()) {
			Producto producto = it.next();
			
			if((producto.getCaducidad().before(fechaInicio)) || producto.getCaducidad().after(fechaFin)) {
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
