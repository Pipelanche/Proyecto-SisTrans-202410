function createCuenta() {
    const Cuenta = {
        numero: document.getElementById('numero').value,
        tipoCuenta: document.getElementById('tipoCuenta').value,
        estado: document.getElementById('estado').value,
        saldo: document.getElementById('saldo').value,
        fechaUltimaTransaccion: document.getElementById('fechaUltimaTransaccion').value,
    };
    fetch('/cuentas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(cuenta),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Cuenta creado:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function loadCuentas() {
    fetch('/cuentas')
    .then(response => response.json())
    .then(data => {
        const list = document.getElementById('cuentasList');
        list.innerHTML = '';
        data.forEach(cuenta => {
            const item = document.createElement('div');
            item.textContent = cuenta.nombre; 
            list.appendChild(item);
        });
    });
}

window.onload = function() {
    loadCuentas();
};