const rol = document.getElementById('rol');
const nombreUsuario = document.getElementById('nombre-usuario');
const tabla = document.getElementById('tbody');
const tabla2 = document.getElementById('tbody2');
const tabla3 = document.getElementById("tbody3");


var cerrarPopup = document.getElementById('cerrar--popup'),
    overlay = document.getElementById('overlay--solicitudes'),
    popup = document.getElementById('popup'),
    cancelarEnvio = document.getElementById('cancelar-envio'),
    contCorreo = document.getElementById('cont-correo'),
    contTelefono = document.getElementById('cont-telefono'),
    otroCorreo = document.getElementById('otro-correo'),
    otroTelefono = document.getElementById('otro-telefono'),
    nombrePersona = document.getElementById('nombre--persona'),
    botonAceptar = document.getElementById('boton-Aceptar');


function abrirPopupDonador(id){
	botonAceptar.innerHTML = `<input type='button' class='boton boton--primario' onclick='aceptarSolicitudDonador(${id})' value='Aceptar'>`
	overlay.classList.add('active');
    popup.classList.add('active');
}
function abrirPopupReceptor(id){
	botonAceptar.innerHTML = `<input type='button' class='boton boton--primario' onclick='aceptarSolicitudReceptor(${id})' value='Aceptar'>`
	overlay.classList.add('active');
    popup.classList.add('active');
}
cerrarPopup.addEventListener('click', function(){
    overlay.classList.remove('active');
    popup.classList.remove('active');
});
cancelarEnvio.addEventListener('click', function(){
    overlay.classList.remove('active');
    popup.classList.remove('active');
});





async function cargarSolicitudes(){
	console.log("asdadssdasad")
	if(rol.value === "donador"){
		const enviadas = await fetch('api/donador/solicitudesEnviadas/'+nombreUsuario.value, {
   	 	method: 'GET',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 		respuestaEnviada = await enviadas.json();
 	 	console.log(respuestaEnviada);
 	 	const recibidas = await fetch('api/donador/solicitudesRecibidas/'+nombreUsuario.value, {
   	 	method: 'GET',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 	const respuestaRecibida = await recibidas.json();
 	 	
 	 	console.log(respuestaRecibida);
 	 	/*const aceptadas = await fetch('api/solicitudes/aceptadas/'+nombreUsuario.value, {
   	 	method: 'GET',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 		respuestaAceptada = await aceptadas.json();*/
 	 
 	 	//console.log(respuestaAceptada);
 	 	if(respuestaEnviada != null){
			respuestaEnviada.forEach(function(enviada){
			let tbody="<tbody>"
          	+"<tr>"
            +`<td><a href='usuarios/perfil/${enviada.usuario.id}'>${enviada.usuario.nombre } ${enviada.usuario.apellidoPaterno } ${enviada.usuario.apellidoMaterno}</a></td>`
            +"<td>"+enviada.usuario.domicilio.pais.nombrePais +"</td>"
            +"<td>"+enviada.usuario.domicilio.estado.nombre +"</td>"
            +"<td>"+enviada.usuario.domicilio.ciudad +"</td>"
            +"<td>"+enviada.tipoSangre.tipoSangre +"</td>"
            +"<td>"+enviada.fecha+"</td>"
            +`<td><input class='boton boton--primario' type='button' onclick='cancelarSolicitudDonador(${enviada.usuario.id})'value='Cancelar solicitud'></td>`
            +"</tr>"
        +"</tbody>"
        
        tabla.innerHTML += tbody;
		})
		}
		
		if(respuestaRecibida != null){
			respuestaRecibida.forEach(function(recibida){
				
			let tbody2="<tbody>"
          	+"<tr>"
            +`<td><a href='usuarios/perfil/${recibida.usuario.id}'>${recibida.usuario.nombre } ${recibida.usuario.apellidoPaterno } ${recibida.usuario.apellidoMaterno}</a></td>`
            +"<td>"+recibida.usuario.domicilio.pais.nombrePais +"</td>"
            +"<td>"+recibida.usuario.domicilio.estado.nombre +"</td>"
            +"<td>"+recibida.usuario.domicilio.ciudad +"</td>"
            +"<td>"+recibida.tipoSangre.tipoSangre +"</td>"
            +"<td>"+recibida.fecha+"</td>"
            +`<td><input class='boton boton--primario' type='button' onclick='abrirPopupDonador(${recibida.usuario.id})'value='Aceptar solicitud'></td>`
            +"</tr>"
        +"</tbody>"
        
        tabla2.innerHTML += tbody2;
		})
		}
 	 	
 	 	
 	 	
 	 	
 	 	funcionTabla();
 	 	funcionTabla2();
 	 	
 	 	}else if(rol.value === "receptor"){
		const enviadas = await fetch('api/receptor/solicitudesEnviadas/'+nombreUsuario.value, {
   	 	method: 'GET',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 		respuestaEnviada = await enviadas.json();
 	 	console.log(respuestaEnviada);
 	 	const recibidas = await fetch('api/receptor/solicitudesRecibidas/'+nombreUsuario.value, {
   	 	method: 'GET',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	
 	 	});
 	 const respuestaRecibida = await recibidas.json();
 	 	
 	 	if(respuestaEnviada != null){
			respuestaEnviada.forEach(function(enviada){
			let tbody="<tbody>"
          	+"<tr>"
            +`<td><a href='usuarios/perfil/${enviada.usuario.id}'>${enviada.usuario.nombre } ${enviada.usuario.apellidoPaterno } ${enviada.usuario.apellidoMaterno}</a></td>`
            +"<td>"+enviada.usuario.domicilio.pais.nombrePais +"</td>"
            +"<td>"+enviada.usuario.domicilio.estado.nombre +"</td>"
            +"<td>"+enviada.usuario.domicilio.ciudad +"</td>"
            +"<td>"+enviada.tipoSangre.tipoSangre +"</td>"
            +"<td>"+enviada.fecha+"</td>"
            +`<td><input class='boton boton--primario' type='button' onclick='cancelarSolicitudReceptor(${enviada.usuario.id})'value='Cancelar solicitud'></td>`
            +"</tr>"
        +"</tbody>"
        
        tabla.innerHTML += tbody;
		})
		}
		
		if(respuestaRecibida != null){
			respuestaRecibida.forEach(function(recibida){
			let tbody2="<tbody>"
          	+"<tr>"
            +`<td><a href='usuarios/perfil/${recibida.usuario.id}'>${recibida.usuario.nombre } ${recibida.usuario.apellidoPaterno } ${recibida.usuario.apellidoMaterno}</a></td>`
            +"<td>"+recibida.usuario.domicilio.pais.nombrePais +"</td>"
            +"<td>"+recibida.usuario.domicilio.estado.nombre +"</td>"
            +"<td>"+recibida.usuario.domicilio.ciudad +"</td>"
            +"<td>"+recibida.tipoSangre.tipoSangre +"</td>"
            +"<td>"+recibida.fecha+"</td>"
            +`<td><input class='boton boton--primario' type='button' onclick='abrirPopupReceptor(${recibida.usuario.id})'value='Aceptar solicitud'></td>`
            +"</tr>"
        +"</tbody>"
        
        tabla2.innerHTML += tbody2;
		})
		}
 	 	
 	 	
 	 	
 	 	
 	 	funcionTabla();
 	 	funcionTabla2();
		}
 	 	
 	 	
	
}
async function cancelarSolicitudDonador(id){
	let cancelarSolicitud = {};
	cancelarSolicitud.idReceptor = id;
	cancelarSolicitud.email = nombreUsuario.value;
	const enviadas = await fetch('api/donador/eliminarSolicitud', {
   	 	method: 'DELETE',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(cancelarSolicitud)
 	 	});
 	 		respuestaEnviada = await enviadas.text();
 	 		window.location.href = "/solicitudes";
 	 		
}
async function cancelarSolicitudReceptor(id){
	let cancelarSolicitud = {};
	cancelarSolicitud.idReceptor = id;
	cancelarSolicitud.email = nombreUsuario.value;
	const enviadas = await fetch('api/receptor/eliminarSolicitud', {
   	 	method: 'DELETE',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(cancelarSolicitud)
 	 	});
 	 		respuestaEnviada = await enviadas.text();
 	 		window.location.href = "/solicitudes";
}

async function aceptarSolicitudDonador(id){
	console.log(id)
	let aceptarSolicitud = {};
	aceptarSolicitud.idReceptor = id;
	aceptarSolicitud.email = nombreUsuario.value;
	const enviadas = await fetch('api/donador/aceptarSolicitud', {
   	 	method: 'PUT',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(aceptarSolicitud)
 	 	});
 	 		respuestaEnviada = await enviadas.text();
 	 		window.location.href = "/solicitudes";
}
async function aceptarSolicitudReceptor(id){
	let aceptarSolicitud = {};
	aceptarSolicitud.idReceptor = id;
	aceptarSolicitud.email = nombreUsuario.value;
	const enviadas = await fetch('api/receptor/aceptarSolicitud', {
   	 	method: 'PUT',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(aceptarSolicitud)
 	 	});
 	 		respuestaEnviada = await enviadas.text();
 	 		window.location.href = "/solicitudes";
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
function funcionTabla2 () {
    $('#myTable2').DataTable({
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