CREATE DATABASE tpintegrador;
USE tpintegrador;

CREATE TABLE Cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    telefono VARCHAR(255)
);

CREATE TABLE Tecnico (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    estado BOOLEAN
);

CREATE TABLE SistemaOperativo (
    tecnico_id BIGINT,
    sistema VARCHAR(255),
    FOREIGN KEY (tecnico_id) REFERENCES Tecnico(id)
);

CREATE TABLE SoporteOperativo (
    tecnico_id BIGINT,
    soporte VARCHAR(255),
    FOREIGN KEY (tecnico_id) REFERENCES Tecnico(id)
);

CREATE TABLE Reporte (
    id BIGINT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE Problema (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(255),
    descripcion VARCHAR(255),
    tipo VARCHAR(255),
    tiempoEstimado TIME,
    fechaTentativa TIME
);

CREATE TABLE Incidente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    estado VARCHAR(255),
    reporte_id BIGINT,
    tecnico_id BIGINT,
    cliente_id BIGINT,
    FOREIGN KEY (reporte_id) REFERENCES Reporte(id),
    FOREIGN KEY (tecnico_id) REFERENCES Tecnico(id),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);

ALTER TABLE Problema ADD COLUMN incidente_id BIGINT, ADD FOREIGN KEY (incidente_id) REFERENCES Incidente(id);

CREATE TABLE Tecnico_Problema (
    tecnico_id BIGINT,
    problema_id BIGINT,
    FOREIGN KEY (tecnico_id) REFERENCES Tecnico(id),
    FOREIGN KEY (problema_id) REFERENCES Problema(id)
);