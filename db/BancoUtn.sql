CREATE DATABASE BancoUtn;

USE BancoUtn;

CREATE TABLE Sexo(
    ID_sexo_se INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL 
);

CREATE TABLE Provincia(
    ID_Provincia_Prv INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Prov_Prv VARCHAR(50) NOT NULL 
);

CREATE TABLE Localidad(
    ID_Localidad_Lca INT AUTO_INCREMENT PRIMARY KEY,
    ID_Provincia_Prv_Lca INT,
    Nombre_Loc_Lca VARCHAR(50) NOT NULL,
    FOREIGN KEY (ID_Provincia_Prv_Lca) REFERENCES Provincia(ID_Provincia_Prv)
);

CREATE TABLE Nacionalidad(
    Id_Nacionalidad_nc CHAR(2) NOT NULL,
    Descripcion_nc VARCHAR(30) NOT NULL,
    CONSTRAINT PK_Id_Nac PRIMARY KEY (Id_Nacionalidad_nc)
);

CREATE TABLE TipoCuenta(
    Id_Tipo_Cuenta INT AUTO_INCREMENT,
    Nombre_Tipo VARCHAR(30) NOT NULL,
    CONSTRAINT PK_Id_TipoC PRIMARY KEY (Id_Tipo_Cuenta)
);

CREATE TABLE Cliente(
    cuil_Cli VARCHAR(20) PRIMARY KEY, 
    dni_Cli INT NOT NULL, 
    nombre_Clii VARCHAR(50) NOT NULL,
    apellido_Cli VARCHAR(50) NOT NULL, 
    ID_sexo_Cli INT, 
    ID_Nacionalidad_Cli CHAR(2),
    fecha_nacimiento_Cli DATE,
    direccion_Cli VARCHAR(100), 
    ID_Localidad_cli INT, 
    correo_electronico_Cli VARCHAR(100),
    telefono_Cli VARCHAR(30),
    estado_Cli BOOLEAN DEFAULT 0,
    FOREIGN KEY (ID_sexo_Cli) REFERENCES Sexo(ID_sexo_se),
    FOREIGN KEY (ID_Nacionalidad_Cli) REFERENCES Nacionalidad(Id_Nacionalidad_nc),
    FOREIGN KEY (ID_Localidad_Cli) REFERENCES Localidad(ID_Localidad_Lca)
);

CREATE TABLE Cuenta (
    Numero_de_Cuenta_Cu INT AUTO_INCREMENT PRIMARY KEY,
    Cuil_Cli_Cu VARCHAR(20),  
    Fecha_Creacion_Cu DATE,
    Id_Tipo_Cuenta INT,
    CBU_Cu VARCHAR(22),
    Saldo_Cu DECIMAL(11,2) DEFAULT 10000,
    Estado_Cu BOOLEAN DEFAULT 1,
    FOREIGN KEY (Cuil_Cli_Cu) REFERENCES Cliente(cuil_Cli),  
    FOREIGN KEY (Id_Tipo_Cuenta) REFERENCES TipoCuenta(Id_Tipo_Cuenta), 
    CONSTRAINT UK_CBU UNIQUE (CBU_Cu)
) AUTO_INCREMENT = 1001;

CREATE TABLE TipoMovimiento(
    Id_TipoMov_TM INT AUTO_INCREMENT,
    Descripcion_TM VARCHAR(40) NOT NULL,
    CONSTRAINT PK_TipoMov PRIMARY KEY (Id_TipoMov_TM)
);

CREATE TABLE Movimiento(
    Id_Movimiento_Mov INT AUTO_INCREMENT,
    FechaMovimiento_Mov DATETIME DEFAULT CURRENT_TIMESTAMP,
    Detalle_Mov VARCHAR(200),
    Importe_Mov DECIMAL(11,2) NOT NULL,
    Id_TipoMov_TM_Mov INT NOT NULL,
    CONSTRAINT PK_Id_Mov PRIMARY KEY (Id_Movimiento_Mov),
    CONSTRAINT FK_Id_TipoM FOREIGN KEY (Id_TipoMov_TM_Mov) REFERENCES TipoMovimiento(Id_TipoMov_TM)
);

CREATE TABLE MovimientoXCuenta(
    Id_Movimiento__Mov_MXC INT NOT NULL,
    Num_Cuenta_Cu_MXC INT NOT NULL,
    CONSTRAINT PK_Id_MXC PRIMARY KEY (Id_Movimiento__Mov_MXC, Num_Cuenta_Cu_MXC),
    CONSTRAINT FK_Mov FOREIGN KEY (Id_Movimiento__Mov_MXC) REFERENCES Movimiento(Id_Movimiento_Mov),
    CONSTRAINT FK_Cuenta FOREIGN KEY (Num_Cuenta_Cu_MXC) REFERENCES Cuenta(Numero_de_Cuenta_Cu)
);

CREATE TABLE Usuario(
    Cuil_us VARCHAR(20) NOT NULL,
    Usuario_us VARCHAR(20) NOT NULL UNIQUE,
    Contrasenia_us VARCHAR(20) NOT NULL,
    Rol_us BOOLEAN NOT NULL,
    Estado_us BOOLEAN NOT NULL DEFAULT 1,
    CONSTRAINT PK_Cuil PRIMARY KEY (Cuil_us)
);

CREATE TABLE InteresXCantidadDMeses(
    Plazo_d_Pagos_En_meses_IXM CHAR(3) PRIMARY KEY,
    Interes_IXM DECIMAL(5, 2),
    Meses_int INT,
    CONSTRAINT PK_Meses UNIQUE (Meses_int)
);

CREATE TABLE Prestamo(
    ID_Prestamo_Pt INT AUTO_INCREMENT PRIMARY KEY,
    Numero_de_Cuenta_Cu_Pt INT,
    Fecha_Peticion_Pt DATETIME DEFAULT CURRENT_TIMESTAMP,
    Importe_solicitado_Pt DECIMAL(11,2),
    Plazo_Pago_Pt CHAR(3),
    Detalle_solicitud_Pt VARCHAR(200),
    Estado_Pt BOOLEAN NOT NULL DEFAULT 0,
    CONSTRAINT FK_Cuenta_Prestamo FOREIGN KEY (Numero_de_Cuenta_Cu_Pt) REFERENCES Cuenta(Numero_de_Cuenta_Cu),
    CONSTRAINT FK_Interes FOREIGN KEY (Plazo_Pago_Pt) REFERENCES InteresXCantidadDMeses(Plazo_d_Pagos_En_meses_IXM)
);

CREATE TABLE DetallesXPrestamo(
    ID_Prestamo_Pt_Dt INT PRIMARY KEY,
    Importe_C_Interes_Dt  DECIMAL(11,2),
    Importe_X_Cuotas_Dt  DECIMAL(11,2),
    Cantidad_Cuotas_Dt  INT,
    CONSTRAINT FK_Prestamo_Detalle FOREIGN KEY (ID_Prestamo_Pt_Dt) REFERENCES Prestamo(ID_Prestamo_Pt)
);

CREATE TABLE CuotasXPrestamos(
    ID_Prestamo_Pt_Cp INT,
    Fecha_vencimiento_Cp DATE,
    N_Cuota INT NOT NULL,
   	pagada BOOLEAN DEFAULT 0,
    
    CONSTRAINT FK_Prestamo_Cuota FOREIGN KEY (ID_Prestamo_Pt_Cp) REFERENCES Prestamo(ID_Prestamo_Pt),
    CONSTRAINT UK_CuotasXPrestamos UNIQUE (ID_Prestamo_Pt_Cp, Fecha_vencimiento_Cp, N_Cuota)
);

CREATE TABLE Transferencias (
    Id_Transferencia INT AUTO_INCREMENT PRIMARY KEY,
    Cuenta_Origen INT NOT NULL,
   CBU_CuentaDestino VARCHAR(22) NOT NULL,
    Monto DECIMAL(11, 2) NOT NULL,
    Fecha_Transferencia DATETIME DEFAULT CURRENT_TIMESTAMP,
    Detalle VARCHAR(100),
    FOREIGN KEY (Cuenta_Origen) REFERENCES Cuenta(Numero_de_Cuenta_Cu),
    FOREIGN KEY (CBU_CuentaDestino) REFERENCES Cuenta(CBU_Cu)
);
DELIMITER $$

CREATE TRIGGER before_insert_cuenta
BEFORE INSERT ON Cuenta
FOR EACH ROW
BEGIN
    DECLARE ultimoCBU BIGINT;

    -- agarramos el Ãºltimo valor de CBU_Cu como un nÃºmero entero
    SELECT MAX(CAST(CBU_Cu AS UNSIGNED)) INTO ultimoCBU
    FROM Cuenta;

    -- si no hay valores previos en la tabla, se inicia con el nÃºmero base
    IF ultimoCBU IS NULL THEN
        SET ultimoCBU = 5500990000000001;
    ELSE
        SET ultimoCBU = ultimoCBU + 1;
    END IF;

    -- asignamos el nuevo CBU a la nueva cuenta
    SET NEW.CBU_Cu = CAST(ultimoCBU AS CHAR(22));

    -- Establecer saldo inicial de la cuenta a 10000
    SET NEW.Saldo_Cu = 10000;  -- Valor predeterminado del saldo
END $$

DELIMITER ;

DELIMITER $$

create TRIGGER after_insert_cuenta
AFTER INSERT ON Cuenta
FOR EACH ROW
BEGIN
    -- Insertar un movimiento para la nueva cuenta
    INSERT INTO Movimiento (Detalle_Mov, Importe_Mov, Id_TipoMov_TM_Mov)
    VALUES (CONCAT('Alta de cuenta'), 10000, 1);  -- Monto por defecto

    -- Obtener el ID del movimiento reci�n insertado
    SET @MovimientoID = LAST_INSERT_ID();

    -- Insertar la relaci�n en MovimientoXCuenta
    INSERT INTO MovimientoXCuenta (Id_Movimiento__Mov_MXC, Num_Cuenta_Cu_MXC)
    VALUES (@MovimientoID, NEW.Numero_de_Cuenta_Cu);
END $$

DELIMITER ;




DELIMITER $$

CREATE PROCEDURE RealizarTransferencia(
    IN CuentaOrigen INT,
    IN CBU_CuentaDestino VARCHAR(22),
    IN Monto DECIMAL(11, 2),
    IN DETALLE VARCHAR(100)
)
BEGIN
    -- Declarar variables
    DECLARE SaldoOrigen DECIMAL(11, 2);
    DECLARE CuentaDestino INT;
    DECLARE MovimientoOrigenID INT;
    DECLARE MovimientoDestinoID INT;

    -- Obtener el n�mero de cuenta basado en el CBU
    SELECT Numero_de_Cuenta_Cu INTO CuentaDestino
    FROM Cuenta
    WHERE CBU_Cu = CBU_CuentaDestino AND Estado_Cu = 1;

    IF CuentaDestino IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El CBU proporcionado no corresponde a una cuenta activa.';
    END IF;

    -- Verificar que la cuenta de origen existe y est� activa
    IF NOT EXISTS (
        SELECT 1 FROM Cuenta WHERE Numero_de_Cuenta_Cu = CuentaOrigen AND Estado_Cu = 1
    ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La cuenta de origen no existe o est� inactiva.';
    END IF;

    -- Obtener saldo de la cuenta de origen
    SELECT Saldo_Cu INTO SaldoOrigen FROM Cuenta WHERE Numero_de_Cuenta_Cu = CuentaOrigen;

    -- Verificar saldo suficiente
    IF SaldoOrigen < Monto THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Saldo insuficiente en la cuenta de origen.';
    END IF;

    -- Actualizar saldos
    UPDATE Cuenta SET Saldo_Cu = Saldo_Cu - Monto WHERE Numero_de_Cuenta_Cu = CuentaOrigen;
    UPDATE Cuenta SET Saldo_Cu = Saldo_Cu + Monto WHERE Numero_de_Cuenta_Cu = CuentaDestino;

    -- Registrar transferencia
    INSERT INTO Transferencias (Cuenta_Origen, CBU_CuentaDestino, Monto, Detalle)
    VALUES (CuentaOrigen, CBU_CuentaDestino, Monto, Detalle);

    -- Registrar movimiento para la cuenta de origen
    INSERT INTO Movimiento (Detalle_Mov, Importe_Mov, Id_TipoMov_TM_Mov)
    VALUES (CONCAT('', Detalle), -Monto, 2);

    -- Obtener el ID del movimiento reci�n creado
    SET MovimientoOrigenID = LAST_INSERT_ID();

    -- Registrar en MovimientoXCuenta para la cuenta de origen
    INSERT INTO MovimientoXCuenta (Id_Movimiento__Mov_MXC, Num_Cuenta_Cu_MXC)
    VALUES (MovimientoOrigenID, CuentaOrigen);

    -- Registrar movimiento para la cuenta de destino
    INSERT INTO Movimiento (Detalle_Mov, Importe_Mov, Id_TipoMov_TM_Mov)
    VALUES (CONCAT('', Detalle), +Monto, 2);

    -- Obtener el ID del movimiento reci�n creado
    SET MovimientoDestinoID = LAST_INSERT_ID();

    -- Registrar en MovimientoXCuenta para la cuenta de destino
    INSERT INTO MovimientoXCuenta (Id_Movimiento__Mov_MXC, Num_Cuenta_Cu_MXC)
    VALUES (MovimientoDestinoID, CuentaDestino);
END$$

DELIMITER ;

    

	DELIMITER $$

	CREATE TRIGGER GenerarCBU
	BEFORE INSERT ON Cuenta
	FOR EACH ROW
	BEGIN
		DECLARE ultimoCBU BIGINT;

		-- Obtener el �ltimo valor de CBU_Cu como un n�mero entero
		SELECT MAX(CAST(CBU_Cu AS UNSIGNED)) INTO ultimoCBU
		FROM Cuenta;

		-- Si no hay valores previos en la tabla, se inicia con el n�mero base
		IF ultimoCBU IS NULL THEN
			SET ultimoCBU = 5500990000000001;
		ELSE
			SET ultimoCBU = ultimoCBU + 1;
		END IF;

		-- Asignar el nuevo CBU a la nueva cuenta
		SET NEW.CBU_Cu = CAST(ultimoCBU AS CHAR(22));
	END$$


    
DELIMITER $$
CREATE TRIGGER after_cliente_insert
AFTER INSERT ON Cliente
FOR EACH ROW
BEGIN
    DECLARE usuario_generado VARCHAR(20);
    SET usuario_generado = CONCAT((NEW.apellido_Cli), '.UTN');
    
  INSERT INTO Usuario (Cuil_us, Usuario_us, Contrasenia_us, Rol_us, Estado_us)
    VALUES (
        NEW.cuil_Cli,                  
        usuario_generado,              
        NEW.dni_Cli,                   
        0,                            
        NEW.estado_Cli                 
    );
END$$


DELIMITER $$

CREATE TRIGGER after_cliente_update
AFTER UPDATE ON Cliente
FOR EACH ROW
BEGIN
   
    IF OLD.estado_Cli <> NEW.estado_Cli THEN
   
        UPDATE Usuario
        SET Estado_us = NEW.estado_Cli
        WHERE Cuil_us = NEW.cuil_Cli;
        UPDATE Cuenta
        SET Estado_Cu = NEW.estado_Cli
        WHERE Cuil_Cli_Cu = NEW.cuil_Cli;
    END IF;
END$$

DELIMITER $$

CREATE TRIGGER after_prestamo_insert
AFTER INSERT ON Prestamo
FOR EACH ROW
BEGIN
    DECLARE interes DECIMAL(5,2);
    DECLARE meses INT;
    DECLARE importe_c_interes DECIMAL(11,2);
    DECLARE importe_x_cuotas DECIMAL(11,2);

  
	-- Obtener el valor de interes
	SELECT Interes_IXM
	INTO interes
	FROM interesxcantidaddmeses
	WHERE Plazo_d_Pagos_En_meses_IXM = NEW.Plazo_Pago_Pt;
    
    
	IF interes IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: la variable interes es NULL.';
	END IF;

	-- Obtener el valor de meses
	SELECT Meses_int
	INTO meses
	FROM interesxcantidaddmeses
	WHERE Plazo_d_Pagos_En_meses_IXM = NEW.Plazo_Pago_Pt;

	IF meses IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: la variable meses es NULL.';
	END IF;






    SET importe_c_interes = NEW.Importe_solicitado_Pt * (1 + (interes / 100));

 
    SET importe_x_cuotas = importe_c_interes / meses;

  	IF importe_x_cuotas IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: la variable importe_x_cuotas es NULL.';
	END IF;

  
    INSERT INTO DetallesXPrestamo (
        ID_Prestamo_Pt_Dt, 
        Importe_C_Interes_Dt, 
        Importe_X_Cuotas_Dt, 
        Cantidad_Cuotas_Dt
    )
    VALUES (
        NEW.ID_Prestamo_Pt,  
        importe_c_interes,   
        importe_x_cuotas,    
        meses               
    );
    
    CALL GenerarCuotas(NEW.ID_Prestamo_Pt, meses);
    
END$$

DELIMITER $$

CREATE PROCEDURE GenerarCuotas(
    IN p_ID_Prestamo INT,       -- ID del préstamo
    IN p_CantidadMeses INT      -- Cantidad de meses (cuotas)
)
BEGIN
    DECLARE v_FechaVencimiento DATE;
    DECLARE i INT DEFAULT 1;

    -- Inicializa la fecha de vencimiento como la fecha actual
    SET v_FechaVencimiento = CURDATE();

    -- Ciclo WHILE para iterar por cada cuota
    WHILE i <= p_CantidadMeses DO
        -- Inserta cada cuota en la tabla CuotasXPrestamos
        INSERT INTO CuotasXPrestamos (
            ID_Prestamo_Pt_Cp,
            Fecha_vencimiento_Cp,
            N_Cuota,
            pagada
        ) VALUES (
            p_ID_Prestamo,       -- ID del préstamo
            v_FechaVencimiento,  -- Fecha de vencimiento
            i,                   -- Número de cuota
            0                    -- Estado inicial: no pagada
        );

        -- Incrementa la fecha de vencimiento al siguiente mes
        SET v_FechaVencimiento = DATE_ADD(v_FechaVencimiento, INTERVAL 1 MONTH);
        
        -- Incrementa el contador de cuotas
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER TriggerInsertarSaldoPrestamo
AFTER UPDATE ON Prestamo
FOR EACH ROW
BEGIN
    IF NEW.Estado_Pt = 1 AND OLD.Estado_Pt <> 1 THEN
        UPDATE Cuenta
        SET Saldo_Cu = Saldo_Cu + NEW.Importe_solicitado_Pt
        WHERE Numero_de_Cuenta_Cu = NEW.Numero_de_Cuenta_Cu_Pt;
    END IF;
END$$

DELIMITER ;
-- INSERTS --

INSERT INTO InteresXCantidadDMeses (Plazo_d_Pagos_En_meses_IXM, Interes_IXM, Meses_int)
VALUES
('01M', 2, 1),   -- 1 mes
('03M', 9, 3),   -- 3 meses
('06M', 19, 6),   -- 6 meses, 
('09M', 34, 9),  -- 9 meses, 
('12M', 45, 12);  -- 12 meses


INSERT INTO Nacionalidad (Id_Nacionalidad_nc, Descripcion_nc) VALUES
('AR', 'Argentina'),
('BR', 'Brasil'),
('CL', 'Chile'),
('CO', 'Colombia'),
('MX', 'Mexico'),
('US', 'Estados Unidos'),
('FR', 'Francia'),
('DE', 'Alemania'),
('IT', 'Italia'),
('JP', 'Japon');

INSERT INTO Sexo (Descripcion) VALUES
('Masculino'),
('Femenino'),
('Prefiero No Decirlo');

INSERT INTO Provincia (Nombre_Prov_Prv) VALUES 
('Buenos Aires'), 
('Cordoba'),
('Santa Fe'), 
('Mendoza'),
('Tucuman');

INSERT INTO Localidad (ID_Provincia_Prv_Lca, Nombre_Loc_Lca) VALUES 
(1, 'La Plata'), 
(1, 'Mar del Plata'), 
(1, 'Bahia Blanca'),
(2, 'Cordoba Capital'),
(2, 'Villa Carlos Paz'),
(3, 'Rosario'),
(3, 'Santa Fe Capital'),
(4, 'Mendoza Capital'),
(4, 'San Rafael'),
(5, 'San Miguel de Tucuman'),
(5, 'Tafi del Valle');

INSERT INTO Usuario (Cuil_us, Usuario_us, Contrasenia_us, Rol_us, Estado_us)
VALUES ('20304050607', 'Pepe', '123', 1, 1);

INSERT INTO Cliente (
    cuil_Cli, dni_Cli, nombre_Clii, apellido_Cli, ID_sexo_Cli, ID_Nacionalidad_Cli, 
    fecha_nacimiento_Cli, direccion_Cli, ID_Localidad_Cli, correo_electronico_Cli, 
    telefono_Cli, estado_Cli
) VALUES 
('20-12345678-9', 12345678, 'Juan', 'Perez', 1, 'AR', '1990-05-10', 'Av. Siempre Viva 742', 1, 'juan.perez@gmail.com', '341-1234567', TRUE),
('27-87654321-8', 87654321, 'Maria', 'Gomez', 2, 'BR', '1985-03-25', 'Calle Principal 123', 3, 'maria.gomez@hotmail.com', '11-87654321', FALSE),
('30-11223344-7', 11223344, 'Carlos', 'Lopez', 1, 'CL', '1995-08-15', 'Av. Libertador 456', 6, 'carlos.lopez@yahoo.com', '261-1122334', TRUE);

INSERT INTO TipoCuenta (Nombre_Tipo) 
VALUES 
('Ahorro'),
('Corriente');

INSERT INTO TipoMovimiento (Id_TipoMov_TM, Descripcion_TM)
VALUES  (1, 'Alta de cuenta'),
		(2, 'Transferencia'),
        (3, 'Alta de prestamo'),
		(4, 'Pago de prestamo');



-- CUENTAS DE EJEMPLO
INSERT INTO Cuenta (Cuil_Cli_Cu, Fecha_Creacion_Cu, Id_Tipo_Cuenta, CBU_Cu, Saldo_Cu, Estado_Cu)
VALUES
('20-12345678-9', '2024-11-24', 1, '1234567890123456789012', 10000, 1);

INSERT INTO Cuenta (Cuil_Cli_Cu, Fecha_Creacion_Cu, Id_Tipo_Cuenta, CBU_Cu, Saldo_Cu, Estado_Cu)
VALUES
('27-87654321-8', '2024-11-24', 2, '2345678901234567890123', 15000, 1);

INSERT INTO Cuenta (Cuil_Cli_Cu, Fecha_Creacion_Cu, Id_Tipo_Cuenta, CBU_Cu, Saldo_Cu, Estado_Cu)
VALUES
('30-11223344-7', '2024-11-24', 1, '3456789012345678901234', 500, 1);


INSERT INTO Prestamo (
    Numero_de_Cuenta_Cu_Pt, 
    Importe_solicitado_Pt, 
    Plazo_Pago_Pt, 
    Detalle_solicitud_Pt, 
    Estado_Pt
) 
VALUES (
    1001,                           -- Número de cuenta existente en la tabla Cuenta
    50000.00,                        -- Importe solicitado (por ejemplo, 50,000.00)
    '06M',                           -- Plazo de pago (por ejemplo, 12 meses, debe existir en InteresXCantidadDMeses)
    'Préstamo para comprar un auto', -- Detalle de la solicitud
    0                                -- Estado inicial del préstamo (0 = Pendiente, 1 = Aprobado/Rechazado)
);

UPDATE Prestamo SET Estado_Pt = 1 WHERE ID_Prestamo_Pt = 1;

