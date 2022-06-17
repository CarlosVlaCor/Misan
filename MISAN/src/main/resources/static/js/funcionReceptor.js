const nombreUsuario = document.getElementById('nombre-usuario');
const tabla = document.getElementById('tbody');
async function dejarDeSerReceptor(){
	let datos = {};
		datos.email = nombreUsuario.value;
		const response = await fetch('api/receptor/'+nombreUsuario.value, {
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

async function obtenerDonadores(){
	
	const response = await fetch('api/receptor/obtenerDonadores/'+nombreUsuario.value, {
   	 	method: 'GET',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 	const donadores = await response.json();
 	 	
        donadores.forEach(function(donador){
			let tbody="<tbody>"
          	+"<tr>"
            +`<td><a href='usuarios/perfil/${donador.usuario.id}'>${donador.usuario.nombre } ${donador.usuario.apellidoPaterno } ${donador.usuario.apellidoMaterno}</a></td>`
            +"<td>"+donador.usuario.domicilio.pais.nombrePais +"</td>"
            +"<td>"+donador.usuario.domicilio.estado.nombre +"</td>"
            +"<td>"+donador.usuario.domicilio.ciudad +"</td>"
            +"<td>"+donador.tipoSangre.tipoSangre +"</td>"
            +"<td>"+donador.fecha+"</td>"
            +`<td><input class='boton boton--primario' type='button' onclick='enviarSolicitud(${donador.usuario.id})'value='Enviar solicitud'></td>`
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
	const response = await fetch(`api/receptor/enviarSolicitud`, {
   	 	method: 'POST',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	 body: JSON.stringify(enviarSolicitud)
 	 	});
 	 	const receptores = await response.text();
 	 	window.location.href = "/receptor";
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