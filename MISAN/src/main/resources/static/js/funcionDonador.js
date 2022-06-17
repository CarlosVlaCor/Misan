const nombreUsuario = document.getElementById('nombre-usuario');
const tabla = document.getElementById('tbody');
async function dejarDeSerDonador(){
	let datos = {};
		datos.email = nombreUsuario.value;
		const response = await fetch('api/donador/'+nombreUsuario.value, {
   	 	method: 'DELETE',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 	
 	 	if(response.ok){
	 	window.location.href = "/";
		}
}

async function obtenerReceptores(){
	const response = await fetch('api/donador/obtenerReceptores/'+nombreUsuario.value, {
   	 	method: 'GET',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 	const receptores = await response.json();
 	 	
        receptores.forEach(function(receptor){
			let tbody="<tbody>"
          	+"<tr>"
            +`<td><a href='usuarios/perfil/${receptor.usuario.id}'>${receptor.usuario.nombre } ${receptor.usuario.apellidoPaterno } ${receptor.usuario.apellidoMaterno}</a></td>`
            +"<td>"+receptor.usuario.domicilio.pais.nombrePais +"</td>"
            +"<td>"+receptor.usuario.domicilio.estado.nombre +"</td>"
            +"<td>"+receptor.usuario.domicilio.ciudad +"</td>"
            +"<td>"+receptor.tipoSangre.tipoSangre +"</td>"
            +"<td>"+receptor.fecha+"</td>"
            +`<td><input class='boton boton--primario' type='button' onclick='enviarSolicitud(${receptor.usuario.id})'value='Enviar solicitud'></td>`
            +"</tr>"
        +"</tbody>"
        
        tabla.innerHTML += tbody;
		})
  
 	 	funcionTabla();
}

async function enviarSolicitud(id){
	const enviarSolicitud= {};
	enviarSolicitud.id = id;
	enviarSolicitud.email= nombreUsuario.value;
	const response = await fetch(`api/donador/enviarSolicitud`, {
   	 	method: 'POST',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	 body: JSON.stringify(enviarSolicitud)
 	 	});
 	 	const receptores = await response.text();
 	 	window.location.href = "/donador";
}

 function funcionTabla () {
    $('#myTable').DataTable({
        fixedHeader: true,
        fixedColumn:true,
		searching: false,
		language: {
			 processing: "Tratamiento en curso...",
			 search: "Buscar&nbsp;:",
			 lengthMenu: "Mostrar _MENU_ items",
			 info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
			 infoEmpty: "No existen datos.",
			 infoFiltered: "(filtrado de _MAX_ elementos en total)",
			 loadingRecords: "Cargando...",
                    zeroRecords: "No se encontraron datos con tu busqueda",
                    emptyTable: "No hay datos disponibles en la tabla.",
                    paginate: {
                        first: "Primero",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    },
                    aria: {
                        sortAscending: ": active para ordenar la columna en orden ascendente",
                        sortDescending: ": active para ordenar la columna en orden descendente"
                    }
			 
		},
		 scrollY: 500,
         scrollX: true,
	});
    
}