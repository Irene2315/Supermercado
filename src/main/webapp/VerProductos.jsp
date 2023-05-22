<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<style>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 10px;
	color: black;
}

.form-container {
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}



.form-container {
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
	background-color: white;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
		</div>
	</nav>
	<div class="container">
		<div class="form-container">
			<h1 class="fw-bold">GESTION DE PRODUCTOS</h1>
			<form method="get" action="BuscadorCodigoNombre">
				<p>
					Codigo o nombre del producto: <input type="text" name="codigoNombre" />
					<input type="submit" class="btn btn-primary" value="Buscar">
					
				</p>
			</form>
			<form method="get" action="BuscadorPrecio">
				<p>
					Introduce del producto: 
					<input type="text" name="precioMin"  placeholder="precioMinimo"/>
					<input type="text" name="precioMax" placeholder="precioMaximo" />
					<input type="submit" class="btn btn-primary" value="Buscar" >
					
				</p>
			</form>
			<a href="VerProductos" class="btn btn-dark">Refrescar</a>
			<table class="table">

				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">id</th>
						<th scope="col">codigo</th>
						<th scope="col">nombre</th>
						<th scope="col">cantidad</th>
						<th scope="col">precio</th>
						<th scope="col">caducidad</th>
						<th scope="col">id_seccion</th>
						
						<th scope="col"></th>
						<th scope="col"></th>
						
							<td><a href="RegistrarProducto" class="btn btn-dark">Registrar
							</a></td>
							
						

					</tr>
				</thead>
				<tbody>

					<c:forEach items="${productos}" var="producto">
						<tr>
							<th scope="row"></th>
							<td>${producto.id}</td>
							<td>${producto.codigo}</td>
							<td>${producto.nombre}</td>
							<td>${producto.cantidad}</td>
							<td>${producto.precio}</td>
							<td>${producto.caducidad}</td>
							<td>${producto.idSeccion.nombre}</td>

							<td><a href="VerProducto?id=${producto.id}"
								class="btn btn-primary ">Ver </a></td>
							
								<td><a
									href="ModificarProducto?id=${producto.id}"
									class="btn btn-secondary ">Modificar </a></td>
								<td><a
									href="EliminarProducto?id=${producto.id}"
									class="btn btn-danger">eliminar </a></td>
							
						</tr>

					</c:forEach>



				</tbody>
			</table>
		</div>
	</div>

</body>
</html>