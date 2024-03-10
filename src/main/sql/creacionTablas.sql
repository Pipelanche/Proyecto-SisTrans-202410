
CREATE TABLE Usuarios (
    tipoDeDocumento VARCHAR(50) NOT NULL,
    numeroDeDocumento VARCHAR(50) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    nacionalidad VARCHAR(255) NOT NULL,
    direccionFisica VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    palabraClave VARCHAR(255) NOT NULL,
    tipoPersona ENUM('natural', 'juridica') NOT NULL,
    rol ENUM('cliente', 'cajero', 'gerente_oficina', 'gerente_general') NOT NULL,
    PRIMARY KEY (tipoDeDocumento, numeroDeDocumento)
);


CREATE TABLE GerentesOficina (
    tipoDeDocumento VARCHAR(50) NOT NULL,
    numeroDeDocumento VARCHAR(50) NOT NULL,
    FOREIGN KEY (tipoDeDocumento, numeroDeDocumento) REFERENCES Usuarios(tipoDeDocumento, numeroDeDocumento),
    PRIMARY KEY (tipoDeDocumento, numeroDeDocumento)
);


CREATE TABLE Ubicaciones (
    clienteTipoDeDocumento VARCHAR(50) NOT NULL,
    clienteNumeroDeDocumento VARCHAR(50) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    departamento VARCHAR(255) NOT NULL,
    codigoPostal VARCHAR(20) NOT NULL,
    FOREIGN KEY (clienteTipoDeDocumento, clienteNumeroDeDocumento) REFERENCES Usuarios(tipoDeDocumento, numeroDeDocumento),
    PRIMARY KEY (clienteTipoDeDocumento, clienteNumeroDeDocumento)
);


CREATE TABLE Productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('cuenta', 'prestamo', 'cdt') NOT NULL,
    clienteTipoDeDocumento VARCHAR(50) NOT NULL,
    clienteNumeroDeDocumento VARCHAR(50) NOT NULL,
    FOREIGN KEY (clienteTipoDeDocumento, clienteNumeroDeDocumento) REFERENCES Usuarios(tipoDeDocumento, numeroDeDocumento)
);


CREATE TABLE Cuentas (
    numero BIGINT AUTO_INCREMENT PRIMARY KEY,
    id BIGINT NOT NULL,
    tipo ENUM('ahorros', 'corriente', 'afc') NOT NULL,
    estado ENUM('activa', 'cerrada', 'desactivada') NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    fechaUltimaTransaccion DATETIME NOT NULL,
    FOREIGN KEY (id) REFERENCES Productos(id)
);


CREATE TABLE Prestamos (
    id BIGINT NOT NULL,
    tipoProducto ENUM('vivienda', 'estudio', 'automovil', 'libre_inversion') NOT NULL,
    estado ENUM('solicitado', 'aprobado', 'rechazado', 'pagado') NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    interes DECIMAL(5, 2) NOT NULL,
    cantidadCuotas INT NOT NULL,
    diaPagoDeCuotas INT NOT NULL,
    valorCuota DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (id) REFERENCES Productos(id),
    PRIMARY KEY (id)
);


CREATE TABLE Operaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('abrir_cuenta', 'cerrar_cuenta', 'consignacion_cuenta', 'retiro_cuenta', 'transferencia_cuenta', 'desactivar_cuenta', 'actualizar_cuenta', 'solicitar_prestamo', 'aprobar_prestamo', 'rechazar_prestamo', 'pago_cuota_ordinaria', 'pago_cuota_extraordinaria', 'cerrar_prestamo', 'actualizar_prestamo') NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    fechaHora DATETIME NOT NULL,
    puntoDeAtencion BIGINT NOT NULL,
    producto BIGINT NOT NULL,
    FOREIGN KEY (puntoDeAtencion) REFERENCES PuntosDeAtencion(id),
    FOREIGN KEY (producto) REFERENCES Productos(id)
);


CREATE TABLE PuntosDeAtencion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('atencion_personalizada', 'cajero_automatico', 'digital') NOT NULL
);


CREATE TABLE PuntosFisicos (
    id BIGINT NOT NULL,
    localizacionGeografica VARCHAR(255) NOT NULL,
    oficina BIGINT NOT NULL,
    FOREIGN KEY (id) REFERENCES PuntosDeAtencion(id),
    FOREIGN KEY (oficina) REFERENCES Oficinas(id),
    PRIMARY KEY (id)
);


CREATE TABLE Oficinas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    cantidadPuntosDeAtencion INT NOT NULL,
    horaAbre TIME NOT NULL,
    horaCierre TIME NOT NULL,
    gerenteTipoDeDocumento VARCHAR(50) NOT NULL,
    gerenteNumeroDeDocumento VARCHAR(50) NOT NULL,
    FOREIGN KEY (gerenteTipoDeDocumento, gerenteNumeroDeDocumento) REFERENCES Usuarios(tipoDeDocumento, numeroDeDocumento)
);
