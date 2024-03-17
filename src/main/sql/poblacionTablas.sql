-- Población de Usuarios
INSERT INTO Usuarios (tipoDeDocumento, numeroDeDocumento, nombre, nacionalidad, direccionFisica, correo, telefono, login, palabraClave, tipoPersona, rol)
VALUES 
('CC', '1000000001', 'Alejandro Gomez', 'Colombiana', 'Calle 1 #23-45', 'alejandro.gomez@example.com', '3001234567', 'alejogomez', 'pass1234', 'natural', 'cliente');
INSERT INTO Usuarios (tipoDeDocumento, numeroDeDocumento, nombre, nacionalidad, direccionFisica, correo, telefono, login, palabraClave, tipoPersona, rol)
VALUES 
('CC', '1000000002', 'Carla Espinosa', 'Mexicana', 'Avenida 6 #78-90', 'carla.espinosa@example.com', '3007654321', 'carlaespi', 'pass5678', 'natural', 'cajero');
INSERT INTO Usuarios (tipoDeDocumento, numeroDeDocumento, nombre, nacionalidad, direccionFisica, correo, telefono, login, palabraClave, tipoPersona, rol)
VALUES 
('CC', '1000000003', 'Luis Morales', 'Peruana', 'Carrera 34 #56-78', 'luis.morales@example.com', '3012345678', 'luismorales', 'pass9012', 'natural', 'gerente_oficina');
INSERT INTO Usuarios (tipoDeDocumento, numeroDeDocumento, nombre, nacionalidad, direccionFisica, correo, telefono, login, palabraClave, tipoPersona, rol)
VALUES 
('CC', '1000000004', 'Sofia Castro', 'Argentina', 'Diagonal 45 #67-89', 'sofia.castro@example.com', '3023456789', 'sofiacastro', 'pass3456', 'juridica', 'gerente_general');


-- Población de GerentesOficina: Luis Morales
INSERT INTO GerentesOficina (tipoDeDocumento, numeroDeDocumento)
VALUES ('CC', '1000000003');


-- Ubicación para Alejandro Gomez (cliente)
INSERT INTO Ubicaciones (clienteTipoDeDocumento, clienteNumeroDeDocumento, ciudad, departamento, codigoPostal)
VALUES ('CC', '1000000001', 'Bogotá', 'Cundinamarca', '110111');
-- Ubicación para Carla Espinosa (cajero)
INSERT INTO Ubicaciones (clienteTipoDeDocumento, clienteNumeroDeDocumento, ciudad, departamento, codigoPostal)
VALUES ('CC', '1000000002', 'Medellín', 'Antioquia', '050001');
-- Ubicación para Luis Morales (gerente de oficina)
INSERT INTO Ubicaciones (clienteTipoDeDocumento, clienteNumeroDeDocumento, ciudad, departamento, codigoPostal)
VALUES ('CC', '1000000003', 'Cali', 'Valle del Cauca', '760001');
-- Ubicación para Sofia Castro (gerente general)
INSERT INTO Ubicaciones (clienteTipoDeDocumento, clienteNumeroDeDocumento, ciudad, departamento, codigoPostal)
VALUES ('CC', '1000000004', 'Barranquilla', 'Atlántico', '080001');


-- Producto tipo cuenta para Alejandro Gomez (cliente) id: 1
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('cuenta', 'CC', '1000000001');
-- Producto tipo prestamo para Carla Espinosa (cajero) id: 2
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('prestamo', 'CC', '1000000002');
-- Producto tipo cdt para Luis Morales (gerente de oficina) id: 3
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('cdt', 'CC', '1000000003'); 
-- Producto tipo cuenta para Sofia Castro (gerente general) id: 4
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('cuenta', 'CC', '1000000004');
-- Producto tipo prestamo para Alejandro Gomez (cliente) id: 5
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('prestamo', 'CC', '1000000001');
-- Producto tipo cdt para Carla Espinosa (cajero) id: 6
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('cdt', 'CC', '1000000002');
-- Producto tipo prestamo y cdt para Sofia Castro (gerente general) id: 7 y 8
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('prestamo', 'CC', '1000000004');
INSERT INTO Productos (tipo, clienteTipoDeDocumento, clienteNumeroDeDocumento)
VALUES ('cdt', 'CC', '1000000004');


-- Cuenta de tipo 'ahorros' para el producto asignado a Alejandro Gomez
INSERT INTO Cuentas (id, tipo, estado, saldo, fechaUltimaTransaccion)
VALUES (1, 'ahorros', 'activa', 10000.00, '2023-01-10');
-- Cuenta de tipo 'corriente' para el segundo producto asignado a Sofia Castro
INSERT INTO Cuentas (id, tipo, estado, saldo, fechaUltimaTransaccion)
VALUES (4, 'corriente', 'activa', 5000.00, '2023-01-15');


-- Préstamo de tipo 'estudio' para el producto asignado a Carla Espinosa
INSERT INTO Prestamos (id, tipoProducto, estado, monto, interes, cantidadCuotas, diaPagoDeCuotas, valorCuota)
VALUES (2, 'estudio', 'aprobado', 20000.00, 3.5, 24, 15, 850.00);
-- Préstamo de tipo 'vivienda' para el producto asignado a Alejandro Gomez
INSERT INTO Prestamos (id, tipoProducto, estado, monto, interes, cantidadCuotas, diaPagoDeCuotas, valorCuota)
VALUES (5, 'vivienda', 'aprobado', 150000.00, 4.5, 120, 30, 1250.00);
-- Préstamo de tipo 'libre_inversion' para el producto asignado a Sofia Castro
INSERT INTO Prestamos (id, tipoProducto, estado, monto, interes, cantidadCuotas, diaPagoDeCuotas, valorCuota)
VALUES (7, 'libre_inversion', 'aprobado', 30000.00, 4.0, 36, 10, 860.00);


-- Población de PuntosDeAtencion 
INSERT INTO PuntosDeAtencion (tipo)
VALUES ('atencion_personalizada');
INSERT INTO PuntosDeAtencion (tipo)
VALUES ('cajero_automatico');
INSERT INTO PuntosDeAtencion (tipo)
VALUES ('digital');


-- Punto Físico para Atención Personalizada 
INSERT INTO PuntosFisicos (id, localizacionGeografica, oficina)
VALUES (1, '13.070765889700894, 91.12660993902222', 1);
-- Punto Físico para Cajero Automático
INSERT INTO PuntosFisicos (id, localizacionGeografica, oficina)
VALUES (2, '-75.85161650827347, -69.33133482461028', 1);


-- Población de Oficinas
INSERT INTO Oficinas (nombre, direccion, cantidadPuntosDeAtencion, horaAbre, horaCierre, gerenteTipoDeDocumento, gerenteNumeroDeDocumento)
VALUES ('Oficina Central', 'Avenida Principal 100, Ciudad Nueva', 1, '08:00', '18:00', 'CC', '1000000003');


-- Operación 'abrir_cuenta' para Alejandro Gomez 
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('abrir_cuenta', 10000.00, '2023-01-10 09:00:00', 1, 1);
-- Operación 'solicitar_prestamo' para Carla Espinosa 
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('solicitar_prestamo', 20000.00, '2023-01-11 10:00:00', 1, 2);
-- Operación 'aprobar_prestamo' para el mismo préstamo de Carla Espinosa
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('aprobar_prestamo', 20000.00, '2023-01-12 11:00:00', 1, 2);
-- Operación 'consignacion_cuenta' para la cuenta de Alejandro Gomez
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('consignacion_cuenta', 5000.00, '2023-01-13 12:00:00', 1, 1);
-- Operación 'abrir_cuenta' para Sofia Castro
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('abrir_cuenta', 5000.00, '2023-01-15 14:00:00', 1, 4);
-- Operación 'solicitar_prestamo' para Sofia Castro
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('solicitar_prestamo', 30000.00, '2023-01-16 15:00:00', 1, 7);
-- Operación 'aprobar_prestamo' para el mismo préstamo de Sofia Castro
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('aprobar_prestamo', 30000.00, '2023-01-17 16:00:00', 1, 7);
-- Operación 'pago_cuota_ordinaria' para el préstamo de Carla Espinosa
INSERT INTO Operaciones (tipo, monto, fechaHora, puntoDeAtencion, producto)
VALUES ('pago_cuota_ordinaria', 850.00, '2023-02-15 13:00:00', 3, 2);
