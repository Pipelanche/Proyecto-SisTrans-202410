
--AISLAMIENTO - TRANSACCIONES

    -- t1
        -- Sesion 1
            SET AUTOCOMMIT OFF;
            SET TRANSACTION ISOLATION LEVEL SERIALIZABLE; -- Caso 1
            -- SET TRANSACTION ISOLATION LEVEL READ COMITTED; Caso 2

    -- t2
        -- Sesion 1
            UPDATE Cuentas SET saldo = saldo + 1000000
            WHERE numero = '4000000';

    -- t3
        -- Sesion 1
            INSERT INTO Operaciones (id, tipo, monto, fechaHora, puntoDeAtencion, producto)
            VALUES (idOperaciones.nextval, 'consignacion_cuenta', 1000000.00, SYSDATE, 1, 1);

    
    -- t4
        -- Sesion 1
            UPDATE Cuentas SET saldo = saldo - 50000
            WHERE numero = '4000001';
        
        -- Sesion 2
            SET AUTOCOMMIT OFF;
            SET TRANSACTION ISOLATION LEVEL SERIALIZABLE; -- Caso 1
            -- SET TRANSACTION ISOLATION LEVEL READ COMITTED; Caso 2
    
    -- t5
        -- Sesion 1
            INSERT INTO Operaciones (id, tipo, monto, fechaHora, puntoDeAtencion, producto)
            VALUES (idOperaciones.nextval, 'retiro_cuenta', 50000.00, SYSDATE, 1, 4);


    -- t6
        -- Sesion 2
            UPDATE Cuentas SET saldo = saldo - 30000 
            WHERE numero = '4000000';
    
    -- t7
        -- Sesion 1
            COMMIT;
    
    -- t8
        -- Sesion 2
            INSERT INTO Operaciones (id, tipo, monto, fechaHora, puntoDeAtencion, producto)
            VALUES (idOperaciones.nextval, 'retiro_cuenta', 30000.00, SYSDATE, 1, 1);
    
    -- t9
        -- Sesion 2
            UPDATE Cuentas SET saldo = saldo + 5000 
            WHERE numero = '4000001';        
    
    -- t10
        -- Sesion 2
            INSERT INTO Operaciones (id, tipo, monto, fechaHora, puntoDeAtencion, producto)
            VALUES (idOperaciones.nextval, 'consignacion_cuenta', 5000.00, SYSDATE, 1, 4);    
    
    -- t11
        -- Sesion 1
            SELECT saldo 
            FROM Cuentas
            WHERE numero IN ('4000000', '4000001');

    -- t12
        -- Sesion 2
            COMMIT;
    
    -- t13
        -- Sesion 1
            SELECT saldo 
            FROM Cuentas 
            WHERE numero IN ('4000000', '4000001');
        
        -- Sesion 2
            SELECT saldo 
            FROM Cuentas 
            WHERE numero IN ('4000000', '4000001');
        
        