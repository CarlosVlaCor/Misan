const donador = document.getElementById('boton-donador'),
    receptor = document.getElementById('boton-receptor'),
    continuar = document.getElementById('ser-continuar'),
    tipoSangre = document.getElementById('tipoSangre'),
    nombreUsuario = document.getElementById('nombre-usuario');
let eleccion = '';
let donadorDesactivado = false;
let receptorDesactivado = false;
donador.addEventListener('click',function(){
   if(donadorDesactivado == false){
        receptor.classList.remove('deshabilitado');
        donador.classList.add('deshabilitado');
        donadorDesactivado = true;
        receptorDesactivado = false;
        console.log(tipoSangre.value)
        eleccion = 'donador';
        if(tipoSangre.value !== 'Seleccione tipo de sangre'){
            continuar.disabled=false;
            continuar.classList.remove('deshabilitado');
        }
   }
    
    
});

receptor.addEventListener('click',function(){
    if(receptorDesactivado == false){
        donador.classList.remove('deshabilitado');
        receptor.classList.add('deshabilitado');
        receptorDesactivado = true;
        donadorDesactivado = false;
        eleccion = 'receptor';
        if(tipoSangre.value !== 'Seleccione tipo de sangre'){
            continuar.disabled=false;
            continuar.classList.remove('deshabilitado');
        }
   }
   
    
});

tipoSangre.addEventListener('change',function(){
    if(tipoSangre.value !== 'Seleccione tipo de sangre'){
        if(donadorDesactivado === true || receptorDesactivado === true){
            continuar.disabled = false;
            continuar.classList.remove('deshabilitado');
        }
    }else{
        continuar.disabled = true;
        continuar.classList.add('deshabilitado');
    }
    
});

async function funContinuar(){
	console.log(nombreUsuario.value);
    if(eleccion === "donador"){
		console.log("sad");
		let datos = {};
		datos.email = nombreUsuario.value;
		datos.tipoSangre = {};
		datos.tipoSangre.id = document.getElementById('tipoSangre').value;
		const response = await fetch('api/donador/serDonador', {
   	 	method: 'POST',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(datos)
 	 	});
 	 	
 	 	if(response.ok){
	 	window.location.href = "/donador";
		}
	}else{
		console.log("sad");
		let datos = {};
		datos.email = nombreUsuario.value;
		datos.tipoSangre = {};
		datos.tipoSangre.id = document.getElementById('tipoSangre').value;
		const response = await fetch('api/receptor/serReceptor', {
   	 	method: 'POST',
   		headers: {
      		'Accept': 'application/json',
      		'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(datos)
 	 	});
 	 	
 	 	if(response.ok){
	 	window.location.href = "/receptor";
		}
	}
}