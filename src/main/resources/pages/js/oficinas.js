function createOficina() {
    const Oficina = {
        nombre: document.getElementById('nombre').value,
        direccion: document.getElementById('direccion').value,
        cantidadPuntosDeAtencion: document.getElementById('cantidadPuntosDeAtencion').value,
        horaAbre: document.getElementById('horaAbre').value,
        horaCierre: document.getElementById('horaCierre').value,
    };
    fetch('/oficinas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(oficina),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Oficina creado:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function loadOficinas() {
    fetch('/oficinas')
    .then(response => response.json())
    .then(data => {
        const list = document.getElementById('oficinasList');
        list.innerHTML = '';
        data.forEach(oficina => {
            const item = document.createElement('div');
            item.textContent = oficina.nombre; 
            list.appendChild(item);
        });
    });
}

window.onload = function() {
    loadOficinas();
};