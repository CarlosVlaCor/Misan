async function funcionRegistro(){
	console.log('1')
	let datos = {};
	datos.nombre = document.getElementById('nombre').value;
	datos.apellidoPaterno = document.getElementById('apellidoPaterno').value;
	datos.apellidoMaterno = document.getElementById('apellidoMaterno').value;
	datos.fechaNacimiento = document.getElementById('fechaNacimiento').value;

	datos.tipoSangre = {};
	datos.tipoSangre.id = document.getElementById('tipoSangre').value;
	
	datos.domicilio = {};
	datos.domicilio.ciudad = document.getElementById('ciudad').value;
	datos.domicilio.estado = {}//
	datos.domicilio.estado.id = document.getElementById('estados').value;
	datos.domicilio.pais = {}//
	datos.domicilio.pais.id = document.getElementById('paises').value;
	console.log('c')
	datos.email = document.getElementById('email').value;
	datos.telefono = document.getElementById('telefono').value;
	datos.password = document.getElementById('contrasena').value;
	console.log('d')
	
	const response = await fetch('api/registro', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  console.log('e')
  const content = await response.text();

  	
   	if(response.ok){
	 window.location.href = "/login";
	 console.log("AAAA")
	}
}

async function funcionLogin(){
	let datos = {};
	datos.correo = document.getElementById('email').value;
	
	datos.contrasena = document.getElementById('contrasena').value;
	
	const response = await fetch('api/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  
  const content = await response.text();
  console.log(content);
 	 if(content !== 'Fail'){
		localStorage.token = content;
		localStorage.correo = correo;
		window.location.href = "/";
	}
 
}
