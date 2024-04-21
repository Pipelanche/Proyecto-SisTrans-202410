
--AISLAMIENTO - TRANSACCIONES

    -- t1
        -- Sesion 1
            SET AUTOCOMMIT OFF;
            SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;

    -- t2
        -- Sesion 1


    -- t3
        -- Sesion 1

    
    -- t4
        -- Sesion 1

        
        -- Sesion 2
            SET AUTOCOMMIT OFF;
            SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    
    -- t5
        -- Sesion 1


    -- t6
        -- Sesion 2

    
    -- t7
        -- Sesion 1
            COMMIT;
    
    -- t8
        -- Sesion 2

    
    -- t9
        -- Sesion 2
            
    
    -- t10
        -- Sesion 2
            
    
    -- t11
        -- Sesion 1
    

    -- t12
        -- Sesion 2
            COMMIT;
    
    -- t13
        -- Sesion 1
            
        
        -- Sesion 2
        
        