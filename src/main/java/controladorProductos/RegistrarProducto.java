package controladorProductos;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clases.Producto;
import clases.Seccion;
import clases.Supermercado;
import modelo.ModeloProSuper;
import modelo.ModeloProducto;
import modelo.ModeloSeccion;
import modelo.ModeloSupermercado;

/**
 * Servlet implementation class RegistrarProducto
 */
@WebServlet("/RegistrarProducto")
public class RegistrarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloSeccion seccioM = new ModeloSeccion();
		
		ArrayList <Seccion>  secciones = new ArrayList <>();
		
//		boolean error= false;
		
		seccioM.conectar();
		secciones = seccioM.getSecciones();
		seccioM.cerrar();
		
		ModeloSupermercado supermercadoM = new ModeloSupermercado();
		
		supermercadoM.conectar();
		
		ArrayList <Supermercado> supermercados = supermercadoM.getSupermercados();
		
		supermercadoM.cerrar();
		
		request.setAttribute("secciones", secciones);
		request.setAttribute("supermercados", supermercados);
//		request.setAttribute("error", error);
		request.getRequestDispatcher("VistaRegistrarProducto.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloProducto productoM = new ModeloProducto();
		
		
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad =Integer.parseInt(request.getParameter("cantidad"));
		double precio = Double.parseDouble(request.getParameter("precio"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String[] supermercados = request.getParameterValues("supermercados"); 
		
		int[] idsSupermercados = Arrays.stream(supermercados).mapToInt(Integer::parseInt).toArray();
		
		Date caducidad = new Date();
		try {
			caducidad = sdf.parse(request.getParameter("caducidad"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		productoM.conectar();
		
		boolean codigoDupli = productoM.CodigoDuplicado(codigo);
	
		productoM.cerrar();
		
		if (codigoDupli==true || cantidad<0 || precio<0 || caducidad.before(new Date())) {
			ModeloSeccion seccioM = new ModeloSeccion();
			
			ArrayList <Seccion>  secciones = new ArrayList <>();
			seccioM.conectar();
			
			boolean error=true;
			secciones = seccioM.getSecciones();
			
			seccioM.cerrar();
			
			ModeloSupermercado supermercadoM = new ModeloSupermercado();
			
			supermercadoM.conectar();
			
			ArrayList <Supermercado> supermercados2 = supermercadoM.getSupermercados();
			
			supermercadoM.cerrar();
			
			request.setAttribute("secciones", secciones);
			request.setAttribute("supermercados", supermercados2);
			
			request.setAttribute("error", error);
			request.setAttribute("secciones", secciones);
			
			request.getRequestDispatcher("VistaRegistrarProducto.jsp").forward(request, response);
		}
		
		
		
		
		Producto producto = new Producto();
		
		
		
		Seccion seccion = new Seccion();
		

		int id =Integer.parseInt(request.getParameter("seccion"));
		
		seccion.setId(id);
		
		
		producto.setCaducidad(caducidad);
		producto.setCodigo(codigo);
		producto.setNombre(nombre);
		producto.setCantidad(cantidad);
		producto.setPrecio(precio);
		producto.setIdSeccion(seccion);
		
		productoM.conectar();
		
		productoM.registrarProducto(producto);
		
		 int  idProducto= productoM.getProductoCodigo(codigo);
		
		productoM.cerrar();
		
		
		
		ModeloProSuper proSupM = new ModeloProSuper();
		proSupM.conectar();
		for (int i = 0; i < idsSupermercados.length; i++) {
			
			proSupM.registrarSuperProducto(idProducto, idsSupermercados[i]);
		}
		
		
		proSupM.cerrar();
		
		response.sendRedirect("VerProductos");
		
	}

}
