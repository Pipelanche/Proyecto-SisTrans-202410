
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
    tipoPersona VARCHAR(50) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    PRIMARY KEY (tipoDeDocumento, numeroDeDocumento)
);

ALTER TABLE Usuarios
	ADD CONSTRAINT CK_USUARIOS_TIPO_PERSONA CHECK (TIPOPERSONA IN ('natural', 'juridica')) 
ENABLE;

ALTER TABLE Usuarios
	ADD CONSTRAINT CK_USUARIOS_ROL CHECK (ROL IN ('cliente', 'cajero', 'gerente_oficina', 'gerente_general')) 
ENABLE;


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
    id NUMBER(8) PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    clienteTipoDeDocumento VARCHAR(50) NOT NULL,
    clienteNumeroDeDocumento VARCHAR(50) NOT NULL,
    FOREIGN KEY (clienteTipoDeDocumento, clienteNumeroDeDocumento) REFERENCES Usuarios(tipoDeDocumento, numeroDeDocumento)
);

ALTER TABLE Productos
	ADD CONSTRAINT CK_PRODUCTOS_TIPO CHECK (TIPO IN ('cuenta', 'prestamo', 'cdt')) 
ENABLE;


CREATE TABLE Cuentas (
    numero NUMBER(8) PRIMARY KEY,
    id NUMBER(8) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL,
    fechaUltimaTransaccion DATE NOT NULL,
    FOREIGN KEY (id) REFERENCES Productos(id)
);

ALTER TABLE Cuentas
	ADD CONSTRAINT CK_CUENTAS_TIPO CHECK (TIPO IN ('ahorros', 'corriente', 'afc')) 
ENABLE;

ALTER TABLE Cuentas
	ADD CONSTRAINT CK_CUENTAS_ESTADO CHECK (ESTADO IN ('activa', 'cerrada', 'desactivada')) 
ENABLE;


CREATE TABLE Prestamos (
    id NUMBER(8) NOT NULL,
    tipoProducto VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    interes DECIMAL(5, 2) NOT NULL,
    cantidadCuotas INT NOT NULL,
    diaPagoDeCuotas INT NOT NULL,
    valorCuota DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (id) REFERENCES Productos(id),
    PRIMARY KEY (id)
);

ALTER TABLE Prestamos
	ADD CONSTRAINT CK_PRESTAMOS_TIPO_PRODUCTO CHECK (TIPOPRODUCTO IN ('vivienda', 'estudio', 'automovil', 'libre_inversion')) 
ENABLE;

ALTER TABLE Prestamos
	ADD CONSTRAINT CK_PRESTAMOS_ESTADO CHECK (ESTADO IN ('solicitado', 'aprobado', 'rechazado', 'pagado')) 
ENABLE;


CREATE TABLE PuntosDeAtencion (
    id NUMBER(8) PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL
);

ALTER TABLE PuntosDeAtencion
	ADD CONSTRAINT CK_PUNTOSDEATENCION_TIPO CHECK (TIPO IN ('atencion_personalizada', 'cajero_automatico', 'digital')) 
ENABLE;


CREATE TABLE Oficinas (
    id NUMBER(8) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    cantidadPuntosDeAtencion INT NOT NULL,
    horaAbre INTERVAL DAY(0) TO SECOND(0) NOT NULL,
    horaCierre INTERVAL DAY(0) TO SECOND(0) NOT NULL,
    gerenteTipoDeDocumento VARCHAR(50) NOT NULL,
    gerenteNumeroDeDocumento VARCHAR(50) NOT NULL,
    FOREIGN KEY (gerenteTipoDeDocumento, gerenteNumeroDeDocumento) REFERENCES Usuarios(tipoDeDocumento, numeroDeDocumento)
);


CREATE TABLE PuntosFisicos (
    id NUMBER(8) NOT NULL,
    localizacionGeografica VARCHAR(255) NOT NULL,
    oficina NUMBER(8) NOT NULL,
    FOREIGN KEY (id) REFERENCES PuntosDeAtencion(id),
    FOREIGN KEY (oficina) REFERENCES Oficinas(id),
    PRIMARY KEY (id)
);


CREATE TABLE Operaciones (
    id NUMBER(8) PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    fechaHora DATE NOT NULL,
    puntoDeAtencion NUMBER(8) NOT NULL,
    producto NUMBER(8) NOT NULL,
    FOREIGN KEY (puntoDeAtencion) REFERENCES PuntosDeAtencion(id),
    FOREIGN KEY (producto) REFERENCES Productos(id)
);

ALTER TABLE Operaciones
	ADD CONSTRAINT CK_OPERACIONES_TIPO CHECK (TIPO IN ('abrir_cuenta', 'cerrar_cuenta', 'consignacion_cuenta', 'retiro_cuenta', 'transferencia_cuenta', 'desactivar_cuenta', 'actualizar_cuenta', 'solicitar_prestamo', 'aprobar_prestamo', 'rechazar_prestamo', 'pago_cuota_ordinaria', 'pago_cuota_extraordinaria', 'cerrar_prestamo', 'actualizar_prestamo')) 
ENABLE;

