
INSERT INTO Usuarios (tipoDeDocumento, numeroDeDocumento, nombre, nacionalidad, direccionFisica, correo, telefono, login, palabraClave, tipoPersona, rol)
VALUES 
('CC', '100000001', 'Laura Martinez', 'Colombiana', 'Carrera 45 #12-34', 'laura.m@example.com', '3100000001', 'lauram', 'password1', 'natural', 'cliente'),
('CC', '100000002', 'Carlos Lopez', 'Mexicana', 'Calle 78 #56-78', 'carlos.l@example.com', '3100000002', 'carlosl', 'password2', 'natural', 'cliente'),
('CC', '100000003', 'Andrea Jimenez', 'Argentina', 'Avenida 9 #123-45', 'andrea.j@example.com', '3100000003', 'andreaj', 'password3', 'natural', 'cliente'),


INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES 
('cuenta', 'CC', '100000001'),
('prestamo', 'CC', '100000002'),
('cdt', 'CC', '100000003'),


INSERT INTO Cuentas (numero, id, tipo, estado, saldo, fechaUltimaTransaccion)
VALUES 
(1001, 1, 'ahorros', 'activa', 5000.00, '2023-02-01'),
(1002, 2, 'corriente', 'activa', 15000.00, '2023-02-02'),


INSERT INTO Prestamos (id, tipoProducto, estado, monto, interes, cantidadCuotas, diaPagoDeCuotas, valorCuota)
VALUES 
(3, 'vivienda', 'aprobado', 200000, 5, 120, 15, 2000),


INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES 
('abrir_cuenta', 5000.00, '2023-02-01 09:00:00', 1, 1),


INSERT INTO PuntosDeAtencion (tipo)
VALUES 
('atencion_personalizada'),
('cajero_automatico'),
('digital');


INSERT INTO PuntosFisicos (id, localizacionGeografica, oficina)
VALUES 
(1, 'Carrera 45 #12-34, Bogotá', 1),


INSERT INTO Oficinas (nombre, direccion, cantidadPuntosDeAtencion, horaAbre, horaCierre, gerenteTipoDeDocumento, gerenteNumeroDeDocumento)
VALUES 
('Oficina Principal', 'Calle 100 #23-45, Bogotá', 5, '08:00', '17:00', 'CC', '100000001'),
