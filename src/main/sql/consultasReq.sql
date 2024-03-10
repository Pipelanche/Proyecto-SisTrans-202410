-- RFC1 - Consultar las cuentas en BancAndes
SELECT Cuentas.* 
FROM Cuentas 
JOIN Productos ON Cuentas.id = Productos.id 
JOIN Usuarios ON Productos.clienteTipoDeDocumento = Usuarios.tipoDeDocumento 
AND Productos.clienteNumeroDeDocumento = Usuarios.numeroDeDocumento
WHERE Cuentas.tipo = 'ahorros' -- Ejemplo de filtro por tipo de cuenta
AND Cuentas.saldo BETWEEN 1000 AND 5000 -- Ejemplo de rango de saldos
AND Cuentas.fechaUltimaTransaccion BETWEEN '2023-01-01' AND '2023-12-31' -- Ejemplo de rango de fechas
ORDER BY Cuentas.tipo, Cuentas.saldo; -- Ejemplo de agrupamiento y orden


-- RFC2 - Consultar un Cliente
SELECT Usuarios.*, Cuentas.numero AS NumeroCuenta, Prestamos.id AS IdPrestamo
FROM Usuarios
LEFT JOIN Productos ON Usuarios.tipoDeDocumento = Productos.clienteTipoDeDocumento 
AND Usuarios.numeroDeDocumento = Productos.clienteNumeroDeDocumento
LEFT JOIN Cuentas ON Productos.id = Cuentas.id
LEFT JOIN Prestamos ON Productos.id = Prestamos.id
WHERE Usuarios.tipoDeDocumento = 'CC' AND Usuarios.numeroDeDocumento = '123456789'; -- Ejemplo de cliente específico


-- RFC3 - Extracto Bancario para una Cuenta
    -- Calculando el saldo inicial antes del mes especificado
WITH SaldoInicial AS (
    SELECT Cuentas.numero, SUM(CASE WHEN Operaciones.tipo IN ('consignacion_cuenta', 'transferencia_cuenta') THEN Operaciones.monto
                                    WHEN Operaciones.tipo IN ('retiro_cuenta', 'pago_cuota_ordinaria', 'pago_cuota_extraordinaria') THEN -Operaciones.monto
                                    ELSE 0 END) AS SaldoInicial
    FROM Cuentas
    LEFT JOIN Operaciones ON Cuentas.numero = Operaciones.producto
    WHERE Cuentas.numero = 1001 AND MONTH(Operaciones.fechaHora) < 2 AND YEAR(Operaciones.fechaHora) = 2023
),
    -- Consulta para el extracto bancario
ExtractoBancario AS (
    SELECT Cuentas.numero, Operaciones.tipo, Operaciones.monto, Operaciones.fechaHora
    FROM Cuentas
    LEFT JOIN Operaciones ON Cuentas.numero = Operaciones.producto
    WHERE Cuentas.numero = 1001 AND MONTH(Operaciones.fechaHora) = 2 AND YEAR(Operaciones.fechaHora) = 2023
)
SELECT * FROM SaldoInicial
UNION ALL
SELECT * FROM ExtractoBancario
UNION ALL
SELECT 'Saldo final' AS descripcion, SUM(monto) OVER () + (SELECT SaldoInicial FROM SaldoInicial) AS SaldoFinal, NULL, NULL FROM ExtractoBancario;
