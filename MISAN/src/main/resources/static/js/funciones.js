var abrirPopup = document.getElementById('abrir-popup'),
    cerrarPopup = document.getElementById('cerrar--popup'),
    overlay = document.getElementById('overlay--solicitudes'),
    popup = document.getElementById('popup'),
    cancelarEnvio = document.getElementById('cancelar-envio'),
    contCorreo = document.getElementById('cont-correo'),
    contTelefono = document.getElementById('cont-telefono'),
    otroCorreo = document.getElementById('otro-correo'),
    otroTelefono = document.getElementById('otro-telefono');

abrirPopup.addEventListener('click',function(){
    overlay.classList.add('active');
    popup.classList.add('active');
    console.log('a')
});

cerrarPopup.addEventListener('click', function(){
    overlay.classList.remove('active');
    popup.classList.remove('active');
});
cancelarEnvio.addEventListener('click', function(){
    overlay.classList.remove('active');
    popup.classList.remove('active');
});
otroCorreo.addEventListener('change', function(){
    if(otroCorreo.checked){
        contCorreo.classList.add('active');
    }else{
        contCorreo.classList.remove('active');
    }
})
otroTelefono.addEventListener('change', function() {
    if(otroTelefono.checked){
        contTelefono.classList.add('active');
    }else{
        contTelefono.classList.remove('active');
    }
});