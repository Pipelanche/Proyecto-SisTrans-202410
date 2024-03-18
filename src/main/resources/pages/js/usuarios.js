function createUsuario() {
    const usuario = {
        tipoDeDocumento: document.getElementById('tipoDeDocumento').value,
        numeroDeDocumento: document.getElementById('numeroDeDocumento').value,
        nombre: document.getElementById('nombre').value,
        nacionalidad: document.getElementById('nacionalidad').value,
        direccionFisica: document.getElementById('direccionFisica').value,
        correo: document.getElementById('correo').value,
        telefono: document.getElementById('telefono').value,
        login: document.getElementById('login').value,
        palabraClave: document.getElementById('palabraClave').value,
    };
    fetch('/usuarios', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(usuario),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Usuario creado:', data);
        // Actualizar la lista de usuarios o mostrar un mensaje
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// Función para cargar y mostrar todos los usuarios
function loadUsuarios() {
    fetch('/usuarios')
    .then(response => response.json())
    .then(data => {
        const list = document.getElementById('usuariosList');
        list.innerHTML = '';
        data.forEach(usuario => {
            const item = document.createElement('div');
            item.textContent = usuario.nombre; // Modificar para mostrar la información deseada
            list.appendChild(item);
        });
    });
}

// Llamar a loadUsuarios al cargar la página
window.onload = function() {
    loadUsuarios();
};
