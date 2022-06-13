const nombreUsuario = document.getElementById('nombre-usuario');
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