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
    Saldo_Cu DECIMAL(11,2) DEFAULT 10000,  -- Saldo por defecto 10000
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
    Detalle_Mov VARCHAR(30),
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
    Meses INT,
    CONSTRAINT PK_Meses UNIQUE (Meses)
);

CREATE TABLE Prestamo(
    ID_Prestamo_Pt INT AUTO_INCREMENT PRIMARY KEY,
    Numero_de_Cuenta_Cu_Pt INT,
    Fecha_Peticion_Pt DATETIME DEFAULT CURRENT_TIMESTAMP,
    Importe_solicitado_Pt DECIMAL(11,2),
    Plazo_Pago_Pt CHAR(3),
   Detalle_solicitud_Pt VARCHAR(100),
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
   
    CONSTRAINT FK_Prestamo_Cuota FOREIGN KEY (ID_Prestamo_Pt_Cp) REFERENCES Prestamo(ID_Prestamo_Pt),
    CONSTRAINT UK_CuotasXPrestamos UNIQUE (ID_Prestamo_Pt_Cp, Fecha_vencimiento_Cp, N_Cuota)
);



use bancoutn;
INSERT INTO Nacionalidad (Id_Nacionalidad_nc, Descripcion_nc) VALUES
('AR', 'Argentina'),
('BR', 'Brasil'),
('CL', 'Chile'),
('CO', 'Colombia'),
('MX', 'México'),
('US', 'Estados Unidos'),
('FR', 'Francia'),
('DE', 'Alemania'),
('IT', 'Italia'),
('JP', 'Japón');

INSERT INTO Sexo (Descripcion) VALUES
('Masculino'),
('Femenino'),
('Prefiero No Decirlo');

INSERT INTO Provincia (Nombre_Prov_Prv) VALUES 
('Buenos Aires'), 
('Córdoba'),
('Santa Fe'), 
('Mendoza'),
('Tucumán');

INSERT INTO Localidad (ID_Provincia_Prv_Lca, Nombre_Loc_Lca) VALUES 
(1, 'La Plata'), 
(1, 'Mar del Plata'), 
(1, 'Bahía Blanca'),
(2, 'Córdoba Capital'),
(2, 'Villa Carlos Paz'),
(3, 'Rosario'),
(3, 'Santa Fe Capital'),
(4, 'Mendoza Capital'),
(4, 'San Rafael'),
(5, 'San Miguel de Tucumán'),
(5, 'Tafí del Valle');

INSERT INTO Usuario (Cuil_us, Usuario_us, Contrasenia_us, Rol_us, Estado_us)
VALUES ('20304050607', 'Pepe', '123', 1, 1);




